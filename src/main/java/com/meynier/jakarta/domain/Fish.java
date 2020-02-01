package com.meynier.jakarta.domain;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Fish.getAll", query = "select f from Fish f"),
        @NamedQuery(name = "Fish.countByFamily", query = "select count(f) from Fish f where f.family.name = :familyName")
}
)
public class Fish {

    @Id
    @Column(name = "FISH_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;
    @NotBlank
    @Column(name = "FISH_NAME")
    private String name;
    @Positive
    private int temperature;
    @Positive
    @DecimalMin("0.3")
    private float price;
    @Positive
    private int quantity;
    @NotNull
    private Family family;
    @NotNull
    @ManyToOne
    private Shop shop;

    public Fish() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return id == fish.id &&
                temperature == fish.temperature &&
                Float.compare(fish.price, price) == 0 &&
                quantity == fish.quantity &&
                Objects.equals(name, fish.name) &&
                Objects.equals(family, fish.family) &&
                Objects.equals(shop, fish.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, temperature, price, quantity, family, shop);
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", temperature=" + temperature +
                ", price=" + price +
                ", quantity=" + quantity +
                ", family=" + family +
                ", shop=" + shop +
                '}';
    }
}