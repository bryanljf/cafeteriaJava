package com.cafeteria;

import com.cafeteria.decorator.ExtraMilk;
import com.cafeteria.decorator.WhippedCream;
import com.cafeteria.factory.ProductFactory;
import com.cafeteria.factory.ProductType;
import com.cafeteria.model.Order;
import com.cafeteria.model.Product;
import com.cafeteria.service.InventoryService;
import com.cafeteria.service.OrderService;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static OrderService orderService;
    private static InventoryService inventory;

    public static void main(String[] args) {
        initializeServices();
        displayWelcome();

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    displayStock();
                    break;
                case 0:
                    running = false;
                    displayGoodbye();
                    break;
                default:
                    System.out.println("\n⚠ Opção inválida!\n");
            }
        }

        scanner.close();
    }

    private static void initializeServices() {
        // DEPENDENCY INJECTION: Injetando implementação do notificador
        OrderService.NotificationService notifier = message ->
                System.out.println("\n>>> 🔔 COZINHA: " + message);

        // SINGLETON: Instâncias únicas
        orderService = OrderService.getInstance(notifier);
        inventory = InventoryService.getInstance();
    }

    private static void displayWelcome() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║   BEM-VINDO À CAFETERIA CENTRAL!      ║");
        System.out.println("║     Sistema de Auto-Atendimento       ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    private static void displayMainMenu() {
        System.out.println("\n┌───────────────────────────────────────┐");
        System.out.println("│ [1] Fazer Pedido                      │");
        System.out.println("│ [2] Ver Estoque                       │");
        System.out.println("│ [0] Sair                              │");
        System.out.println("└───────────────────────────────────────┘");
        System.out.print("→ Escolha: ");
    }

    private static void createOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            displayMenu();
            int choice = readInt();

            if (choice == 0) {
                ordering = false;
            } else {
                ProductType type = getProductType(choice);
                if (type != null) {
                    addProductToOrder(order, type);
                } else {
                    System.out.println("\n⚠ Opção inválida!\n");
                }
            }
        }

        orderService.processOrder(order);
    }

    private static void displayMenu() {
        System.out.println("\n╔════════════════ CARDÁPIO ═════════════════╗");
        System.out.println("║             BEBIDAS QUENTES               ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ [1] Café Expresso ............ R$  4,50  ║");
        System.out.println("║ [2] Cappuccino ............... R$  6,50  ║");
        System.out.println("║ [3] Café Latte ............... R$  6,00  ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║               COMIDAS                     ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ [4] Bolo de Chocolate ........ R$  8,00  ║");
        System.out.println("║ [5] Croissant ................ R$  5,50  ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ [0] Finalizar Pedido                      ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.print("→ Escolha: ");
    }

    private static void addProductToOrder(Order order, ProductType type) {
        if (!inventory.isAvailable(type)) {
            System.out.println("\n⚠ Produto indisponível no estoque!\n");
            return;
        }

        // FACTORY: Criando produto
        Product product = ProductFactory.create(type);

        // DECORATOR: Aplicar apenas em bebidas
        if (type.getCategory().equals("BEBIDA")) {
            product = applyDecorators(product);
        }

        inventory.withdraw(type);
        order.add(product);
        System.out.println("\n✓ " + product.getName() + " adicionado!\n");
    }

    private static Product applyDecorators(Product product) {
        System.out.println("\n┌─── Complementos Disponíveis ───┐");
        System.out.println("│ [1] Leite Extra (+R$ 1,50)    │");
        System.out.println("│ [2] Chantilly (+R$ 2,00)      │");
        System.out.println("│ [0] Sem complementos           │");
        System.out.println("└────────────────────────────────┘");
        System.out.print("→ Escolha: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                return new ExtraMilk(product);
            case 2:
                return new WhippedCream(product);
            default:
                return product;
        }
    }

    private static void displayStock() {
        System.out.println("\n╔════════════ ESTOQUE ATUAL ════════════╗");
        for (ProductType type : ProductType.values()) {
            int qty = inventory.getStock(type);
            String status = qty > 5 ? "✓" : (qty > 0 ? "⚠" : "✗");
            System.out.printf("║ %s %-20s %3d unidades  ║%n",
                    status, type.name(), qty);
        }
        System.out.println("╚═══════════════════════════════════════╝\n");
    }

    private static void displayGoodbye() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║  Obrigado por visitar a Cafeteria!    ║");
        System.out.println("║         Volte sempre! ☕               ║");
        System.out.println("╚═══════════════════════════════════════╝\n");
        System.out.println("Total de pedidos processados: " + orderService.getTotalOrders());
    }

    private static ProductType getProductType(int choice) {
        switch (choice) {
            case 1: return ProductType.ESPRESSO;
            case 2: return ProductType.CAPPUCCINO;
            case 3: return ProductType.LATTE;
            case 4: return ProductType.CAKE;
            case 5: return ProductType.CROISSANT;
            default: return null;
        }
    }

    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}