package com.meynier.jakarta.service;

import com.meynier.jakarta.domain.Family;
import com.meynier.jakarta.domain.Shop;
import com.meynier.jakarta.exception.NotEnoughFish;
import com.meynier.jakarta.exception.NotEnoughMoneyException;
import com.meynier.jakarta.repository.ShopRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@ApplicationScoped
public class FishService {

    @Inject
    private ShopRepository shopRepository;

    public int countByType(String fishFamily) {
        return shopRepository.countFishByFamily(fishFamily);
    }

    @Transactional
    public void buy(String fishFamily, int quantity) {
        Family family = shopRepository.findFamilyByName(fishFamily);
        Shop shop = shopRepository.findMainShop();
        float price = family.getPrice() * quantity;
        if(price > shop.getAccount()){
            throw new NotEnoughMoneyException();
        }
        shopRepository.moneyTransaction(shop, -1 * price);
        shopRepository.buyFish(shop, family);
    }

    public void sell(String fishFamily) {
        Family family = shopRepository.findFamilyByName(fishFamily);
        if(family.getFishs().isEmpty()){
            throw new NotEnoughFish();
        }
        Shop shop = shopRepository.findMainShop();
        shopRepository.moneyTransaction(shop, family.getPrice());
        shopRepository.sellFish(fishFamily);
    }
}
