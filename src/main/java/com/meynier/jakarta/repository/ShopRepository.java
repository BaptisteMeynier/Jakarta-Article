package com.meynier.jakarta.repository;

import com.meynier.jakarta.domain.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.QueryHint;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Named
@ApplicationScoped
public class ShopRepository {

    private final static String FETCHGRAPH = "javax.persistence.fetchgraph";

    private EntityManager entityManager;

    public ShopRepository() {
    }

    @Inject
    public ShopRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    //----- NATIVE QUERY -----//

    public Shop findShopByName(String shopName) {
        return (Shop)entityManager.createNativeQuery(String.format("select * from Shop where name = '%s'", shopName),Shop.class)
                .setParameter("name", shopName)
                .getSingleResult();
    }

    //----- NAMED QUERY -----//

    public int countFishByFamily(String familyName) {
        return entityManager.createNamedQuery("Fish.countByFamily", Long.class)
                .setParameter("familyName", familyName)
                .getSingleResult()
                .intValue();
    }

    public Fish findFishByName(String fishName) {
        return entityManager.createNamedQuery("Fish.findByName", Fish.class)
                .setParameter("fishName", fishName)
                .getSingleResult();
    }

    //----- CRITERIA QUERY -----//

    public Family findFamilyByName(String fishFamily) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Family> listCriteria = builder.createQuery(Family.class);
        Root<Family> listRoot = listCriteria.from(Family.class);
        listCriteria.select(listRoot).where(builder.equal(listRoot.get("name"),fishFamily));
        TypedQuery<Family> query = entityManager.createQuery(listCriteria);
        return query.getSingleResult();
    }

    public Stock findStock(String shopName, String fishName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Stock> listCriteria = builder.createQuery(Stock.class);

        Root<Stock> listRoot = listCriteria.from(Stock.class);
        listCriteria.select(listRoot)
                .where(builder.and(
                        builder.equal(listRoot.get("shop").get("name"),shopName),
                        builder.equal(listRoot.get("fish").get("name"),fishName)
                        ));
        TypedQuery<Stock> query = entityManager.createQuery(listCriteria);
        return query.getSingleResult();
    }

    //----- SIMPLY ENTITY MANAGER -----//

    public void saveStock(Stock stock) {
        entityManager.persist(stock);
    }

    public void saveShop(Shop shop) {
        entityManager.persist(shop);
    }

    @Transactional
    public void saveFamily(Family family) {
        entityManager.persist(family);
    }
}
