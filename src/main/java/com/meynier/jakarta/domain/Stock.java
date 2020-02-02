package com.meynier.jakarta.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Stock implements Serializable {

    @EmbeddedId
    private FishStockKey id;

    @ManyToOne
    @MapsId("fishFk")
    @JoinColumn(name = "fish_fk")
    private Fish fish;

    @ManyToOne
    @MapsId("shopFk")
    @JoinColumn(name = "shop_fk")
    private Shop shop;

    private int quantity;

    public Stock() {
    }

    public FishStockKey getId() {
        return id;
    }

    public void setId(FishStockKey id) {
        this.id = id;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return quantity == stock.quantity &&
                Objects.equals(fish, stock.fish) &&
                Objects.equals(shop, stock.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fish, shop, quantity);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "fish=" + fish +
                ", shop=" + shop +
                ", quantity=" + quantity +
                '}';
    }
}
