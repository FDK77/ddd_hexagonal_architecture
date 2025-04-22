package com.example.ddd_course_hexogonal_architecture.domain.model;

public class ProductForecast {
    private final String productId;
    private final int quantity;

    public ProductForecast(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return productId + " x " + quantity;
    }
}
