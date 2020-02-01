package com.meynier.jakarta;

import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.repository.ShopRepository;
import com.meynier.jakarta.service.FishService;
import com.meynier.jakarta.utils.EntityManagerHK2Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@DisplayName("Programmaticaly Injection Unit Tests")
public class InjectionTest {

    private static ServiceLocator serviceLocator;

    @BeforeAll
    public static void setUpBeforeClass() {
        serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServiceLocatorUtilities.addClasses(serviceLocator, EntityManagerHK2Factory.class, FishService.class, ShopRepository.class);
    }
/*
    @Test
    public void it_should_call_service() {
        //GIVEN
        FishService foo = serviceLocator.getService(FishService.class);
        //WHEN
        String greeting = foo.sayHello();
        //THEN
        assertThat(greeting, is("Hello World"));
    }

    @Test
    public void it_should_initialize_entityManager() {
        //GIVEN
        PersonDao foo = serviceLocator.getService(PersonDao.class);
        //WHEN
        List<Fish> all = foo.getAll();
        //THEN
        assertThat(all.size(),is(3));
    }
*/


}
