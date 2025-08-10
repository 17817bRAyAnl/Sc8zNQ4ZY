// 代码生成时间: 2025-08-11 01:33:30
package com.example.dataanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
public class DataAnalysisApp {
    public static void main(String[] args) {
        SpringApplication.run(DataAnalysisApp.class, args);
    }
}

@RestController
@RequestMapping("/api/analysis")
class DataAnalysisController {

    @GetMapping("/sum")
    public ResponseEntity<Integer> sum(@RequestParam(name = "numbers") String numbers) {
        try {
            // Split the numbers by comma and sum them as integers
            String[] numArray = numbers.split(",");
            int sum = 0;
            for (String num : numArray) {
                sum += Integer.parseInt(num.trim());
            }
            return ResponseEntity.ok(sum);
        } catch (Exception e) {
            // Handle the exception and return a custom error message
            return ResponseEntity.badRequest().body(0);
        }
    }

    // Exception handler for handling any exceptions that are not caught by specific try-catch blocks
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("An error occurred during processing: " + e.getMessage());
    }
}