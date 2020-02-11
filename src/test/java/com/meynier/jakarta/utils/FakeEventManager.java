package com.meynier.jakarta.utils;

import com.meynier.jakarta.event.StockEvent;
import org.jboss.weld.event.EventImpl;

import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.enterprise.util.TypeLiteral;
import java.lang.annotation.Annotation;
import java.util.concurrent.CompletionStage;

public class FakeEventManager implements Event<StockEvent> {
    @Override
    public void fire(StockEvent event) {

    }

    @Override
    public <U extends StockEvent> CompletionStage<U> fireAsync(U event) {
        return null;
    }

    @Override
    public <U extends StockEvent> CompletionStage<U> fireAsync(U event, NotificationOptions options) {
        return null;
    }

    @Override
    public Event<StockEvent> select(Annotation... qualifiers) {
        return null;
    }

    @Override
    public <U extends StockEvent> Event<U> select(Class<U> subtype, Annotation... qualifiers) {
        return null;
    }

    @Override
    public <U extends StockEvent> Event<U> select(TypeLiteral<U> subtype, Annotation... qualifiers) {
        return null;
    }
}
