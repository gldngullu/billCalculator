package com.website.billCalculator.service;

import com.website.billCalculator.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BillCalculatorService {

    public ArrayList<Bill> createExampleBills() {

        Item phone1 = new Item("Kelly103", ItemType.PHONE, 1230.90);
        Item phone2 = new Item("CoolPhone", ItemType.PHONE, 3000.00);
        Item laptop1 = new Item("Hello10", ItemType.LAPTOP, 4600.50);
        Item laptop2 = new Item("Leopard", ItemType.LAPTOP, 2700.00);
        Item keyboard1 = new Item("Typo99", ItemType.KEYBOARD, 150.75);
        Item keyboard2 = new Item("Buttons", ItemType.KEYBOARD, 70.90);
        Item monitor1 = new Item("Colors2", ItemType.MONITOR, 500.63);
        Item monitor2 = new Item("IsThisReal", ItemType.MONITOR, 840.90);

        User user1 = new User("Cem", SpecialCard.GOLDEN_CARD, false, LocalDate.of(2016, Month.MARCH, 15));
        User user2 = new User("Thomas", SpecialCard.SILVER_CARD, true, LocalDate.of(2020, Month.SEPTEMBER, 10));
        User user3 = new User("Amy", SpecialCard.NONE, false, LocalDate.of(2015, Month.DECEMBER, 2));
        User user4 = new User("Jessica", SpecialCard.SILVER_CARD, true, LocalDate.of(2020, Month.MAY, 26));

        ArrayList<Item> listOfItems1 = new ArrayList<>(Arrays.asList(phone1, laptop1));
        ArrayList<Item> listOfItems2 = new ArrayList<>(Arrays.asList(phone2, laptop2, phone1));
        ArrayList<Item> listOfItems3 = new ArrayList<>(Arrays.asList(keyboard1, laptop1));
        ArrayList<Item> listOfItems4 = new ArrayList<>(Arrays.asList(monitor1, laptop2, keyboard2, monitor2));

        Bill bill1 = new Bill(user1, listOfItems1);
        Bill bill2 = new Bill(user2, listOfItems2);
        Bill bill3 = new Bill(user3, listOfItems3);
        Bill bill4 = new Bill(user4, listOfItems4);
        return new ArrayList<>(Arrays.asList(bill1, bill2, bill3, bill4));
    }

    public double calculateBillCostWithoutDiscount(List<Item> listOfItems) {
        double cost = 0;
        for (Item item : listOfItems) {
            cost += item.getCost();
        }
        return cost;
    }

    public double calculateDiscount(CalculatedBill calculatedBill) {
        double totalDiscount = 0;

        //Calculating the percentage discount
        double percentageDiscountAmount = determinePercentageDiscount(calculatedBill.getUser());
        if (percentageDiscountAmount != 0) {
            double percentageDiscount = calculatedBill.getCost();
            for (Item item : calculatedBill.getListOfItems()) {
                if (item.getItemType() == ItemType.PHONE)
                    percentageDiscount -= item.getCost();
            }
            percentageDiscount *= percentageDiscountAmount;
            totalDiscount += percentageDiscount;
        }

        //Calculating discount for every 200$
        double discountForEvery200 = ((int) (calculatedBill.getCost() - totalDiscount) / 200) * 5;
        totalDiscount += discountForEvery200;

        return totalDiscount;
    }

    public double determinePercentageDiscount(User user) {
        if (user.getUserCard() == SpecialCard.GOLDEN_CARD ){
            return 0.3;
        }else if (user.getUserCard() == SpecialCard.SILVER_CARD) {
            return 0.2;
        }else if (user.isAffiliate()) {
            return 0.1;
        }else if ((ChronoUnit.YEARS.between(user.getSubscriptionDate(), LocalDate.now())) >= 2) {
            return 0.05;
        }else {
            return 0;
        }
    }

    public CalculatedBill calculateBill(Bill bill) {
        CalculatedBill calculatedBill = new CalculatedBill(bill.getUser(), bill.getListOfItems());

        Double cost = calculateBillCostWithoutDiscount(calculatedBill.getListOfItems());
        calculatedBill.setCost(cost);
        Double discount = calculateDiscount(calculatedBill);
        calculatedBill.setDiscount(discount);
        calculatedBill.setNetCost(cost - discount);

        return calculatedBill;
    }
}
