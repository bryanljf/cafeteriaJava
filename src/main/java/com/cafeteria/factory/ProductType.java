package com.cafeteria.factory;

public enum ProductType {
    ESPRESSO("BEBIDA"),
    CAPPUCCINO("BEBIDA"),
    LATTE("BEBIDA"),
    CAKE("COMIDA"),
    CROISSANT("COMIDA");

    private final String category;

    ProductType(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}