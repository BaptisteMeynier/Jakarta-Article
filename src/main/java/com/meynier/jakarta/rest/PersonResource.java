package com.meynier.jakarta.rest;

import com.meynier.jakarta.service.SampleService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/person")
@ApplicationScoped
public class PersonResource {

    @Inject
    SampleService sampleService;

    @GET
    public String getPerson() {
        return sampleService.sayHello();
    }
}
