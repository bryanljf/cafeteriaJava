package com.cafeteria.service;

import com.cafeteria.factory.ProductType;
import java.util.HashMap;
import java.util.Map;

// PADRÃO SINGLETON: Controle único de estoque
public class InventoryService {
    private static InventoryService instance;
    private final Map<ProductType, Integer> stock = new HashMap<>();

    private InventoryService() {
        initStock();
    }

    public static synchronized InventoryService getInstance() {
        if (instance == null) {
            instance = new InventoryService();
        }
        return instance;
    }

    private void initStock() {
        stock.put(ProductType.ESPRESSO, 20);
        stock.put(ProductType.CAPPUCCINO, 15);
        stock.put(ProductType.LATTE, 15);
        stock.put(ProductType.CAKE, 10);
        stock.put(ProductType.CROISSANT, 12);
    }

    public boolean isAvailable(ProductType type) {
        return stock.getOrDefault(type, 0) > 0;
    }

    public boolean withdraw(ProductType type) {
        if (isAvailable(type)) {
            stock.put(type, stock.get(type) - 1);
            return true;
        }
        return false;
    }

    public int getStock(ProductType type) {
        return stock.getOrDefault(type, 0);
    }
}