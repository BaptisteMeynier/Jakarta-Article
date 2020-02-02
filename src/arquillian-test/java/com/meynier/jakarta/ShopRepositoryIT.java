package com.meynier.jakarta;

import com.meynier.jakarta.config.EntityManagerConfigurator;
import com.meynier.jakarta.domain.Family;
import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.domain.enums.WaterType;
import com.meynier.jakarta.repository.ShopRepository;
import org.arquillian.ape.api.UsingDataSet;
import org.arquillian.ape.rdbms.ShouldMatchDataSet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class ShopRepositoryIT {

    @Inject
    private ShopRepository shopRepository;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, Fish.class.getPackage())
                .addClass(ShopRepository.class)
                .addClass(EntityManagerConfigurator.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(0)
    @UsingDataSet("datasets/fish.yml")
    public void it_should_find_fish_for_family() throws Exception {
        //GIVEN
        String familyName = "Cichlidae";
        //WHEN
        Family family = shopRepository.findFamilyByName(familyName);
        //THEN
        assertNotNull(family);
        assertThat(family.getName(), is(familyName));
        assertThat(family.getFishs().size(), is(2));
    }

    @Test
    @InSequence(1)
    @ShouldMatchDataSet(value= "datasets/expected-fish.yml", excludeColumns="id")
    public void it_should_persist_family_and_fishs() throws Exception {
        //GIVEN
        Fish shark1 = new Fish();
        shark1.setName("Requin marteau");
        shark1.setTemperature(16);
        shark1.setPrice(30000);

        Family family = new Family();
        family.setName("Sphyrnidae");
        family.setWaterType(WaterType.SEA);
        family.setFishs(Stream.of(shark1).collect(Collectors.toList()));

        shark1.setFamily(family);

        //WHEN
        shopRepository.saveFamily(family);
        //THEN
    }
}


