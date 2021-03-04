package com.website.billCalculator.model;

import java.util.List;

public class CalculatedBill {
    //Bill including the cost and discounts.
    private User user;
    private Double cost;//Without discount
    private Double discount;
    private Double netCost;//Including discounts
    private List<Item> listOfItems;

    public CalculatedBill(User user, List<Item> listOfItems) {
        this.user = user;
        this.listOfItems = listOfItems;
    }

    public List<Item> getListOfItems() {
        return listOfItems;
    }

    public User getUser() {
        return user;
    }

    public Double getCost() {
        return cost;
    }

    public Double getNetCost() {
        return netCost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setNetCost(Double netCost) {
        this.netCost = netCost;
    }

    @Override
    public String toString() {
        return "User: " + user.getName() + "\n" +
                "Purchased items: " + ItemListToString();
    }

    public String ItemListToString() {
        StringBuilder itemList = new StringBuilder();
        for (Item item : listOfItems) {
            itemList.append("[Item:" + item.getName() + " -ItemType:" + item.getItemType() + " -Cost:" + item.getCost() + "]");
        }
        return itemList.toString();
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
