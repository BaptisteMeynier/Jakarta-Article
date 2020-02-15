package com.meynier.jakarta.rest;

import com.meynier.jakarta.rest.param.FishTransactionParam;
import com.meynier.jakarta.service.FishServiceImpl;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;

@Valid
@Path("fish")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class FishResource {

    private static final Logger LOGGER = Logger.getLogger(FishResource.class.getName());

    @Inject
    private FishServiceImpl fishService;

    @Resource(name="concurrent/__defaultManagedExecutorService")
    private Executor executor;

    @GET
    @Path("{fishFamily}")
    public int countByFamily(@NotBlank @PathParam("fishFamily") String fishFamily) {
        return fishService.countByFamily(fishFamily);
    }

    @POST
    @Path("{fishName}")
    public Response buy(@BeanParam FishTransactionParam fishTransactionParam) {
        float bill = fishService.buy(fishTransactionParam.shopName, fishTransactionParam.fishName, fishTransactionParam.quantity);
        return Response.ok(bill).build();
    }

    @DELETE
    @Path("{fishName}")
    public Response sell(@PathParam("fishName") String fishName,
                         @MatrixParam("shopName") String shopName,
                         @MatrixParam("quantity") @DefaultValue("1") int quantity) {
        float bill = fishService.sell(shopName, fishName, quantity);
        return Response.ok(bill).build();
    }

    @GET
    @Path("negotiate/{fishName}")
    public void negotiatePrice(@Suspended final AsyncResponse asyncResponse) {
        this.fishService.callManager(executor)
            .thenApply(info -> asyncResponse.resume("None !!!"))
            .exceptionally(e -> asyncResponse.resume(Response.status(INTERNAL_SERVER_ERROR).entity(e).build()));

        asyncResponse.setTimeout(2000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                Response.status(SERVICE_UNAVAILABLE).entity("Operation timed out, manager is on a meeting").build()));
    }
}
