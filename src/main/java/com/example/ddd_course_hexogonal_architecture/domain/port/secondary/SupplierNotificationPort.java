package com.example.ddd_course_hexogonal_architecture.domain.port.secondary;

import com.example.ddd_course_hexogonal_architecture.domain.model.Order;

public interface SupplierNotificationPort {
    void notifySupplier(Order order);
}