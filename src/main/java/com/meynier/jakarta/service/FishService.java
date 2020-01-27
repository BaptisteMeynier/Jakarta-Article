package com.meynier.jakarta.service;

import com.meynier.jakarta.domain.Family;
import com.meynier.jakarta.exception.NotEnoughMoneyException;
import com.meynier.jakarta.repository.FamilyRepository;
import com.meynier.jakarta.repository.FishRepository;
import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.repository.ShopRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class FishService {

    @Inject
    private ShopRepository shopRepository;
    @Inject
    private FishRepository fishRepository;
    @Inject
    private FamilyRepository familyRepository;

    public List<Fish> findByName(final String name){
        return fishRepository.findByName(name);
    }

    public int countByType(String fishFamily) {
        return fishRepository.countByType(fishFamily);
    }

    @Transactional
    public void buy(String fishFamily, int quantity) {
        Family family = familyRepository.findByName();
        float account = shopRepository.findMoneyForMainShop();
        float total = family.getPrice() * quantity;
        if(total > account){
            throw new NotEnoughMoneyException();
        }
        shopRepository.spendForMainShop(total);
        fishRepository.saveAll(fishFamily, quantity);
    }
}
