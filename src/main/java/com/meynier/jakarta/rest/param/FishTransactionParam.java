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

    public FishTransactionParam() {
    }

    public FishTransactionParam(@NotBlank String fishName, @NotBlank String shopName, @Positive int quantity) {
        this.fishName = fishName;
        this.shopName = shopName;
        this.quantity = quantity;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
