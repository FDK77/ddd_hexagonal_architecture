package com.example.ddd_course_hexogonal_architecture.adapter.primary.rest;

import com.example.ddd_course_hexogonal_architecture.domain.model.Forecast;
import com.example.ddd_course_hexogonal_architecture.domain.model.Order;
import com.example.ddd_course_hexogonal_architecture.domain.model.OrderStatus;
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
        // Используем доменный Forecast напрямую из JSON
        Order order = service.createOrder(forecast);
        return ResponseEntity.status(201).body(order);
    }


    @PostMapping("/{id}/send")
    public ResponseEntity<Void> sendOrder(@PathVariable String id) {
        service.sendOrder(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Void> confirmOrder(@PathVariable String id) {
        service.receiveConfirmation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/transit")
    public ResponseEntity<Void> transitOrder(@PathVariable String id) {
        service.markInTransit(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<OrderStatus> getStatus(@PathVariable String id) {
        return ResponseEntity.ok(service.getStatus(id));
    }

    @PostMapping("/{id}/receive")
    public ResponseEntity<Void> receiveDelivery(@PathVariable String id, @RequestParam boolean qualityOk) {
        service.acceptDelivery(id, qualityOk);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Order> listOrders() {
        return service.listOrders();
    }
}

