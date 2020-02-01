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


    //----- NATIVE QUERY -----//

    public void sellFish(String fishFamily) {
        entityManager.createNativeQuery("delete from Fish where id = (select max(id) from Fish) and family_id=:fishFamily")
                .setParameter("fishFamily", fishFamily)
                .getSingleResult();
    }

    //----- NAMED QUERY -----//

    public int countFishByFamily(String familyName) {
        return entityManager.createNamedQuery("Fish.countByFamily", Long.class)
                .setParameter("familyName", familyName)
                .getSingleResult()
                .intValue();
    }

    public Shop findMainShop() {
        return entityManager.createNamedQuery("Shop.findMoney", Shop.class)
                .setParameter("name","Main")
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

    //----- SIMPLY ENTITY MANAGER -----//

    public void buyFish(Shop shop, Family fishFamily) {
        Fish fish = new Fish();
        fish.setFamily(fishFamily);
      //  fish.setShop(shop);
        entityManager.persist(fish);
    }


    public void moneyTransaction(Shop shop, float money) {
        shop.setAccount(shop.getAccount() + money);
        entityManager.persist(shop);
    }

    public Shop findShopByName(String shopName) {
        //entityManager.
        return null;
    }

    public Fish findFishByName(String fishName) {
        return null;
    }
}
