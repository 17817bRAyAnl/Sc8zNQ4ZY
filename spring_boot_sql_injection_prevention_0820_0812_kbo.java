// 代码生成时间: 2025-08-20 08:12:02
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.NoResultException;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringBootApplication {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/secure-search")
    public List<User> secureSearch(@RequestParam String username) {
        // Prepare a query to prevent SQL injection by using named parameters
        String jpql = "SELECT u FROM User u WHERE u.username = :username";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class)
                .setParameter("username", username);
        return query.getResultList();
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<String> handleNoResultException(NoResultException ex) {
        return ResponseEntity.notFound().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}

// User entity class
class User {
    private Long id;
    private String username;
    // getters and setters
}
