package com.meynier.jakarta;


import com.meynier.jakarta.dao.PersonDao;
import com.meynier.jakarta.domain.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@DisplayName("Programmaticaly Persistence Unit Tests")
public class PersistenceTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static PersonDao personDao;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("JPADemo");
        entityManager = entityManagerFactory.createEntityManager();
        personDao = new PersonDao(entityManager);
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void it_should_get_person_native() throws Exception {
        List<Person> all = personDao.getAllNativeVersion();
        assertThat(all.size(), is(3));
    }

    @Test
    public void it_should_get_person() throws Exception {
        List<Person> all = personDao.getAll();
        assertThat(all.size(), is(3));
    }

}
