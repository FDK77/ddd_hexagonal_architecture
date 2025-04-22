package com.example.ddd_course_hexogonal_architecture.domain.service;



import com.example.ddd_course_hexogonal_architecture.domain.model.Forecast;
import com.example.ddd_course_hexogonal_architecture.domain.model.Order;
import com.example.ddd_course_hexogonal_architecture.domain.model.OrderStatus;
import com.example.ddd_course_hexogonal_architecture.domain.model.ProductForecast;
import com.example.ddd_course_hexogonal_architecture.domain.port.primary.OrderServicePort;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.OrderRepository;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.SupplierNotificationPort;

import java.util.List;
import java.util.Optional;

public class OrderService implements OrderServicePort {
    private final OrderRepository repo;
    private final SupplierNotificationPort notifier;

    public OrderService(OrderRepository repo, SupplierNotificationPort notifier) {
        this.repo = repo;
        this.notifier = notifier;
    }

    @Override
    public Order createOrder(Forecast forecast) {
        Order order = new Order(forecast.getItems());
        repo.save(order);
        return order;
    }

    @Override
    public void sendOrder(String orderId) {
        Order order = repo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.send();
        repo.save(order);
        notifier.notifySupplier(order);
    }

    @Override
    public void receiveConfirmation(String orderId) {
        Order order = repo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.confirm();
        repo.save(order);
    }

    @Override
    public void markInTransit(String orderId) {
        Order order = repo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.markInTransit();
        repo.save(order);
    }

    @Override
    public OrderStatus getStatus(String orderId) {
        return repo.findById(orderId)
                .map(Order::getStatus)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }

    @Override
    public void acceptDelivery(String orderId, boolean qualityOk) {
        Order order = repo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.receive();
        order.qualityCheck(qualityOk);
        repo.save(order);
    }

    @Override
    public List<Order> listOrders() {
        return repo.findAll();
    }
}