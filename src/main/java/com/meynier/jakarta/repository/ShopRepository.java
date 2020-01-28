package com.meynier.jakarta.repository;

import com.meynier.jakarta.domain.Family;
import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.domain.Shop;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Named
@ApplicationScoped
public class ShopRepository {

    private EntityManager entityManager;

    public ShopRepository() {
    }

    @Inject
    public ShopRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Family findFamilyByName(String fishFamily) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Family> listCriteria = builder.createQuery(Family.class);
        Root<Family> listRoot = listCriteria.from(Family.class);
        listCriteria.select(listRoot).where(builder.equal(listRoot.get("name"),fishFamily));
        TypedQuery<Family> query = entityManager.createQuery(listCriteria);
        return query.getSingleResult();
    }

    public int countFishByFamily(String fishFamily) {
        return entityManager.createNamedQuery("Fish.countByFamily", Integer.class)
                .setParameter("familyName", fishFamily)
                .getSingleResult();
    }

    public void buyFish(Shop shop, Family fishFamily) {
        Fish fish = new Fish();
        fish.setFamily(fishFamily);
        fish.setShop(shop);
        entityManager.persist(fish);
    }

    public void sellFish(String fishFamily) {
        entityManager.createNamedQuery("delete from Fish where id = (select max(id) from Fish) and family_id=:fishFamily")
                .setParameter("fishFamily", fishFamily)
                .getSingleResult();
    }

    public Shop findMainShop() {
        return entityManager.createNamedQuery("Shop.findMoney", Shop.class)
                .setParameter("name","Main")
                .getSingleResult();
    }

    public void moneyTransaction(Shop shop, float money) {
        shop.setAccount(shop.getAccount() + money);
        entityManager.persist(shop);
    }

}
