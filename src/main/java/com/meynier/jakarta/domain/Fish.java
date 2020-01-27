package com.meynier.jakarta.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Fish.getAll", query = "select f from Fish f"),
        @NamedQuery(name = "Fish.countByFamily", query = "select count(f) from Fish f where f.family.name = :familyName")
}
)
public class Fish {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;
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
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", family=" + family +
                ", shop=" + shop +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return id == fish.id &&
                Objects.equals(family, fish.family) &&
                Objects.equals(shop, fish.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, family, shop);
    }
}