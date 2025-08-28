// 代码生成时间: 2025-08-28 19:31:14
 * filename: spring_boot_integration_test_app.java
 */

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}

@RestController
@RequestMapping("/api")
class ApiController {
    // REST API to return a greeting
    @GetMapping("/greet/{name}")
    public ResponseEntity<String> greet(@PathVariable String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return ResponseEntity.ok("Hello, " + name);
    }

    // Exception Handling
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

@RestControllerAdvice
class GlobalExceptionHandler {
    // Global exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        String errorDetail = "Error Message: " + ex.getMessage() + "
URI: " + request.getRequestURI();
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}