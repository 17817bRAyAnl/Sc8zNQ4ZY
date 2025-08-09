// 代码生成时间: 2025-08-10 05:12:41
package com.example.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ShoppingCartApplication {

    // In-memory storage for shopping cart items
    private Map<Integer, List<String>> cartItems = new HashMap<>();
    private int cartIdCounter = 1;

    // Endpoint to create a new shopping cart
    @PostMapping("/cart")
    public Map<String, Integer> createCart() {
        int newCartId = cartIdCounter++;
        cartItems.put(newCartId, new ArrayList<>());
        return Map.of("cartId", newCartId);
    }

    // Endpoint to add items to the shopping cart
    @PostMapping("/cart/{cartId}/items")
    public Map<String, String> addItemToCart(@RequestParam int cartId, @RequestParam String item) {
        if(!cartItems.containsKey(cartId)) {
            throw new CartNotFoundException("Cart not found");
        }
        cartItems.get(cartId).add(item);
        return Map.of("message", "Item added to cart");
    }

    // Endpoint to get items from the shopping cart
    @GetMapping("/cart/{cartId}/items")
    public Map<String, List<String>> getCartItems(@RequestParam int cartId) {
        if(!cartItems.containsKey(cartId)) {
            throw new CartNotFoundException("Cart not found");
        }
        return Map.of("items", cartItems.get(cartId));
    }

    // Exception handling
    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCartNotFoundException(CartNotFoundException ex) {
        return Map.of("error", ex.getMessage());
    }

    // Custom exception class
    public static class CartNotFoundException extends RuntimeException {
        public CartNotFoundException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }
}
