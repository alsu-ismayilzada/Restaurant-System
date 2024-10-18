package com.example.restaurantsystem.repository;

public enum ItemType {
    BREAKFAST("Breakfast"),
    MEAL("Meal"),
    BEVERAGE("Beverage"),
    DESSERT("Dessert"),
    FASTFOOD("Fastfood");

    String itemType;

    ItemType (String itemType){
        this.itemType = itemType;
    }
    public String getItemType() {
        return itemType;
    }
}
