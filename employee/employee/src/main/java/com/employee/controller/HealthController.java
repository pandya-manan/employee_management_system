package com.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @GetMapping("/health")
    public String healthCheck() {
        return "Service is running on port 8005";
    }
    
    @GetMapping("/")
    public String home() {
        return "Employee Management API is running. Visit <a href='/swagger-ui.html'>Swagger UI</a>";
    }
}