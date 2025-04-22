package com.example.ddd_course_hexogonal_architecture.cfg;

import com.example.ddd_course_hexogonal_architecture.adapter.primary.console.ConsoleOrderAdapter;
import com.example.ddd_course_hexogonal_architecture.adapter.secondary.notification.ConsoleNotificationAdapter;
import com.example.ddd_course_hexogonal_architecture.adapter.secondary.persistance.InMemoryOrderRepository;
import com.example.ddd_course_hexogonal_architecture.domain.port.primary.OrderServicePort;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.OrderRepository;
import com.example.ddd_course_hexogonal_architecture.domain.port.secondary.SupplierNotificationPort;
import com.example.ddd_course_hexogonal_architecture.domain.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OrderRepository orderRepository() {
        return new InMemoryOrderRepository();
    }

    @Bean
    public SupplierNotificationPort supplierNotificationPort() {
        return new ConsoleNotificationAdapter();
    }

    @Bean
    public OrderServicePort orderServicePort(
            OrderRepository repo,
            SupplierNotificationPort notifier) {
        return new OrderService(repo, notifier);
    }

    @Bean
    public ConsoleOrderAdapter consoleOrderAdapter(OrderServicePort service) {
        return new ConsoleOrderAdapter(service);
    }
}