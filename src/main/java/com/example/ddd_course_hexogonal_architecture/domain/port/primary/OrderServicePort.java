package com.example.ddd_course_hexogonal_architecture.domain.port.primary;

import com.example.ddd_course_hexogonal_architecture.domain.model.Forecast;
import com.example.ddd_course_hexogonal_architecture.domain.model.Order;
import com.example.ddd_course_hexogonal_architecture.domain.model.OrderStatus;

import java.util.List;

public interface OrderServicePort {
    Order createOrder(Forecast forecast);
    void sendOrder(String orderId);
    void receiveConfirmation(String orderId);
    void markInTransit(String orderId);
    OrderStatus getStatus(String orderId);
    void acceptDelivery(String orderId, boolean qualityOk);
    List<Order> listOrders();
}
