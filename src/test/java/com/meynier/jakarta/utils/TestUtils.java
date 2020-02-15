package com.meynier.jakarta.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public class TestUtils {

    public static final String PERSISTENCEXML_PROPERTIE = "eclipselink.persistencexml";

    public static EntityManagerFactory getEntityManagerFactory() {
        Properties properties = new Properties();
        properties.put(PERSISTENCEXML_PROPERTIE, "META-INF/persistence-h2.xml");
        return Persistence.createEntityManagerFactory("JPADemo", properties);
    }
}
