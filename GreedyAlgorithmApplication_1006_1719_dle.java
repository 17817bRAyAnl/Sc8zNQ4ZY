// 代码生成时间: 2025-10-06 17:19:41
package com.example.greedyapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class GreedyAlgorithmApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreedyAlgorithmApplication.class, args);
    }

    @GetMapping("/greedy")
    public ResponseEntity<String> greedyAlgorithm() {
        // 模拟一个简单的贪心算法逻辑
        return ResponseEntity.ok("Greedy Algorithm executed successfully!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
