package com.example.ddd_course_hexogonal_architecture.adapter.primary.console;

import com.example.ddd_course_hexogonal_architecture.domain.model.*;
import com.example.ddd_course_hexogonal_architecture.domain.port.primary.OrderServicePort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleOrderAdapter {
    private final OrderServicePort service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleOrderAdapter(OrderServicePort service) {
        this.service = service;
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(scanner.nextLine());
            handle(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println();
        System.out.println("=== Управление заказами поставщикам ===");
        System.out.println("1. Создать заказ");
        System.out.println("2. Отправить заказ");
        System.out.println("3. Подтвердить заказ");
        System.out.println("4. Отметить доставку");
        System.out.println("5. Контроль качества");
        System.out.println("6. Проверить статус");
        System.out.println("7. Список всех заказов");
        System.out.println("0. Выход");
        System.out.print("> ");
    }

    private void handle(int choice) {
        switch (choice) {
            case 1:
                createOrder();
                break;
            case 2:
                sendOrder();
                break;
            case 3:
                confirmOrder();
                break;
            case 4:
                deliverOrder();
                break;
            case 5:
                qualityCheck();
                break;
            case 6:
                showStatus();
                break;
            case 7:
                listOrders();
                break;
            case 0:
                System.out.println("Выход из приложения.");
                break;
            default:
                System.out.println("Неверный выбор. Повторите.");
        }
    }

    private void createOrder() {
        List<Supplier> suppliers = List.of(
                new Supplier("1", "Бабушка Зоя (дача)", "zoya1957@example.com"),
                new Supplier("2", "Мясокомбинат 'Потрошки'", "myasoooo@example.com"),
                new Supplier("3", "Пятёрочка просрочка", "5chka@example.com")
        );
        List<Product> products = List.of(
                new Product("P1", "Сыр", "кг"),
                new Product("P2", "Масло", "кг"),
                new Product("P3", "Салат", "шт"),
                new Product("P4", "Помидор", "кг")
        );

        System.out.println("Выберите поставщика:");
        for (int i = 0; i < suppliers.size(); i++) {
            System.out.println((i + 1) + ") " + suppliers.get(i));
        }
        int supIdx = Integer.parseInt(scanner.nextLine()) - 1;
        Supplier chosenSupplier = suppliers.get(supIdx);

        String forecastId = UUID.randomUUID().toString();
        System.out.print("Количество позиций: ");
        int itemCount = Integer.parseInt(scanner.nextLine());

        Forecast forecast = new Forecast(forecastId, chosenSupplier, new ArrayList<>());
        for (int i = 0; i < itemCount; i++) {
            System.out.println("Выберите продукт:");
            for (int j = 0; j < products.size(); j++) {
                System.out.println((j + 1) + ") " + products.get(j));
            }
            int prodIdx = Integer.parseInt(scanner.nextLine()) - 1;
            Product chosenProduct = products.get(prodIdx);
            System.out.print("Количество: ");
            int qty = Integer.parseInt(scanner.nextLine());
            forecast.getItems().add(new LineItem(chosenProduct, qty));
        }

        Order order = service.createOrder(forecast);
        System.out.println("Создан заказ: " + order.getId());
    }


    private void sendOrder() {
        System.out.print("ID заказа: ");
        String id = scanner.nextLine();
        service.sendOrder(id);
        System.out.println("Заказ отправлен поставщику.");
    }

    private void confirmOrder() {
        System.out.print("ID заказа: ");
        String id = scanner.nextLine();
        System.out.print("Код подтверждения: ");
        String code = scanner.nextLine();
        service.confirmOrder(id, code);
        System.out.println("Заказ подтверждён.");
    }

    private void deliverOrder() {
        System.out.print("ID заказа: ");
        String id = scanner.nextLine();
        service.deliverOrder(id);
        System.out.println("Заказ доставлен.");
    }

    private void qualityCheck() {
        System.out.print("ID заказа: ");
        String id = scanner.nextLine();
        System.out.print("Качество OK? (true/false): ");
        boolean passed = Boolean.parseBoolean(scanner.nextLine());
        service.qualityCheck(id, passed);
        System.out.println(passed ? "Заказ принят." : "Заказ возвращён.");
    }

    private void showStatus() {
        System.out.print("ID заказа: ");
        String id = scanner.nextLine();
        Status status = service.getStatus(id);
        System.out.println("Текущий статус: " + status);
    }

    private void listOrders() {
        List<Order> orders = service.listOrders();
        if (orders.isEmpty()) {
            System.out.println("Нет заказов.");
        } else {
            orders.forEach(System.out::println);
        }
    }
}