package com.meynier.jakarta.rest;

import com.meynier.jakarta.rest.param.FishTransactionParam;
import com.meynier.jakarta.service.FishServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Valid
@Path("fish")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class FishResource {

    private static final Logger LOGGER = Logger.getLogger(FishResource.class.getName());

    @Inject
    private FishServiceImpl fishService;

    @GET
    @Path("{fishFamily}")
    public int countByType(@NotBlank @PathParam("fishFamily") String fishFamily) {
        return fishService.countByType(fishFamily);
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
}
