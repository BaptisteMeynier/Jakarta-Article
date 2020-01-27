package com.meynier.jakarta.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.*;

@ApplicationScoped
public class EntityManagerConfigurator {

    @PersistenceContext
    EntityManager em;

    @Produces
    public EntityManager configureEm() {
        return em;
    }
}
