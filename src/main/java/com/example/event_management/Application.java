package com.example.event_management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(
        title = "My API Event Management",
        description = "Manage all event...",
        version = "1.1.1"))
@SpringBootApplication

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    // welcome
}
