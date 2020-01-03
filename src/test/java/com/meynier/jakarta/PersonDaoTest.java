package com.meynier.jakarta;

import com.meynier.jakarta.dao.PersonDao;
import com.meynier.jakarta.domain.Person;
//import org.arquillian.ape.api.UsingDataSet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


@RunWith(Arquillian.class)
public class PersonDaoTest {

    @EJB
    private PersonDao personDao;

    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(Person.class)
                .addClass(PersonDao.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Test
   // @UsingDataSet("datasets/person.yml")
    public void shouldReturnAllPerson() throws Exception {
        List<Person> personList = personDao.getAll();

        assertNotNull(personList);
        assertThat(personList.size(), is(1));
        assertThat(personList.get(0).getName(), is("John"));
        assertThat(personList.get(0).getLastName(), is("Malkovich"));
    }
}


