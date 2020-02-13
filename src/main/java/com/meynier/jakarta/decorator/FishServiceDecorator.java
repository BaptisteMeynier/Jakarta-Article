package com.meynier.jakarta.decorator;

import com.meynier.jakarta.event.ShopTransactionType;
import com.meynier.jakarta.event.StockEvent;
import com.meynier.jakarta.service.FishService;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class FishServiceDecorator implements FishService {

    @Inject
    private Event<StockEvent> stockEvent;

    @Inject
    @Delegate
    @Any
    FishService fishService;

    @Override
    public float buy(String shopName, String fishName, int quantity) {
        float price = fishService.buy(shopName, fishName, quantity);
        stockEvent.fireAsync(new StockEvent(ShopTransactionType.PURCHASE,price));
        return price;
    }

    @Override
    public float sell(String shopName, String fishName, int quantity) {
        float price = fishService.sell(shopName, fishName, quantity);
        stockEvent.fireAsync(new StockEvent(ShopTransactionType.SALE,price));
        return price;
    }
}
