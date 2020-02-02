package com.meynier.jakarta.rest;

import com.meynier.jakarta.rest.param.FishTransactionParam;
import com.meynier.jakarta.service.FishService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Valid
@Path("/fish")
@ApplicationScoped
public class FishResource {

    @Inject
    private FishService fishService;

    @GET
    public int countByType(@NotBlank String fishFamily) {
        return fishService.countByType(fishFamily);
    }

    @POST
    public Response buy(@BeanParam FishTransactionParam fishTransactionParam) {
        float bill = fishService.buy(fishTransactionParam.shopName, fishTransactionParam.fishName, fishTransactionParam.quantity);
        return Response.ok(bill).build();
    }

    @DELETE
    public Response sell(@BeanParam FishTransactionParam fishTransactionParam) {
        float bill = fishService.sell(fishTransactionParam.shopName, fishTransactionParam.fishName, fishTransactionParam.quantity);
        return Response.ok(bill).build();
    }
}
