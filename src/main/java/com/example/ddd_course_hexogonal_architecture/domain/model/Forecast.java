package com.example.ddd_course_hexogonal_architecture.domain.model;

import java.util.List;

public class Forecast {
    private final String forecastId;
    private final List<ProductForecast> items;

    public Forecast(String forecastId, List<ProductForecast> items) {
        this.forecastId = forecastId;
        this.items = items;
    }

    public String getForecastId() { return forecastId; }
    public List<ProductForecast> getItems() { return items; }
}