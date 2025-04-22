package com.example.ddd_course_hexogonal_architecture.adapter.secondary.notification;

import com.example.ddd_course_hexogonal_architecture.domain.model.Order;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.SupplierNotificationPort;

public class ConsoleNotificationAdapter implements SupplierNotificationPort {
    @Override
    public void notifySupplier(Order order) {
        System.out.println("[NOTIFY] Supplier notified for order " + order.getOrderId());
    }
}