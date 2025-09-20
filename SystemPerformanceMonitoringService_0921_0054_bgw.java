// 代码生成时间: 2025-09-21 00:54:42
package com.example.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.lang.management.ManagementFactory;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api/monitoring")
public class SystemPerformanceMonitoringService {

    private final Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        SpringApplication.run(SystemPerformanceMonitoringService.class, args);
    }

    @GetMapping("/cpu")
    public ResponseEntity<Double> getCpuUsage() {
        double cpuUsage = ManagementFactory.getOperatingSystemMXBean().getSystemCpuLoad();
        return ResponseEntity.ok(cpuUsage);
    }

    @GetMapping("/memory")
    public ResponseEntity<Long> getMemoryUsage() {
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        return ResponseEntity.ok(usedMemory);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + ex.getMessage());
    }
}
