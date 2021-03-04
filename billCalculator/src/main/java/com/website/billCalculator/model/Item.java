package com.website.billCalculator.model;

public class Item {

    private String name;
    private ItemType itemType;
    private double cost;

    public Item(String name, ItemType itemType, double cost) {
        this.name = name;
        this.itemType = itemType;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
