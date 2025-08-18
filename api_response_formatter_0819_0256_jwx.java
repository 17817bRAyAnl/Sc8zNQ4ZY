// 代码生成时间: 2025-08-19 02:56:02
package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class ApiResponseFormatter {

    // 格式化响应的工具类
    public static class ApiResponse<T> {
        private String timestamp;
        private String status;
        private T data;
        private String message;

        public ApiResponse(LocalDateTime timestamp, HttpStatus status, T data) {
            this.timestamp = timestamp.toString();
            this.status = status.toString();
            this.data = data;
       
            if (status.is2xxSuccessful()) {
                this.message = "Success";
            } else {
                this.message = "Error";
            }
        }
    
        // Getter and Setter methods
        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @GetMapping("/format")
    public ResponseEntity<ApiResponse<String>> formatResponse() {
        return ResponseEntity.ok(new ApiResponse<>(LocalDateTime.now(), HttpStatus.OK, "Formatted API Response"));
    }

    // 异常处理
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        ApiResponse<String> errorResponse = new ApiResponse<>(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, "");
        errorResponse.setMessage("Internal Server Error: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
