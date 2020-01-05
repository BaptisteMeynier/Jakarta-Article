package com.meynier.jakarta.service;

import javax.inject.Named;

@Named
public class SampleService {
    public String sayHello(){
        return "Hello";
    }
}
