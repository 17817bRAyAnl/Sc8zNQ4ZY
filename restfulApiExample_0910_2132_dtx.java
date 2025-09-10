// 代码生成时间: 2025-09-10 21:32:34
package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class RestfulApiExample {

    // 模拟一个存储数据的列表
    private static final List<String> DATA = new ArrayList<>(Arrays.asList("Item1", "Item2", "Item3"));

    // GET请求，获取所有数据
    @GetMapping("/items")
    public List<String> getAllItems() {
        return DATA;
    }

    // GET请求，根据ID获取数据
    @GetMapping("/items/{id}")
    public ResponseEntity<String> getItemById(@PathVariable String id) {
        if (DATA.contains(id)) {
            return ResponseEntity.ok(DATA.get(DATA.indexOf(id)));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
    }

    // POST请求，添加新数据
    @PostMapping("/items")
    public ResponseEntity<String> createItem(@RequestBody String item) {
        DATA.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added successfully");
    }

    // DELETE请求，根据ID删除数据
    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable String id) {
        if (DATA.contains(id)) {
            DATA.remove(DATA.indexOf(id));
            return ResponseEntity.ok("Item deleted successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
    }

    // 异常处理器
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getReason(), ex.getStatus());
    }
}
