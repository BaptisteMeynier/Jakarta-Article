package com.meynier.jakarta.domain;

import com.meynier.jakarta.domain.enums.WaterType;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Collection;
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
    @OneToMany
    private Collection<Fish> fishs;

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

    public WaterType getWaterType() {
        return waterType;
    }

    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
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

    public Collection<Fish> getFishs() {
        return fishs;
    }

    public void setFishs(Collection<Fish> fishs) {
        this.fishs = fishs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return id == family.id &&
                temperature == family.temperature &&
                Float.compare(family.price, price) == 0 &&
                Objects.equals(name, family.name) &&
                waterType == family.waterType &&
                Objects.equals(fishs, family.fishs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, waterType, temperature, price, fishs);
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", waterType=" + waterType +
                ", temperature=" + temperature +
                ", price=" + price +
                ", fishs=" + fishs +
                '}';
    }
}

