// 代码生成时间: 2025-09-06 04:14:49
package com.example.messagenotificationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class MessageNotificationSystem {

    public static void main(String[] args) {
        SpringApplication.run(MessageNotificationSystem.class, args);
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        // Simulate message sending
        System.out.println("Message sent: " + message);
        return ResponseEntity.ok("Message sent successfully");
    }

    @PostMapping("/notify")
    public ResponseEntity<Map<String, Object>> notifyUser(@RequestBody Map<String, Object> notification) {
        // Simulate notification sending
        System.out.println("Notification sent with data: " + notification);
        return ResponseEntity.ok(Map.of("status", "Notification sent successfully"));
    }

    // Exception Handling
    @RestControllerAdvice
    static class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
        }
    }
}
