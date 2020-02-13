package com.meynier.jakarta.event;

import java.util.UUID;

public class StockEvent {

    private final ShopTransactionType transactionType;
    private final float total;
    private final String uuid;


    public StockEvent(ShopTransactionType transactionType, float total) {
        this.transactionType = transactionType;
        this.total = total;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public ShopTransactionType getTransactionType() {
        return transactionType;
    }

    public float getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "StockEvent{" +
                "transactionType=" + transactionType +
                ", total=" + total +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
