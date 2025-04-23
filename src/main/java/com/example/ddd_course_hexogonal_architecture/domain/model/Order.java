package com.example.ddd_course_hexogonal_architecture.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static long counter = 1;
    private final String id;
    private final Supplier supplier;
    private final List<LineItem> items = new ArrayList<>();
    private Status status;
    private final LocalDateTime createdAt;
    private LocalDateTime confirmedAt;
    private LocalDateTime deliveredAt;
    private String confirmationCode;
    private boolean qualityPassed;

    public Order(Supplier supplier) {
        this.id = "SO-" + (counter++);
        this.supplier = supplier;
        this.status = Status.DRAFT;
        this.createdAt = LocalDateTime.now();
    }
    public String getId() { return id; }
    public Supplier getSupplier() { return supplier; }
    public List<LineItem> getItems() { return new ArrayList<>(items); }
    public Status getStatus() { return status; }
    public boolean isQualityPassed() { return qualityPassed; }

    public void addItem(LineItem li) {
        if (status != Status.DRAFT)
            throw new IllegalStateException("Нельзя менять заказ в статусе " + status);
        items.add(li);
    }
    public void send() {
        if (items.isEmpty()) throw new IllegalStateException("Пустой заказ");
        status = Status.SENT;
    }
    public void confirm(String code) {
        if (status != Status.SENT) throw new IllegalStateException();
        this.confirmedAt = LocalDateTime.now();
        this.confirmationCode = code;
        status = Status.CONFIRMED;
    }
    public void deliver() {
        if (status != Status.CONFIRMED) throw new IllegalStateException();
        this.deliveredAt = LocalDateTime.now();
        status = Status.DELIVERED;
    }
    public void qualityCheck(boolean passed) {
        if (status != Status.DELIVERED) throw new IllegalStateException();
        this.qualityPassed = passed;
        status = passed ? Status.ACCEPTED : Status.RETURNED;
    }
    @Override public String toString() {
        return "SupplierOrder{" + id + ", sup=" + supplier + ", items=" + items + ", status=" + status + ", qPassed=" + qualityPassed + '}';
    }
}