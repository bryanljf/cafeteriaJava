package com.cafeteria.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int counter = 0;
    private final int id;
    private final List<Product> items = new ArrayList<>();

    public Order() {
        this.id = ++counter;
    }

    public void add(Product product) {
        items.add(product);
    }

    public double getTotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\n╔═══════════════════════════════════╗\n");
        sb.append("║       PEDIDO #").append(String.format("%03d", id)).append("              ║\n");
        sb.append("╠═══════════════════════════════════╣\n");

        items.forEach(p -> {
            sb.append(String.format("║ %-25s R$ %5.2f ║%n",
                    p.getName(), p.getPrice()));
        });

        sb.append("╠═══════════════════════════════════╣\n");
        sb.append(String.format("║ TOTAL:                R$ %6.2f ║%n", getTotal()));
        sb.append("╚═══════════════════════════════════╝\n");

        return sb.toString();
    }
}