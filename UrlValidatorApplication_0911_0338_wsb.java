// 代码生成时间: 2025-09-11 03:38:58
package com.example.urlvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
@RestController
public class UrlValidatorApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(UrlValidatorApplication.class, args);
    }

    @PostMapping("/checkUrl")
    public ResponseEntity<?> validateUrl(@RequestParam String url) {
        try {
            new URL(url);
            return ResponseEntity.ok("URL is valid.");
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().body("Invalid URL: " + url);
        }
    }
    
    @ControllerAdvice
    class GlobalExceptionHandler {
        @ExceptionHandler(MalformedURLException.class)
        public ResponseEntity<String> handleInvalidURLException(MalformedURLException ex) {
            return new ResponseEntity<>("URL is invalid", HttpStatus.BAD_REQUEST);
        }
    }
}
