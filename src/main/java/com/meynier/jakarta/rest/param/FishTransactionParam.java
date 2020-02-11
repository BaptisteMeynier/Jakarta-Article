package com.meynier.jakarta.rest.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;

public class FishTransactionParam {
    @NotBlank @PathParam("fishName") public String fishName;
    @DefaultValue("Magic Fish") @NotBlank @FormParam("shopName") public String shopName;
    @Positive @FormParam("quantity") public int quantity;
}
