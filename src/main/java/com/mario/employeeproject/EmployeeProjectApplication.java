package com.mario.employeeproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.mario.employeeproject.repository")
public class EmployeeProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeProjectApplication.class, args);
    }
}
