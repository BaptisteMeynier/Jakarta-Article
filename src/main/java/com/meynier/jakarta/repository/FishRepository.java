package com.meynier.jakarta.repository;

import com.meynier.jakarta.domain.Fish;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Singleton
public class FishRepository {


    private EntityManager entityManager;

    public FishRepository() {
    }

    @Inject
    public FishRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Fish> getAllNativeVersion() {
        return entityManager.createNativeQuery("select * from Person", Fish.class).getResultList();
    }

    public List<Fish> getAll() {
        return entityManager.createNamedQuery("Person.getAll", Fish.class).getResultList();
    }

    public List<Fish> findByName(final String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fish> listCriteria = builder.createQuery(Fish.class);
        Root<Fish> listRoot = listCriteria.from(Fish.class);
        listCriteria.select(listRoot).where(builder.equal(listRoot.get("name"),name));
        TypedQuery<Fish> query = entityManager.createQuery(listCriteria);
        return query.getResultList();
    }

    @Transactional
    public void save(final Fish person){
        entityManager.persist(person);
    }

    public int countByType(String fishFamily) {
        return entityManager.createNamedQuery("Fish.countByFamily", Integer.class)
                .setParameter("familyName",fishFamily)
                .getSingleResult();
    }
}
