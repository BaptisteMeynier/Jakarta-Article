package com.meynier.jakarta.domain;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Fish.getAll", query = "select f from Fish f"),
        @NamedQuery(name = "Fish.findByName", query = "select f from Fish f where f.name = :fishName"),
        @NamedQuery(name = "Fish.countByFamily", query = "select count(fi) from Fish fi where fi.family.name = :familyName")
}
)
public class Fish {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;
    @NotBlank
    private String name;
    @Positive
    private int temperature;
    @Positive
    @DecimalMin("0.3")
    private float price;
    @Positive
    private int quantity;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="family_fk")
    private Family family;
    @NotNull
    @OneToMany(mappedBy = "fish")
    private Collection<Stock> stock;

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

    public Collection<Stock> getStock() {
        return stock;
    }

    public void setStock(Collection<Stock> stock) {
        this.stock = stock;
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
                Objects.equals(stock, fish.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, temperature, price, quantity, family, stock);
    }
}