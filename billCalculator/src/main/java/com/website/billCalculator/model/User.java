package com.website.billCalculator.model;

import java.time.LocalDate;


public class User {

    private String name;
    private SpecialCard userCard;
    private boolean isAffiliate;
    private LocalDate subscriptionDate;


    public User(String name, SpecialCard userCard, boolean isAffiliate, LocalDate subscriptionDate) {
        this.name = name;
        this.userCard = userCard;
        this.isAffiliate = isAffiliate;
        this.subscriptionDate = subscriptionDate;
    }

    public SpecialCard getUserCard() {
        return userCard;
    }

    public String getName() {
        return name;
    }

    public boolean isAffiliate() {
        return isAffiliate;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }
}
