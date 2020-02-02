package com.meynier.jakarta;


import com.meynier.jakarta.domain.Family;
import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.domain.Shop;
import com.meynier.jakarta.repository.ShopRepository;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@DisplayName("Programmaticaly Persistence Unit Tests")
public class PersistenceTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static ShopRepository shopRepository;

    @BeforeAll
    public static void setUpBeforeClass() {
        Properties properties = new Properties();
        properties.put("eclipselink.persistencexml","META-INF/persistence-h2.xml");
        entityManagerFactory = Persistence.createEntityManagerFactory("JPADemo", properties);
        entityManager = entityManagerFactory.createEntityManager();
        shopRepository = new ShopRepository(entityManager);
    }

    @AfterAll
    public static void tearDownAfterClass() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void it_should_count_fish_by_family() {
        //GIVEN
        String familyName = "Cichlidae";
        //WHEN
        int total = shopRepository.countFishByFamily(familyName);
        //THEN
        assertThat(total, is(4));
    }

    @Test
    public void it_should_find_family_by_name() {
        //GIVEN
        String familyName = "Cichlidae";
        //WHEN
        Family family = shopRepository.findFamilyByName(familyName);
        //THEN
        assertThat(family.getFishs().size(), is(4));
    }

    @Test
    public void it_should_find_shop_by_name() {
        //GIVEN
        String familyName = "Magic Fish";
        //WHEN
        Shop shop = shopRepository.findShopByName(familyName);
        //THEN
        assertThat(shop.getName(), is(familyName));
    }


}
