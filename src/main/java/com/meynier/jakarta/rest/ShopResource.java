package com.meynier.jakarta.rest;


import com.meynier.jakarta.domain.Stock;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@Path("/shop")
@ApplicationScoped
public class ShopResource {


    private Sse sse;
    private SseBroadcaster sseBroadcaster;
    private OutboundSseEvent.Builder eventBuilder;

    @Context
    public void setSse(Sse sse) {
        this.sse = sse;
        this.eventBuilder = sse.newEventBuilder();
        this.sseBroadcaster = sse.newBroadcaster();
    }

    @GET
    @Path("stock")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getStockPrices(@Context SseEventSink sseEventSink,
                               @HeaderParam(HttpHeaders.LAST_EVENT_ID_HEADER) @DefaultValue("-1") int lastReceivedId) {

        int lastEventId = 1;
        if (lastReceivedId != -1) {
            lastEventId = ++lastReceivedId;
        }
        boolean running = true;
       /* while (running) {
            Stock stock = shopService.getNextTransaction(lastEventId);
            if (stock != null) {
                OutboundSseEvent sseEvent = this.eventBuilder
                        .name("stock")
                        .id(String.valueOf(lastEventId))
                        .mediaType(MediaType.APPLICATION_JSON_TYPE)
                        .data(Stock.class, stock)
                        .reconnectDelay(3000)
                        .comment("price change")
                        .build();
                sseEventSink.send(sseEvent);
                lastEventId++;
            }
            //Simulate connection close
            if (lastEventId % 5 == 0) {
                sseEventSink.close();
                break;
            }

            try {
                //Wait 5 seconds
                Thread.sleep(5 * 1000);
            } catch (InterruptedException ex) {
                // ...
            }
        }*/
        sseEventSink.close();
    }
}
