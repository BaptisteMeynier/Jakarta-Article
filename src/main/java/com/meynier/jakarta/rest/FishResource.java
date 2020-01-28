package com.meynier.jakarta.rest;

import com.meynier.jakarta.service.FishService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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
    public Response buy(@NotBlank String fishType, @Positive int quantity) {
        fishService.buy(fishType,quantity);
        return Response.ok().build();
    }

    @DELETE
    public Response sell(@NotBlank String fishFamily) {
        fishService.sell(fishFamily);
        return Response.ok().build();
    }
}
