package com.example.ddd_course_hexogonal_architecture.domain.service;



import com.example.ddd_course_hexogonal_architecture.domain.model.*;
import com.example.ddd_course_hexogonal_architecture.domain.port.primary.OrderServicePort;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.OrderRepository;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.SupplierNotificationPort;

import java.util.List;

public class OrderService implements OrderServicePort {
    private final OrderRepository repo;
    private final SupplierNotificationPort notifier;
    public OrderService(OrderRepository repo, SupplierNotificationPort notifier) {
        this.repo = repo;
        this.notifier = notifier;
    }
    public Order createOrder(Forecast f) {
        Order so = new Order(f.getSupplier());
        f.getItems().forEach(so::addItem);
        repo.save(so);
        return so;
    }
    public void sendOrder(String id) {
        Order so = repo.findById(id).orElseThrow();
        so.send(); repo.save(so); notifier.notifySupplier(so);
    }
    public void confirmOrder(String id, String code) {
        Order so = repo.findById(id).orElseThrow();
        so.confirm(code); repo.save(so);
    }
    public void deliverOrder(String id) {
        Order so = repo.findById(id).orElseThrow();
        so.deliver(); repo.save(so);
    }
    public void qualityCheck(String id, boolean passed) {
        Order so = repo.findById(id).orElseThrow();
        so.qualityCheck(passed); repo.save(so);
    }
    public Status getStatus(String id) {
        return repo.findById(id).orElseThrow().getStatus();
    }
    public List<Order> listOrders() {
        return repo.findAll();
    }
}
