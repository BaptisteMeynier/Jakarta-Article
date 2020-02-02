package com.meynier.jakarta.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FishStockKey implements Serializable {

    @Column(name = "fish_fk")
    private Long fishFk;

    @Column(name = "shop_fk")
    private Long shopFk;

    public FishStockKey() {
    }

    public Long getFishFk() {
        return fishFk;
    }

    public void setFishFk(Long fishFk) {
        this.fishFk = fishFk;
    }

    public Long getShopFk() {
        return shopFk;
    }

    public void setShopFk(Long shopFk) {
        this.shopFk = shopFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FishStockKey that = (FishStockKey) o;
        return Objects.equals(fishFk, that.fishFk) &&
                Objects.equals(shopFk, that.shopFk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fishFk, shopFk);
    }
}
