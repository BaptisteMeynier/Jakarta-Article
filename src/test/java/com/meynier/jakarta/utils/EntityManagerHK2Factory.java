package com.meynier.jakarta.utils;

import org.glassfish.hk2.api.Factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerHK2Factory implements Factory<EntityManager> {

    private final EntityManagerFactory emf;

    public EntityManagerHK2Factory() {
        this.emf = TestUtils.getEntityManagerFactory();
    }

    public EntityManager provide() {
        return this.emf.createEntityManager();
    }

    @Override
    public void dispose(EntityManager entityManager) {
        entityManager.close();
        emf.close();
    }

}
