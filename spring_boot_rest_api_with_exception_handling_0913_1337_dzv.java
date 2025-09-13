// 代码生成时间: 2025-09-13 13:37:09
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringBootApplication {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}

// Define a custom exception if needed
class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}

// Define a controller for the REST API with exception handling
@RestController
@RequestMapping("/api")
class ApiController {

    @GetMapping("/test")
    public String test() throws CustomException {
        if (true) { // Replace with actual condition
            throw new CustomException("Test exception");
        }
        return "Test succeeded";
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCustomException(CustomException e) {
        return "Custom error: " + e.getMessage();
    }
}