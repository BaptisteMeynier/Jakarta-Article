package com.meynier.jakarta.service;

import com.meynier.jakarta.dao.PersonDao;
import com.meynier.jakarta.domain.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class SampleService {

    private PersonDao personDao;

    @Inject
    public SampleService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public String sayHello(){
        return "Hello World";
    }

    public List<Person> findByName(final String name){
        return personDao.findByName(name);
    }
}
