// 代码生成时间: 2025-09-21 16:44:58
package com.example.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
@RestController
@RequestMapping("/api/monitoring")
public class SystemPerformanceMonitoring {

    public static void main(String[] args) {
        SpringApplication.run(SystemPerformanceMonitoring.class, args);
    }

    @GetMapping("/cpu")
    public String getCpuUsage() {
        // Logic to fetch CPU usage goes here
        return "CPU usage: XX%";
    }

    @GetMapping("/memory")
    public String getMemoryUsage() {
        // Logic to fetch memory usage goes here
        return "Memory usage: XX%";
    }

    @GetMapping("/disk")
    public String getDiskUsage() {
        // Logic to fetch disk usage goes here
        return "Disk usage: XX%";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "An error occurred: " + ex.getMessage();
    }
}
