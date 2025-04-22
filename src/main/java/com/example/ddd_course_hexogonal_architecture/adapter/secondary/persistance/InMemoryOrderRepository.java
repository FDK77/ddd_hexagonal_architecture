package com.example.ddd_course_hexogonal_architecture.adapter.secondary.persistance;



import com.example.ddd_course_hexogonal_architecture.domain.model.Order;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.OrderRepository;

import java.util.*;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String,Order> store = new HashMap<>();

    @Override public void save(Order order) { store.put(order.getOrderId(), order); }
    @Override public Optional<Order> findById(String id) { return Optional.ofNullable(store.get(id)); }
    @Override public List<Order> findAll() { return new ArrayList<>(store.values()); }
}