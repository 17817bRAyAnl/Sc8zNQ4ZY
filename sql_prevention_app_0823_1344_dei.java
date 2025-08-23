// 代码生成时间: 2025-08-23 13:44:22
package com.example.sqlpreventionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotEmpty;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class SqlPreventionApp {
    private static final Logger LOGGER = Logger.getLogger(SqlPreventionApp.class.getName());
    
    @Autowired
    private UserJpaRepository userJpaRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(SqlPreventionApp.class, args);
    }
    
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userJpaRepository.findAll());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all users", e);
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }
    
    @GetMapping("/users/search")
    public ResponseEntity<?> searchUsers(@RequestParam @NotEmpty(message = "Search keyword is required") String keyword) {
        try {
            return ResponseEntity.ok(userJpaRepository.findByNameContaining(keyword));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error searching for users", e);
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }
    
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody @Validated User user) {
        try {
            userJpaRepository.save(user);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating user", e);
            return ResponseEntity.badRequest().body("Error creating user");
        }
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        LOGGER.log(Level.SEVERE, "Exception occurred", e);
        return ResponseEntity.internalServerError().body("An error occurred");
    }
}

// UserRepository interface to interact with database
package com.example.sqlpreventionapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String name);
}

// Entity class representing a user
package com.example.sqlpreventionapp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
