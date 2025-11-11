package com.cafeteria.service;

import com.cafeteria.model.Order;

// PADRÃO SINGLETON + DEPENDENCY INJECTION
public class OrderService {
    private static OrderService instance;
    private final NotificationService notifier;
    private int totalOrders = 0;

    // Construtor privado com DI
    private OrderService(NotificationService notifier) {
        this.notifier = notifier;
    }

    public static synchronized OrderService getInstance(NotificationService notifier) {
        if (instance == null) {
            instance = new OrderService(notifier);
        }
        return instance;
    }

    public void processOrder(Order order) {
        if (order.isEmpty()) {
            System.out.println("\n⚠ Pedido vazio não processado.\n");
            return;
        }

        totalOrders++;
        System.out.println(order);
        notifier.sendNotification("Pedido #" + order.getId() + " enviado para cozinha!");
        System.out.println("✓ Pedido confirmado! Aguarde o preparo.\n");
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    // Interface para DI
    public interface NotificationService {
        void sendNotification(String message);
    }
}