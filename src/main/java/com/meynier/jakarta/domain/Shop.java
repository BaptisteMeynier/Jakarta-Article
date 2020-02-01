package com.meynier.jakarta.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Shop.findMoney", query = "select s from Shop s where name = :name")
}
)
public class Shop {
    @Id
    @Column(name = "SHOP_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    @NotBlank
    @Column(name = "SHOP_NAME")
    private String name;
    @Email
    @NotBlank
    private String email;
    @PastOrPresent
    private Date creation;
    @Pattern(regexp = "(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}")
    private String phoneNumber;
    private float account;
    @OneToMany
    private Collection<Fish> stock;

    public Shop() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getAccount() {
        return account;
    }

    public void setAccount(float account) {
        this.account = account;
    }

    public Collection<Fish> getStock() {
        return stock;
    }

    public void setStock(Collection<Fish> stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id == shop.id &&
                Float.compare(shop.account, account) == 0 &&
                Objects.equals(name, shop.name) &&
                Objects.equals(email, shop.email) &&
                Objects.equals(creation, shop.creation) &&
                Objects.equals(phoneNumber, shop.phoneNumber) &&
                Objects.equals(stock, shop.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, creation, phoneNumber, account, stock);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", creation=" + creation +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", account=" + account +
                ", stock=" + stock +
                '}';
    }
}
