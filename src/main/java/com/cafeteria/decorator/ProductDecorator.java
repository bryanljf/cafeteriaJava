package com.cafeteria.decorator;

import com.cafeteria.model.Product;

// PADRÃO DECORATOR: Classe base para decoradores
public abstract class ProductDecorator implements Product {
    protected final Product product;

    public ProductDecorator(Product product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

    @Override
    public String getCategory() {
        return product.getCategory();
    }
}