package com.meynier.jakarta.rest.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.ws.rs.FormParam;

public class FishTransactionParam {
    @NotBlank @FormParam("shopName") public String shopName;
    @NotBlank @FormParam("fishName") public String fishName;
    @Positive @FormParam("quantity") public int quantity;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
