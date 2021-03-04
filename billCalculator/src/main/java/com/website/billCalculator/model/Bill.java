package com.website.billCalculator.model;

import java.util.List;

public class Bill {
    //Bill without the calculated cost, therefore does not include cost attribute.
    private User user;
    private List<Item> listOfItems;

    public Bill(User user, List<Item> listOfItems) {
        this.user = user;
        this.listOfItems = listOfItems;
    }

    public List<Item> getListOfItems() {
        return listOfItems;
    }

    public User getUser() {
        return user;
    }
}
