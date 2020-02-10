package com.meynier.jakarta.rest;

import com.meynier.jakarta.rest.param.FishTransactionParam;
import com.meynier.jakarta.service.FishService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Valid
@Path("/fish")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class FishResource {

    private static final Logger LOGGER = Logger.getLogger(FishResource.class.getName());

    @Inject
    private FishService fishService;

    @GET
    @Path("{fishFamily}")
    public int countByType(@NotBlank @PathParam("fishFamily") String fishFamily) {
        return fishService.countByType(fishFamily);
    }

    @POST
    public Response buy(@BeanParam FishTransactionParam fishTransactionParam) {
        LOGGER.info("BUY");
        float bill = fishService.buy(fishTransactionParam.shopName, fishTransactionParam.fishName, fishTransactionParam.quantity);
        return Response.ok(bill).build();
    }

    @DELETE
    @Path("del3")
    public void sell3() {
        String tot="toto";
    }

    @DELETE
    @Path("del1")
    public Response sell1() {
        return Response.ok("Del1").build();
    }

    @DELETE
    @Path("del2")
    public Response sell2() {
        return Response.ok("Del2").build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("del4")
    public Response sell4() {
        LOGGER.info("INSIDE");
        float bill = fishService.sell("Magic Fish", "Discus", 1);
        return Response.ok(bill).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("del5/{shopName}/{fishName}/{quantity}")
    public Response sell5(@PathParam("shopName") String shopName, @PathParam("fishName") String fishName,  @PathParam("quantity") int quantity) {
        LOGGER.info("INSIDE");
        float bill = fishService.sell(shopName, fishName, quantity);
        return Response.ok(bill).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("del6")
    public Response sell6( @NotBlank @FormParam("shopName")  String shopName,
            @NotBlank @FormParam("fishName")  String fishName,
    @FormParam("quantity") int quantity) {
        LOGGER.info("INSIDE");
        float bill = fishService.sell("Magic Fish", "Discus", 1);
        return Response.ok(bill).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("del7/{fishName}/{quantity}")
    public Response sell5(@PathParam("fishName") String fishName,  @PathParam("quantity") int quantity) {
        LOGGER.info("INSIDE");
        float bill = fishService.sell("Magic Fish", fishName, quantity);
        return Response.ok(bill).build();
    }


    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sell(@BeanParam FishTransactionParam fishTransactionParam) {
        LOGGER.info("INSIDE");
        float bill = fishService.sell(fishTransactionParam.shopName, fishTransactionParam.fishName, fishTransactionParam.quantity);
        return Response.ok(bill).build();
    }
}
