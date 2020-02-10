package com.meynier.jakarta.rest;


import com.meynier.jakarta.event.StockEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.ObservesAsync;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Path("/shop")
@RequestScoped
public class ShopResource {

    private OutboundSseEvent.Builder eventBuilder;
    private final BlockingQueue<StockEvent> stockEvent = new ArrayBlockingQueue<>(10);

    @Context
    public void setSse(Sse sse) {
        this.eventBuilder = sse.newEventBuilder();
    }

    public void getStockEvent(@ObservesAsync StockEvent event) {
        stockEvent.add(event);
    }

    @GET
    @Path("stock")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getStockPrices(@Context SseEventSink sseEventSink) throws InterruptedException {
        StockEvent poll;
        while(Objects.nonNull(poll = stockEvent.poll(5, TimeUnit.SECONDS))){
                OutboundSseEvent sseEvent = this.eventBuilder
                        .name("stock")
                        //.id(String.valueOf(lastEventId))
                        .mediaType(MediaType.APPLICATION_JSON_TYPE)
                        .data(StockEvent.class, poll)
                        .reconnectDelay(3000)
                        .comment("price change")
                        .build();
                sseEventSink.send(sseEvent);
        }
        sseEventSink.close();
    }
}
