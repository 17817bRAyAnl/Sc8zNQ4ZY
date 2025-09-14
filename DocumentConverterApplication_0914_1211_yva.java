// 代码生成时间: 2025-09-14 12:11:41
package com.example.documentconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SpringBootApplication
public class DocumentConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentConverterApplication.class, args);
    }
}

@RestController
@RequestMapping("/api")
class DocumentConverterController {

    // Exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }

    // Example endpoint for document conversion
    @GetMapping("/convert")
# 扩展功能模块
    public ResponseEntity<String> convertDocument(@RequestParam String sourceFormat, @RequestParam String targetFormat) {
        try {
            // Conversion logic would go here
            String result = "Converted from " + sourceFormat + " to " + targetFormat;
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new RuntimeException("Conversion failed", e);
        }
    }
}
