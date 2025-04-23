package com.example.ddd_course_hexogonal_architecture.adapter.primary.rest;

import com.example.ddd_course_hexogonal_architecture.domain.model.Forecast;
import com.example.ddd_course_hexogonal_architecture.domain.model.Order;
import com.example.ddd_course_hexogonal_architecture.domain.model.Status;
import com.example.ddd_course_hexogonal_architecture.domain.port.primary.OrderServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class RestOrderController {
    private final OrderServicePort service;

    @Autowired
    public RestOrderController(OrderServicePort service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Forecast forecast) {
        Order order = service.createOrder(forecast);
        return ResponseEntity.status(201).body(order);
    }

    @PostMapping("/{id}/send")
    public ResponseEntity<Void> sendOrder(@PathVariable String id) {
        service.sendOrder(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Void> confirmOrder(
            @PathVariable String id,
            @RequestParam String code) {
        service.confirmOrder(id, code);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/deliver")
    public ResponseEntity<Void> deliverOrder(@PathVariable String id) {
        service.deliverOrder(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/qc")
    public ResponseEntity<Void> qualityCheck(
            @PathVariable String id,
            @RequestParam boolean passed) {
        service.qualityCheck(id, passed);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Status> getStatus(@PathVariable String id) {
        return ResponseEntity.ok(service.getStatus(id));
    }

    @GetMapping
    public ResponseEntity<List<Order>> listOrders() {
        return ResponseEntity.ok(service.listOrders());
    }
}