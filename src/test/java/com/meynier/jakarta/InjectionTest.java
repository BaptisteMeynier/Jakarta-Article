package com.meynier.jakarta;

import com.meynier.jakarta.dao.PersonDao;
import com.meynier.jakarta.service.SampleService;
import com.meynier.jakarta.utils.EntityManagerHK2Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@DisplayName("Programmaticaly Injection Unit Tests")
public class InjectionTest {

    private static ServiceLocator serviceLocator;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServiceLocatorUtilities.addClasses(serviceLocator, EntityManagerHK2Factory.class, SampleService.class, PersonDao.class);
    }

    @Test
    public void it_should_call_service() throws Exception {
        SampleService foo = serviceLocator.getService(SampleService.class);
        assertThat(foo.sayHello(),is("Hello World"));
    }

    @Test
    public void it_should_initialize_entityManager() throws Exception {
        PersonDao foo = serviceLocator.getService(PersonDao.class);
        assertThat(foo.getAll().size(),is(3));
    }



}
