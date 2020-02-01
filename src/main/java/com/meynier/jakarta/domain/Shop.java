package com.meynier.jakarta.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Shop.findMoney", query = "from Shop s where s.name = :name")
})
public class Shop {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @PastOrPresent
    private Date creation;
    @Column(name="PHONE_NUMBER")
    @Pattern(regexp = "(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}")
    private String phoneNumber;
    private float account;
    @OneToMany(mappedBy = "shop")
    private Set<Stock> stocks;

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

    public Collection<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
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
                Objects.equals(stocks, shop.stocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, creation, phoneNumber, account, stocks);
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
                ", stocks=" + stocks +
                '}';
    }
}
