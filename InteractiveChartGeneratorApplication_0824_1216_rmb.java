// 代码生成时间: 2025-08-24 12:16:55
 * InteractiveChartGeneratorApplication.java
 * Spring Boot application for an interactive chart generator.
 */

package com.example.chartgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class InteractiveChartGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(InteractiveChartGeneratorApplication.class, args);
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateChart(@RequestParam(required = false) String data) {
        try {
            // Generate chart logic
            String chart = "Interactive chart generated with data: " + data;
            return ResponseEntity.ok(chart);
        } catch (Exception e) {
            // Log error (e.g., using SLF4J)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating chart: " + e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Log the exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}
