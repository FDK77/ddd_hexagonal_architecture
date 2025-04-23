package com.example.ddd_course_hexogonal_architecture.domain.model;

public class LineItem {
    private final Product product;
    private final int quantity;
    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    @Override public String toString() { return product.getName() + " x " + quantity; }
}