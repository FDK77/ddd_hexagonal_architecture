package com.example.ddd_course_hexogonal_architecture.domain.port.primary;

import com.example.ddd_course_hexogonal_architecture.domain.model.*;

import java.util.List;

public interface OrderServicePort {
    Order createOrder(Forecast f);
    void sendOrder(String orderId);
    void confirmOrder(String orderId, String code);
    void deliverOrder(String orderId);
    void qualityCheck(String orderId, boolean passed);
    Status getStatus(String orderId);
    List<Order> listOrders();
}
