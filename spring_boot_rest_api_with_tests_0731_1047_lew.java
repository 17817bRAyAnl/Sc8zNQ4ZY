// 代码生成时间: 2025-07-31 10:47:21
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RestController
    @RequestMapping("/api")
    public class ApiController {

        @GetMapping("/test")
        public String test() {
            return "Hello, this is a test API!";
        }

        @PostMapping("/users")
        public ResponseEntity<?> createUser(@RequestBody Map<String, Object> userMap) {
            // Here you would normally save the user to the database
            return ResponseEntity.ok("User created!");
        }
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, String>> handleException(Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("errorMessage", e.getMessage());
            error.put("status", "error");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
