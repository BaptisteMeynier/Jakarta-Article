package com.meynier.jakarta.service;

public interface FishService {

    int countByFamily(String fishFamily);

    float buy(String shopName, String fishName, int quantity);

    float sell(String shopName, String fishName, int quantity);
}
