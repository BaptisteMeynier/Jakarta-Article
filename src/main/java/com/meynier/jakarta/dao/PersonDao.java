package com.meynier.jakarta.dao;

import com.meynier.jakarta.domain.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Predicate;

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

    public List<Person> getAllNativeVersion() {
        return entityManager.createNativeQuery("select * from Person", Person.class).getResultList();
    }

    public List<Person> getAll() {
        return entityManager.createNamedQuery("Person.getAll", Person.class).getResultList();
    }

    public List<Person> findByName(final String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> listCriteria = builder.createQuery(Person.class);
        Root<Person> listRoot = listCriteria.from(Person.class);
        listCriteria.select(listRoot).where(builder.equal(listRoot.get("name"),name));
        TypedQuery<Person> query = entityManager.createQuery(listCriteria);
        return query.getResultList();
    }

    @Transactional
    public void save(final Person person){
        entityManager.persist(person);
    }
}


