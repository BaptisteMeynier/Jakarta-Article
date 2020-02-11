package com.meynier.jakarta.utils;

import com.meynier.jakarta.event.StockEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@ApplicationScoped
@Singleton
public class ProducerUtils {

    @Produces
    public Event<StockEvent> getEventListener (){
        return new FakeEventManager();
    }
}
