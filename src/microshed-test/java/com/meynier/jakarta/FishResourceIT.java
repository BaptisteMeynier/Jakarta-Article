package com.meynier.jakarta;

import com.meynier.jakarta.rest.FishResource;
import com.meynier.jakarta.rest.param.FishTransactionParam;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@MicroShedTest
public class FishResourceIT {

    private final static String CONTEXT_APPLICATION = "jakarta-article";

    @Container
    public static ApplicationContainer app = new ApplicationContainer("jakarta-article:1.0.0")
            .withAppContextRoot(CONTEXT_APPLICATION);

    @RESTClient
    public static FishResource fishResource;

    @Test
    public void buyExistingFish() {
        //Given
        String existingShop = "Magic Fish";
        String existingFish = "Scalaire";
        FishTransactionParam fishTransactionParam = new FishTransactionParam();
        fishTransactionParam.fishName = existingFish;
        fishTransactionParam.quantity = 1;
        fishTransactionParam.shopName = existingShop;

        //When
        Response response = fishResource.buy(fishTransactionParam);

        //Then
        assertThat(response.getStatus(),is(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void buyInexistingFish() {
        //Given
        String existingShop = "Magic Fish";
        String inexistingFish = "Sun";
        FishTransactionParam fishTransactionParam = new FishTransactionParam();
        fishTransactionParam.fishName = inexistingFish;
        fishTransactionParam.quantity = 1;
        fishTransactionParam.shopName = existingShop;

        //When
        Response response = fishResource.buy(fishTransactionParam);

        //Then
        assertThat(response.getStatus(),is(Response.Status.NOT_FOUND.getStatusCode()));
    }

}
