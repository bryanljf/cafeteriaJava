package com.cafeteria.factory;

import com.cafeteria.model.BaseProduct;
import com.cafeteria.model.Product;

// PADRÃO FACTORY: Centraliza criação de produtos
public class ProductFactory {

    public static Product create(ProductType type) {
        switch (type) {
            case ESPRESSO:
                return new BaseProduct("Café Expresso", 4.50, "BEBIDA");

            case CAPPUCCINO:
                return new BaseProduct("Cappuccino", 6.50, "BEBIDA");

            case LATTE:
                return new BaseProduct("Café Latte", 6.00, "BEBIDA");

            case CAKE:
                return new BaseProduct("Bolo de Chocolate", 8.00, "COMIDA");

            case CROISSANT:
                return new BaseProduct("Croissant", 5.50, "COMIDA");

            default:
                throw new IllegalArgumentException("Tipo inválido: " + type);
        }
    }
}