package com.meynier.jakarta.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@ApplicationScoped
public class ShopRepository {
    @Inject
    private EntityManager entityManager;

    public float findMoneyForMainShop() {
        return entityManager.createNamedQuery("Shop.findMoney", Integer.class)
                .setParameter("name","Main")
                .getSingleResult();
    }
}
