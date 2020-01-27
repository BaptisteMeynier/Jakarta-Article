package com.meynier.jakarta;

import com.meynier.jakarta.rest.FishResource;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;


import static org.junit.jupiter.api.Assertions.assertTrue;

@MicroShedTest
public class PersonResourceIT {

    private final static String CONTEXT_APPLICATION = "jakarta-article";

    @Container
    public static ApplicationContainer app = new ApplicationContainer("jakarta-article:1.0.0")
            .withAppContextRoot(CONTEXT_APPLICATION);

    @RESTClient
    public static FishResource personResource;

    @Test
    public void sayHello() {
      //  String data = personResource.getPerson();

    //    assertTrue(data.startsWith("Hello World"));
    }

}
