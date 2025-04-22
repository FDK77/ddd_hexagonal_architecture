package com.example.ddd_course_hexogonal_architecture.domain.port.secondary;



import com.example.ddd_course_hexogonal_architecture.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(String orderId);
    List<Order> findAll();
}
