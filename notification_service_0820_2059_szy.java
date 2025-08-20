// 代码生成时间: 2025-08-20 20:59:19
package com.example.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/notifications")
@SpringBootApplication
public class NotificationService {

    public static void main(String[] args) {
        SpringApplication.run(NotificationService.class, args);
    }

    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody Notification notification) {
        // 这里添加创建通知的逻辑
        return ResponseEntity.ok("Notification created successfully");
    }

    @GetMapping
    public ResponseEntity<Notification> getNotification(@RequestParam Long id) {
        // 这里添加获取通知的逻辑
        Notification notification = new Notification();
        // 假设根据id获取到了通知
        return ResponseEntity.ok(notification);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getMessage());
    }

    // Notification DTO
    public static class Notification {
        private Long id;
        private String message;

        // Getters and Setters
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
