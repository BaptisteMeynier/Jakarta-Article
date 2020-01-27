package com.meynier.jakarta.repository;

import com.meynier.jakarta.domain.Family;
import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.domain.Shop;

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

    @Transactional
    public void save(final Fish person) {
        entityManager.persist(person);
    }

    public int countByType(String fishFamily) {
        return entityManager.createNamedQuery("Fish.countByFamily", Integer.class)
                .setParameter("familyName", fishFamily)
                .getSingleResult();
    }

    public void addFish(Shop shop, Family fishFamily) {
        Fish fish = new Fish();
        fish.setFamily(fishFamily);
        fish.setShop(shop);
        entityManager.persist(fish);
    }

    public void deleteFish(Shop shop, Family fishFamily) {
        Fish fish = (Fish) entityManager.createNativeQuery(
                "select * " +
                        "from Fish fi join Family fa on fi.family_id = fa.family_id " +
                        "where " +
                        "fa.family_name = :familyName" +
                        "fa.family_name = :shopName",
                Fish.class)
                .setParameter("familyName", fishFamily)
                .setParameter("shopName", shop.getName())
                .setMaxResults(1)
                .getSingleResult();
        entityManager.remove(fish);
    }
}
