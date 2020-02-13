package com.meynier.jakarta.service;

import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.domain.Shop;
import com.meynier.jakarta.domain.Stock;
import com.meynier.jakarta.exception.NotEnoughFishException;
import com.meynier.jakarta.exception.NotEnoughMoneyException;
import com.meynier.jakarta.repository.ShopRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@ApplicationScoped
public class FishServiceImpl implements FishService {

    @Inject
    private ShopRepository shopRepository;

    public int countByType(String fishFamily) {
        return shopRepository.countFishByFamily(fishFamily);
    }

    @Transactional
    public float buy(String shopName, String fishName, int quantity) {
        Shop shop = shopRepository.findShopByName(shopName);
        Fish fish = shopRepository.findFishByName(fishName);
        Stock stock = shopRepository.findStock(shopName, fishName);

        float price = quantity * fish.getPrice();
        if(shop.getAccount() < price){
            throw new NotEnoughMoneyException();
        }
        shop.setAccount(shop.getAccount() - price);
        stock.setQuantity(stock.getQuantity() + quantity);
        shopRepository.saveStock(stock);
        shopRepository.saveShop(shop);

        return price;
    }

    @Transactional
    public float sell(String shopName, String fishName, int quantity) {
        System.out.println(shopName);
        System.out.println(fishName);
        System.out.println(quantity);
        Shop shop = shopRepository.findShopByName(shopName);
        Fish fish = shopRepository.findFishByName(fishName);
        Stock stock = shopRepository.findStock(shopName, fishName);

        float price = quantity * fish.getPrice();
        if(stock.getQuantity() < quantity){
            throw new NotEnoughFishException();
        }
        shop.setAccount(shop.getAccount() + price);
        stock.setQuantity(stock.getQuantity() - quantity);
        shopRepository.saveStock(stock);
        shopRepository.saveShop(shop);

        return price;
    }

}
