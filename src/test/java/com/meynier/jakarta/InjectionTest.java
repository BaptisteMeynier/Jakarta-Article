package com.meynier.jakarta;

import com.meynier.jakarta.domain.*;
import com.meynier.jakarta.domain.enums.WaterType;
import com.meynier.jakarta.exception.NotEnoughFishException;
import com.meynier.jakarta.exception.NotEnoughMoneyException;
import com.meynier.jakarta.repository.ShopRepository;
import com.meynier.jakarta.service.FishService;
import com.meynier.jakarta.utils.EntityManagerHK2Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@DisplayName("Programmaticaly Injection Unit Tests")
public class InjectionTest {

    private static ServiceLocator serviceLocator;

    @BeforeAll
    public static void setUpBeforeClass() {
        serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServiceLocatorUtilities.addClasses(
                serviceLocator,
                EntityManagerHK2Factory.class,
                FishService.class,
                ShopRepository.class,
                Family.class, Fish.class, FishStockKey.class, Shop.class, Stock.class, WaterType.class);
    }

    @Test
    public void it_should_sell_fish() {
        //GIVEN
        FishService fishService = serviceLocator.getService(FishService.class);
        String shopName = "Magic Fish";
        String fishName = "Scalaire";
        int quantity = 3;
        //WHEN
        float bill = fishService.sell(shopName, fishName, quantity);
        //THEN
        assertThat(bill, is(54f));
    }

    @Test
    public void it_should_throw_exception_when_sell_too_much_fish() {
        //GIVEN
        FishService fishService = serviceLocator.getService(FishService.class);
        String shopName = "Magic Fish";
        String fishName = "Discus";
        int quantity = 300;
        //WHEN //THEN
        Assertions.assertThrows(NotEnoughFishException.class, () -> fishService.sell(shopName, fishName, quantity));
    }

    @Test
    public void it_should_buy_fish() {
        //GIVEN
        FishService fishService = serviceLocator.getService(FishService.class);
        String shopName = "Magic Fish";
        String fishName = "Nez rouge";
        int quantity = 100;
        //WHEN
        float bill = fishService.buy(shopName, fishName, quantity);
        //THEN
        assertThat(bill, is(40f));
    }

    @Test
    public void it_should_throw_exception_when_buy_too_much__expensive_fish() {
        //GIVEN
        FishService fishService = serviceLocator.getService(FishService.class);
        String shopName = "Magic Fish";
        String fishName = "Requin marteau";
        int quantity = 1;
        //WHEN //THEN
        Assertions.assertThrows(NotEnoughMoneyException.class, () -> fishService.buy(shopName, fishName, quantity));
    }

}
