package com.meynier.jakarta.rest.application;

import com.meynier.jakarta.rest.FishResource;
import com.meynier.jakarta.rest.ShopResource;
import com.meynier.jakarta.rest.exceptionMapper.NoResultExceptionMapper;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationPath("/petshop")
public class PetShopApplication extends Application {}
