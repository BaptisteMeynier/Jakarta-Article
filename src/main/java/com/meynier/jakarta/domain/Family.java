package com.meynier.jakarta.domain;

import com.meynier.jakarta.domain.enums.WaterType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
public class Family {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    @NotBlank
    private String name;
    @NotNull
    private WaterType waterType;
    @Positive
    private int temperature;
    @Positive
    @DecimalMin("0.3")
    private float price;

    public Family() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return id == family.id &&
                temperature == family.temperature &&
                Float.compare(family.price, price) == 0 &&
                Objects.equals(name, family.name);
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", temperature=" + temperature +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, temperature, price);
    }
}
