package com.meynier.jakarta.config;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyVisibilityStrategy;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Provider
public class JSONConfigurator implements ContextResolver {

    private class PrivateVisibilityStrategy implements PropertyVisibilityStrategy {
        @Override
        public boolean isVisible(Field field) {
            return true;
        }
        @Override
        public boolean isVisible(Method method) {
            return false;
        }
    }

    @Override
    public Jsonb getContext(Class type) {
        JsonbConfig config = new JsonbConfig().
                withPropertyVisibilityStrategy(new PrivateVisibilityStrategy());
        return JsonbBuilder.newBuilder().
                withConfig(config).
                build();
    }
}