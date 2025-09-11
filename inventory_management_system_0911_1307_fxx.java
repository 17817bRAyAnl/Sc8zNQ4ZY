// 代码生成时间: 2025-09-11 13:07:35
package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/inventory")
public class InventoryManagementSystem {

    private final Map<Integer, String> inventory = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystem.class, args);
    }

    @PostMapping("/items")
    public ResponseEntity<String> addItem(@RequestBody String itemName) {
        inventory.put(inventory.size() + 1, itemName);
        return ResponseEntity.ok("Item added: " + itemName);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<String> getItem(@PathVariable int id) {
        String itemName = inventory.get(id);
        if (itemName == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemName);
    }

    @PostMapping("/items/{id}")
    public ResponseEntity<String> updateItem(@PathVariable int id, @RequestBody String newItemName) {
        if (inventory.containsKey(id)) {
            inventory.put(id, newItemName);
            return ResponseEntity.ok("Item updated: " + newItemName);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/items")
    public ResponseEntity<Map<Integer, String>> getAllItems() {
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/items/count")
    public ResponseEntity<Integer> getItemCount() {
        return ResponseEntity.ok(inventory.size());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}
