// 代码生成时间: 2025-10-09 02:33:17
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/achievements")
public class AchievementSystem {

    // 模拟成就存储
    private static final String[] ACHIEVEMENTS = new String[] {
        "First Login", 
        