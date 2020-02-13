package com.meynier.jakarta.rest;


import com.meynier.jakarta.event.StockEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/shop")
@ApplicationScoped
public class ShopResource {

    private static final Logger LOGGER = Logger.getLogger(ShopResource.class.getName());

    private OutboundSseEvent.Builder eventBuilder;
    private final Map<String,BlockingQueue<StockEvent>> eventsMap = new Hashtable<>();

    @Context
    public void setSse(Sse sse) {
        this.eventBuilder = sse.newEventBuilder();
    }

    public void getStockEvent(@ObservesAsync StockEvent event) {
        eventsMap.forEach((key, value) -> value.add(event));
    }

    @GET
    @Path("stock")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getStockPrices(@Context SseEventSink sseEventSink) {
        String userSession = UUID.randomUUID().toString();
        eventsMap.put(userSession, new ArrayBlockingQueue<>(20));
        try{
            StockEvent poll;
            while(Objects.nonNull(poll = eventsMap.get(userSession).poll(5, TimeUnit.SECONDS))){
                    OutboundSseEvent sseEvent = this.eventBuilder
                            .name("stock")
                            .id(poll.getUuid())
                            .mediaType(MediaType.APPLICATION_JSON_TYPE)
                            .data(StockEvent.class, poll)
                            .reconnectDelay(3000)
                            .comment("price change")
                            .build();
                    sseEventSink.send(sseEvent);
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE,e.getMessage(),e);
        }finally {
            eventsMap.remove(userSession);
            sseEventSink.close();
        }

    }

}
