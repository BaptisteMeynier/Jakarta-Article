package com.meynier.jakarta.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class SampleService {
    public String sayHello(){
        return "Hello World";
    }
}
