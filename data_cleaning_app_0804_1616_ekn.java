// 代码生成时间: 2025-08-04 16:16:33
package com.example.datacleaning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DataCleaningApp {

    public static void main(String[] args) {
        SpringApplication.run(DataCleaningApp.class, args);
    }

    @GetMapping("/cleanData")
    public ResponseEntity<?> cleanData(@RequestParam(required = false) String rawData) {
        try {
            if (rawData == null || rawData.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Raw data cannot be empty.");
            }
            String cleanedData = rawData.trim(); // 示例：去除首尾空白
            return ResponseEntity.ok(cleanedData);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleException(Exception e) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", e.getMessage());
        return ResponseEntity.status(500).body(errorDetails);
    }
}
