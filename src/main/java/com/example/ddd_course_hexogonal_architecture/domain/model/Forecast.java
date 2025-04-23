package com.example.ddd_course_hexogonal_architecture.domain.model;

import java.util.List;

public class Forecast {
    private final String id;
    private final Supplier supplier;
    private final List<LineItem> items;
    public Forecast(String id, Supplier supplier, List<LineItem> items) {
        this.id = id; this.supplier = supplier; this.items = items;
    }
    public String getId() { return id; }
    public Supplier getSupplier() { return supplier; }
    public List<LineItem> getItems() { return items; }
}