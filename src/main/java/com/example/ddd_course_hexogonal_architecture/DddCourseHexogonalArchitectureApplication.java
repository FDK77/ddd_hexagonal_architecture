package com.example.ddd_course_hexogonal_architecture;

import com.example.ddd_course_hexogonal_architecture.adapter.primary.console.ConsoleOrderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DddCourseHexogonalArchitectureApplication implements CommandLineRunner {

    private ConsoleOrderAdapter consoleUI;

    @Autowired
    public DddCourseHexogonalArchitectureApplication(ConsoleOrderAdapter consoleUI) {
        this.consoleUI = consoleUI;
    }

    public static void main(String[] args) {
        SpringApplication.run(DddCourseHexogonalArchitectureApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Запуск консольного UI...");
        consoleUI.start();
    }
}
