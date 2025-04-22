package com.example.ddd_course_hexogonal_architecture.adapter.primary.console;

import com.example.ddd_course_hexogonal_architecture.domain.model.Forecast;
import com.example.ddd_course_hexogonal_architecture.domain.model.Order;
import com.example.ddd_course_hexogonal_architecture.domain.model.OrderStatus;
import com.example.ddd_course_hexogonal_architecture.domain.model.ProductForecast;
import com.example.ddd_course_hexogonal_architecture.domain.port.primary.OrderServicePort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            try {
                handle(choice);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n=== Управление заказами поставщикам ===");
        System.out.println("1. Создать заказ по прогнозу");
        System.out.println("2. Отправить заказ");
        System.out.println("3. Подтвердить заказ");
        System.out.println("4. Отметить заказ в пути");
        System.out.println("5. Проверить статус заказа");
        System.out.println("6. Принять поставку и провести контроль качества");
        System.out.println("7. Показать все заказы");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handle(int choice) {
        switch (choice) {
            case 1: createOrder(); break;
            case 2: sendOrder(); break;
            case 3: confirmOrder(); break;
            case 4: markInTransit(); break;
            case 5: checkStatus(); break;
            case 6: receiveDelivery(); break;
            case 7: listAllOrders(); break;
            case 0: System.out.println("Выход из приложения."); break;
            default: System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void createOrder() {
        System.out.print("Идентификатор прогноза: ");
        String forecastId = scanner.nextLine();
        System.out.print("Количество позиций: ");
        int n = Integer.parseInt(scanner.nextLine());
        List<ProductForecast> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Идентификатор продукта: ");
            String pid = scanner.nextLine();
            System.out.print("Количество: ");
            int qty;
            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Введите корректное количество: ");
                    continue;
                }
                try {
                    qty = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат числа. Повторите ввод: ");
                }
            }
            items.add(new ProductForecast(pid, qty));
        }
        Order order = service.createOrder(new Forecast(forecastId, items));
        System.out.println("Заказ создан, его ID: " + order.getOrderId());
    }

    private void sendOrder() {
        System.out.print("Идентификатор заказа: ");
        String id = scanner.nextLine();
        service.sendOrder(id);
        System.out.println("Заказ отправлен поставщику.");
    }

    private void confirmOrder() {
        System.out.print("Идентификатор заказа: ");
        String id = scanner.nextLine();
        service.receiveConfirmation(id);
        System.out.println("Заказ подтверждён поставщиком.");
    }

    private void markInTransit() {
        System.out.print("Идентификатор заказа: ");
        String id = scanner.nextLine();
        service.markInTransit(id);
        System.out.println("Заказ находится в пути.");
    }

    private void checkStatus() {
        System.out.print("Идентификатор заказа: ");
        String id = scanner.nextLine();
        OrderStatus status = service.getStatus(id);
        System.out.println("Текущий статус заказа: " + status);
    }

    private void receiveDelivery() {
        System.out.print("Идентификатор заказа: ");
        String id = scanner.nextLine();
        System.out.print("Качество в порядке? (true/false): ");
        boolean ok = Boolean.parseBoolean(scanner.nextLine());
        service.acceptDelivery(id, ok);
        System.out.println(ok ? "Поставка принята и качество подтверждено." : "Поставка возвращена из‑за проблем с качеством.");
    }

    private void listAllOrders() {
        List<Order> orders = service.listOrders();
        if (orders.isEmpty()) {
            System.out.println("Нет созданных заказов.");
        } else {
            System.out.println("\n=== Все заказы ===");
            for (Order o : orders) {
                System.out.println(o);
            }
        }
    }
}