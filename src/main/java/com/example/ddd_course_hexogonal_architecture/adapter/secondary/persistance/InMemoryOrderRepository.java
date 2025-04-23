package com.example.ddd_course_hexogonal_architecture.adapter.secondary.persistance;



import com.example.ddd_course_hexogonal_architecture.domain.model.Order;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.OrderRepository;

import java.util.*;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> store = new HashMap<>();
    public void save(Order o) { store.put(o.getId(), o); }
    public Optional<Order> findById(String id) { return Optional.ofNullable(store.get(id)); }
    public List<Order> findAll() { return new ArrayList<>(store.values()); }
}