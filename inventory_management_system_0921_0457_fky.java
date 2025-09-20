// 代码生成时间: 2025-09-21 04:57:08
package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/inventory")
public class InventoryManagementSystem {

    private final Map<String, Integer> inventory = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystem.class, args);
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<Integer> getItem(@PathVariable String itemId) {
        return ResponseEntity.ok(inventory.getOrDefault(itemId, 0));
    }

    @PostMapping("/items/{itemId}")
    public ResponseEntity<Void> addItem(@PathVariable String itemId, @RequestBody int quantity) {
        inventory.put(itemId, inventory.getOrDefault(itemId, 0) + quantity);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex) {
        return ResponseEntity.badRequest().body("An error occurred: " + ex.getMessage());
    }
}
