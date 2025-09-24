// 代码生成时间: 2025-09-24 11:13:25
package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/api/inventory")
public class InventoryManagementSystem {

    private static final List<Item> inventory = new ArrayList<>();

    @PostMapping
    public Item addItem(@RequestBody Item newItem) {
        inventory.add(newItem);
        return newItem;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return inventory;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return Optional.ofNullable(inventory.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id)))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleItemNotFoundException(ItemNotFoundException ex) {
        // Log the exception or perform other error handling
    }

    // Define the Item class
    static class Item {
        private Long id;
        private String name;
        private int quantity;

        public Item(Long id, String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        // Getters and setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    // Define the ItemNotFoundException class
    static class ItemNotFoundException extends RuntimeException {
        public ItemNotFoundException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystem.class, args);
    }
}
