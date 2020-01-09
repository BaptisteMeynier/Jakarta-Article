package com.meynier.jakarta;

import com.meynier.jakarta.rest.PersonResource;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicroShedTest
public class PersonResourceIT {

    private final static String CONTEXT_APPLICATION = "jakarta-article";
    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withAppContextRoot(String.format("/%s", CONTEXT_APPLICATION))
            .withReadinessPath(String.format("/%s/app/person",CONTEXT_APPLICATION));

    @RESTClient
    public static PersonResource personResource;

    @Test
    public void sayHello() throws IOException {
        String data = personResource.getPerson();

        assertTrue(data.startsWith("Hello World"));
        System.out.println(data);

    }

}
