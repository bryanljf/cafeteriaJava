package com.cafeteria.model;

public class BaseProduct implements Product {
    private final String name;
    private final double price;
    private final String category;

    public BaseProduct(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getCategory() {
        return category;
    }
}