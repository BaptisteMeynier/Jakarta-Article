package com.meynier.jakarta.rest.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


public class FishTransactionParam {
    @NotBlank public String shopName;
    @NotBlank public String fishName;
    @Positive public int quantity;
}
