package com.example.ddd_course_hexogonal_architecture.adapter.secondary.notification;

import com.example.ddd_course_hexogonal_architecture.domain.model.Order;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.SupplierNotificationPort;

public class ConsoleNotificationAdapter implements SupplierNotificationPort {
    public void notifySupplier(Order o) {
        System.out.println("[Уведомление] Заказ отправлен: " + o.getId());
    }
}