package com.meynier.jakarta;

import com.meynier.jakarta.domain.Fish;
import com.meynier.jakarta.domain.Shop;
import com.meynier.jakarta.domain.Stock;
import com.meynier.jakarta.repository.ShopRepository;
import com.meynier.jakarta.service.FishServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @InjectMocks
    private FishServiceImpl fishService;

    @Mock
    ShopRepository shopRepository;

    @Test
    public void it_should_sell_fish(){
        //GIVEN
        String shopName = "Magic Fish";
        String fishName = "Scalaire";
        int quantity = 3;
        Shop shop = new Shop();
        shop.setAccount(1000);
        Fish fish = new Fish();
        fish.setName(fishName);
        fish.setPrice(5);
        Stock stock = new Stock();
        stock.setShop(shop);
        stock.setFish(fish);
        stock.setQuantity(12);
        when(shopRepository.findShopByName(any())).thenReturn(shop);
        when(shopRepository.findFishByName(any())).thenReturn(fish);
        when(shopRepository.findStock(any(),any())).thenReturn(stock);
        doNothing().when(shopRepository).saveStock(any());
        doNothing().when(shopRepository).saveShop(any());
        //WHEN
        float bill = fishService.sell(shopName, fishName, quantity);
        //THEN
        verify(shopRepository,times(1)).saveShop(any());
        assertThat(bill, is(15f));
    }


}
