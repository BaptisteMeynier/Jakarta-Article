package com.meynier.jakarta.utils;

import org.glassfish.hk2.api.Factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public class EntityManagerHK2Factory implements Factory<EntityManager> {

    private final EntityManagerFactory emf;

    public EntityManagerHK2Factory() {
        Properties properties = new Properties();
        properties.put("eclipselink.persistencexml","META-INF/persistence-h2.xml");
        this.emf = Persistence.createEntityManagerFactory("JPADemo",properties);
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
