# ☕ Sistema de Cafeteria - Auto Atendimento

Sistema de pedidos para cafeteria desenvolvido como trabalho prático de Padrões de Projeto da Universidade Positivo.

---

## 📋 Sobre o Projeto

Sistema que simula um painel de auto-atendimento onde clientes podem:
- Escolher produtos do cardápio
- Adicionar complementos às bebidas
- Visualizar estoque disponível
- Processar pedidos automaticamente

---

## 🎯 Padrões Implementados

| Padrão | Classe | Objetivo |
|--------|--------|----------|
| **Singleton** | `InventoryService`, `OrderService` | Instância única de estoque e pedidos |
| **Dependency Injection** | `OrderService` | Desacoplamento via interface |
| **Factory** | `ProductFactory` | Criação centralizada de produtos |
| **Decorator** | `ExtraMilk`, `WhippedCream` | Complementos dinâmicos em bebidas |

---

## 🏗️ Arquitetura
```
com.cafeteria/
├── model/              # Entidades de domínio
│   ├── Product.java
│   ├── BaseProduct.java
│   └── Order.java
├── factory/            # Padrão Factory
│   ├── ProductType.java
│   └── ProductFactory.java
├── decorator/          # Padrão Decorator
│   ├── ProductDecorator.java
│   ├── ExtraMilk.java
│   └── WhippedCream.java
├── service/            # Singleton + DI
│   ├── InventoryService.java
│   └── OrderService.java
└── Main.java           # Ponto de entrada
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior

### Compilar
```bash
cd src
javac com/cafeteria/**/*.java com/cafeteria/*.java
```

### Executar
```bash
java com.cafeteria.Main
```

---

## 🔧 Princípios SOLID

- ✅ **SRP** - Cada classe tem responsabilidade única
- ✅ **OCP** - Extensível via Decorator sem modificar código base
- ✅ **DIP** - Dependências de abstrações, não implementações

---

## 📊 Entidades Principais

- **Product** - Interface de produtos do cardápio
- **Order** - Agregação de produtos em um pedido
- **InventoryService** - Controle de estoque (Singleton)
- **OrderService** - Processamento de pedidos (Singleton + DI)

---

## 💡 Funcionalidades

- [x] Menu interativo via console
- [x] Cardápio com bebidas e comidas
- [x] Complementos exclusivos para bebidas
- [x] Controle de estoque em tempo real
- [x] Notificação automática para cozinha
- [x] Validação de disponibilidade
- [x] Formatação visual de pedidos

---
