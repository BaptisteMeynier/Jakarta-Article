package com.meynier.jakarta.repository;

import com.meynier.jakarta.domain.Family;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class FamilyRepository {

    private EntityManager entityManager;

    public Family findByName(String fishFamily) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Family> listCriteria = builder.createQuery(Family.class);
        Root<Family> listRoot = listCriteria.from(Family.class);
        listCriteria.select(listRoot).where(builder.equal(listRoot.get("name"),fishFamily));
        TypedQuery<Family> query = entityManager.createQuery(listCriteria);
        return query.getSingleResult();
    }
}
