package com.example.ddd_course_hexogonal_architecture.domain.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final List<ProductForecast> items;
    private OrderStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(List<ProductForecast> items) {
        this.orderId = UUID.randomUUID().toString();
        this.items = items;
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    public String getOrderId() { return orderId; }
    public List<ProductForecast> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    private void ensureStatus(OrderStatus required) {
        if (this.status != required) {
            throw new IllegalStateException(
                    "Order must be " + required + " to invoke this operation (current: " + status + ")");
        }
    }

    public void send() {
        ensureStatus(OrderStatus.CREATED);
        status = OrderStatus.SENT;
        updatedAt = LocalDateTime.now();
    }

    public void confirm() {
        ensureStatus(OrderStatus.SENT);
        status = OrderStatus.CONFIRMED;
        updatedAt = LocalDateTime.now();
    }

    public void markInTransit() {
        ensureStatus(OrderStatus.CONFIRMED);
        status = OrderStatus.IN_TRANSIT;
        updatedAt = LocalDateTime.now();
    }

    public void receive() {
        ensureStatus(OrderStatus.IN_TRANSIT);
        status = OrderStatus.RECEIVED;
        updatedAt = LocalDateTime.now();
    }

    public void qualityCheck(boolean ok) {
        ensureStatus(OrderStatus.RECEIVED);
        status = ok ? OrderStatus.QUALITY_CHECKED : OrderStatus.RETURNED;
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + orderId + '\'' +
                ", status=" + status +
                ", items=" + items +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
