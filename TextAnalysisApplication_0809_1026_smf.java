// 代码生成时间: 2025-08-09 10:26:49
package com.example.textanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@RestController
public class TextAnalysisApplication {

    @PostMapping("/analyze")
    public String analyzeFileContent(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to upload empty file");
            }
            String content = new String(file.getBytes());
            // Analyze the content here...
            // For simplicity, we just return the length of the content
            return "Total characters: " + content.length();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TextAnalysisApplication.class, args);
    }
}

// Exception handling is done within the method with try-catch block.
// For a more structured approach, consider creating a custom exception handler.
