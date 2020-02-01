package com.meynier.jakarta.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FishStockKey implements Serializable {

    @Column(name = "fish_id")
    private Long fishId;

    @Column(name = "shop_id")
    private Long shopId;

    public FishStockKey() {
    }

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FishStockKey that = (FishStockKey) o;
        return Objects.equals(fishId, that.fishId) &&
                Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fishId, shopId);
    }
}
