// 代码生成时间: 2025-09-06 19:36:34
package com.example.documentconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
public class DocumentConverterApplication implements ErrorController {

    public static void main(String[] args) {
        SpringApplication.run(DocumentConverterApplication.class, args);
    }

    @RequestMapping("/convert")
    public String convertDocument(MultipartFile file) {
        // Check if the file is empty
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty.");
        }

        // Implement the logic to convert the document
        // For demonstration, we'll return a string indicating the conversion
        return "Converted file: " + file.getOriginalFilename();
    }

    @RequestMapping("/error")
    public HttpStatus error() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    // Implement additional error handling logic if needed
    // ...
}
