package com.meynier.jakarta;

import com.meynier.jakarta.dao.PersonDao;
import com.meynier.jakarta.domain.Person;
import com.meynier.jakarta.service.SampleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @InjectMocks
    private SampleService sampleService;

    @Mock PersonDao personDao;

    @Test
    public void should_get_persons_by_name(){
        //GIVEN
        String name="Bernard";

        Person person1 = new Person();
        person1.setName("Dupont");
        person1.setLastName("Bernard");

        Person person2 = new Person();
        person2.setName("Dupont");
        person2.setLastName("Charline");

        when(personDao.findByName(any())).thenReturn(Arrays.asList(person1, person2));

        //WHEN
        List<Person> persons = sampleService.findByName(name);

        //THEN
        verify(personDao,times(1)).findByName(any());
    }
}
