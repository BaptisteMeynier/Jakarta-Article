package com.meynier.jakarta.dao;

import com.meynier.jakarta.domain.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public PersonDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Person> getAll() {
        return entityManager.createNamedQuery("Person.getAll", Person.class).getResultList();
    }

    public List<Person> getAllNativeVersion() {
        return entityManager.createNativeQuery("select * from Person", Person.class).getResultList();
    }

    @Transactional
    public void save(final Person person){
        entityManager.persist(person);
    }
}


