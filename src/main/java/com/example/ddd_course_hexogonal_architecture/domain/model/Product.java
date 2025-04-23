package com.example.ddd_course_hexogonal_architecture.domain.model;

public class Product {
    private final String id;
    private final String name;
    private final String unit;
    public Product(String id, String name, String unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getUnit() { return unit; }
    @Override public String toString() { return name + " (" + unit + ")"; }
}
