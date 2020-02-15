package com.meynier.jakarta;

import com.meynier.jakarta.domain.Family;
import com.meynier.jakarta.domain.enums.WaterType;
import com.meynier.jakarta.repository.ShopRepository;
import com.meynier.jakarta.utils.TestUtils;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@EnableWeld
public class WeldInjectionTest {

    @Inject
    private ShopRepository shopRepository;

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(ShopRepository.class, WeldInjectionTest.class).build();

    @ApplicationScoped
    @Produces
    EntityManager produceEntityManager() {
        return TestUtils.getEntityManagerFactory().createEntityManager();
    }

    @Test
    void it_should_inject_repository(){
        //GIVEN
        Family family = new Family();
        family.setName("Scombridae");
        family.setWaterType(WaterType.SEA);
        //WHEN
        shopRepository.saveFamily(family);
        //THEN
    }
}
