package com.meynier.jakarta.event;

public class StockEvent {

    private TransactionType transactionType;
    private float total;

    public StockEvent(TransactionType transactionType, float total) {
        this.transactionType = transactionType;
        this.total = total;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "StockEvent{" +
                "transactionType=" + transactionType +
                ", total=" + total +
                '}';
    }
}
