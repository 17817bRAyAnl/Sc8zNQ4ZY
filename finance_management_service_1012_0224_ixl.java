// 代码生成时间: 2025-10-12 02:24:26
package com.example.financemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@RestController
@RequestMapping("/api/finance")
public class FinanceManagementService {

    private final ConcurrentHashMap<String, Double> transactions = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(FinanceManagementService.class, args);
    }

    @PostMapping("/transaction")
    public String createTransaction(@RequestBody @Valid Transaction transaction) {
        transactions.put(transaction.getId(), transaction.getAmount());
        return "Transaction created successfully";
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransactionById(@PathVariable String id) {
        Double amount = transactions.get(id);
        if (amount == null) {
            throw new TransactionNotFoundException("Transaction with ID: " + id + " not found");
        }
        return new Transaction(id, amount);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public String handleTransactionNotFoundException(TransactionNotFoundException ex) {
        return ex.getMessage();
    }
}

class Transaction {
    private String id;
    private Double amount;

    public Transaction() {
    }

    public Transaction(String id, Double amount) {
        this.id = id;
        this.amount = amount;
    }

    // Getters and setters omitted for brevity
}

class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}