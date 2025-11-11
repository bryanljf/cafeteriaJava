package com.cafeteria.decorator;

import com.cafeteria.model.Product;

public class ExtraMilk extends ProductDecorator {
    private static final double EXTRA_COST = 1.50;

    public ExtraMilk(Product product) {
        super(product);
    }

    @Override
    public String getName() {
        return product.getName() + " + Leite Extra";
    }

    @Override
    public double getPrice() {
        return product.getPrice() + EXTRA_COST;
    }
}