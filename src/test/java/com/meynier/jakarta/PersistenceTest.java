package com.meynier.jakarta;


import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.repository.ShopRepository;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@DisplayName("Programmaticaly Persistence Unit Tests")
public class PersistenceTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static ShopRepository shopRepository;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        Properties properties = new Properties();
        properties.put("eclipselink.persistencexml","META-INF/persistence-h2.xml");
        entityManagerFactory = Persistence.createEntityManagerFactory("JPADemo", properties);
        entityManager = entityManagerFactory.createEntityManager();
        shopRepository = new ShopRepository(entityManager);
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void it_should_count_fish_by_family() throws Exception {
        int total = shopRepository.countFishByFamily("Cichlidae");
        assertThat(total, is(3));
    }



}
