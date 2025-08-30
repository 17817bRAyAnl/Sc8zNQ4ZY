// 代码生成时间: 2025-08-31 02:14:34
package com.example.testdatagenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class TestDataGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDataGeneratorApplication.class, args);
    }

    @GetMapping("/generate")
    public ResponseEntity<?> generateTestData(@RequestParam int count) {
        try {
            if (count <= 0) {
                throw new IllegalArgumentException("Count must be greater than zero.");
            }
            List<String> testData = generateRandomData(count);
            return ResponseEntity.ok(testData);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private List<String> generateRandomData(int count) {
        List<String> testData = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            testData.add("Data_" + i);
        }
        return testData;
    }
}
