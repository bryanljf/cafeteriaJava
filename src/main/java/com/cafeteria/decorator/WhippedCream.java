package com.cafeteria.decorator;

import com.cafeteria.model.Product;

public class WhippedCream extends ProductDecorator {
    private static final double EXTRA_COST = 2.00;

    public WhippedCream(Product product) {
        super(product);
    }

    @Override
    public String getName() {
        return product.getName() + " + Chantilly";
    }

    @Override
    public double getPrice() {
        return product.getPrice() + EXTRA_COST;
    }
}