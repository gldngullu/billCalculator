package com.website.billCalculator;

import com.website.billCalculator.model.*;
import com.website.billCalculator.service.BillCalculatorService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BillCalculatorServiceTest {
    BillCalculatorService service = new BillCalculatorService();

    @Test
    public void calculateBillCostWithoutDiscountTest(){
        Item phone1 = new Item("Kelly103", ItemType.PHONE, 1230.90);
        Item laptop1 = new Item("Hello10", ItemType.LAPTOP, 4600.50);
        ArrayList<Item> listOfItems1 = new ArrayList<>(Arrays.asList(phone1, laptop1));

        double expected = 5831.4;
        double result = service.calculateBillCostWithoutDiscount(listOfItems1);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void calculateDiscountTest(){
        User user1 = new User("Cem", SpecialCard.GOLDEN_CARD, false, LocalDate.of(2016, Month.MARCH, 15));
        Item phone1 = new Item("Kelly103", ItemType.PHONE, 1230.90);
        Item laptop1 = new Item("Hello10", ItemType.LAPTOP, 4600.50);
        ArrayList<Item> listOfItems1 = new ArrayList<>(Arrays.asList(phone1, laptop1));
        CalculatedBill bill = new CalculatedBill(user1, listOfItems1);
        bill.setCost(service.calculateBillCostWithoutDiscount(listOfItems1));

        double expected = 1490.1499999999999;
        double result = service.calculateDiscount(bill);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void determinePercentageDiscountGoldCardTest(){
        User user = new User("Cem", SpecialCard.GOLDEN_CARD, false, LocalDate.of(2016, Month.MARCH, 15));

        double expected = 0.3;
        double result = service.determinePercentageDiscount(user);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void determinePercentageDiscountSilverCardTest(){
        User user = new User("Thomas", SpecialCard.SILVER_CARD, true, LocalDate.of(2020, Month.SEPTEMBER, 10));

        double expected = 0.2;
        double result = service.determinePercentageDiscount(user);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void determinePercentageDiscountAffiliateTest(){
        User user = new User("Cem", SpecialCard.NONE, true, LocalDate.of(2020, Month.MARCH, 15));

        double expected = 0.1;
        double result = service.determinePercentageDiscount(user);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void determinePercentageDiscountTwoYearsTest(){
        User user = new User("Amy", SpecialCard.NONE, false, LocalDate.of(2015, Month.DECEMBER, 2));

        double expected = 0.05;
        double result = service.determinePercentageDiscount(user);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void determinePercentageDiscountNoDiscountTest(){
        User user = new User("Cem", SpecialCard.NONE, false, LocalDate.of(2021, Month.JANUARY, 8));

        double expected = 0;
        double result = service.determinePercentageDiscount(user);
        assertThat(result, equalTo(expected));
    }



}
