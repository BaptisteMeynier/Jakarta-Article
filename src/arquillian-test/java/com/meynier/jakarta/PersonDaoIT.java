package com.meynier.jakarta;

import com.meynier.jakarta.config.EntityManagerConfigurator;
import com.meynier.jakarta.domain.Fish;
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
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class PersonDaoIT {
/*
    @Inject
    private PersonDao personDao;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(Fish.class)
                .addClass(PersonDao.class)
                .addClass(EntityManagerConfigurator.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(0)
    @UsingDataSet("datasets/person.yml")
    public void shouldReturnAllPerson() throws Exception {
        //GIVEN
        //WHEN
        List<Fish> personList = personDao.getAll();
        //THEN
        assertNotNull(personList);
        assertThat(personList.size(), is(1));
   //     assertThat(personList.get(0).getName(), is("John"));
   //     assertThat(personList.get(0).getLastName(), is("Malkovich"));
    }

    @Test
    @InSequence(1)
    @ShouldMatchDataSet(value="datasets/expected-person.yml", excludeColumns="id")
    public void shouldPersistPerson() throws Exception {
        //GIVEN
        Fish person = new Fish();
    //    person.setLastName("Antoine");
    //    person.setName("Chesnoy");
        //WHEN
        personDao.save(person);
        //THEN
    }*/
}


