// 代码生成时间: 2025-08-22 20:35:19
package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
# NOTE: 重要实现细节
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
# TODO: 优化性能
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
# FIXME: 处理边界情况
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
# 优化算法效率
public class MyRestController {

    @GetMapping("/greeting/{name}")
    public ResponseEntity<String> greeting(@PathVariable String name) {
# 增强安全性
        return ResponseEntity.ok("Hello, " + name + "!");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
# 添加错误处理
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}
