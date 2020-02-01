package com.meynier.jakarta;

import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.service.FishService;
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
    private FishService sampleService;
/*
    @Mock PersonDao personDao;

    @Test
    public void should_get_persons_by_name(){
        //GIVEN
        String name="Bernard";

        Fish person1 = new Fish();
        person1.setName("Dupont");
        person1.setLastName("Bernard");

        Fish person2 = new Fish();
        person2.setName("Dupont");
        person2.setLastName("Charline");

        when(personDao.findByName(any())).thenReturn(Arrays.asList(person1, person2));

        //WHEN
        List<Fish> persons = sampleService.findByName(name);

        //THEN
        verify(personDao,times(1)).findByName(any());
    }*/
}
