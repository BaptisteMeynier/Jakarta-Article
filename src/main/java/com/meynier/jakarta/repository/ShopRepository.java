package com.meynier.jakarta.repository;

import com.meynier.jakarta.domain.Shop;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@ApplicationScoped
public class ShopRepository {
    @Inject
    private EntityManager entityManager;

    public Shop findMainShop() {
        return entityManager.createNamedQuery("Shop.findMoney", Shop.class)
                .setParameter("name","Main")
                .getSingleResult();
    }

    public void spend(Shop shop, float total) {
        shop.setAccount(shop.getAccount() - total);
        entityManager.persist(shop);
    }
}
