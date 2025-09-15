// 代码生成时间: 2025-09-16 07:47:44
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.time.LocalDateTime;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class DemoApplication {
# 添加错误处理

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/time")
    public String getCurrentTime() {
        return "Current time is: " + LocalDateTime.now().toString();
    }

    @ExceptionHandler(Exception.class)
# 扩展功能模块
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .badRequest()
                .body("Error: " + e.getMessage());
    }
# FIXME: 处理边界情况
}

@ControllerAdvice
class ExceptionHandlingControllerAdvice {
# NOTE: 重要实现细节

    @ExceptionHandler(Exception.class)
# FIXME: 处理边界情况
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .badRequest()
                .body("Global error: " + e.getMessage());
    }
}
