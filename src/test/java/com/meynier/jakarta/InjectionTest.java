package com.meynier.jakarta;

import com.meynier.jakarta.dao.PersonDao;
import com.meynier.jakarta.domain.Person;
import com.meynier.jakarta.service.SampleService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
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

@DisplayName("Programmaticaly Injection Unit Tests")
public class InjectionTest {


    private ServiceLocator serviceLocator;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        ServiceLocator serviceLocator =
                ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServiceLocatorUtilities.addClasses(serviceLocator, PersonDao.class, SampleService.class);
    }

    @Test
    public void it_should_get_person_native() throws Exception {
        SampleService foo = serviceLocator.getService(SampleService.class);
        assertThat(foo.sayHello(),is("Hello World"));
    }


}
