// 代码生成时间: 2025-09-05 09:59:56
package com.example.errorlogcollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ErrorLogCollectorApplication {

    private final List<String> errorLogs = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(ErrorLogCollectorApplication.class, args);
    }

    @PostMapping("/log")
    public ResponseEntity<String> logError(@RequestBody String error) {
        errorLogs.add(error);
        return ResponseEntity.ok("Error logged successfully");
    }

    @GetMapping("/logs")
    public ResponseEntity<List<String>> getAllLogs() {
        return ResponseEntity.ok(errorLogs);
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + ex.getMessage());
        }
    }
}
