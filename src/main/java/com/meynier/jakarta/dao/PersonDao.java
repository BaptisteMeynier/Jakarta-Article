package com.meynier.jakarta.dao;

import com.meynier.jakarta.domain.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class PersonDao {


    private EntityManager entityManager;


    @Inject
    public PersonDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public PersonDao() {
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


