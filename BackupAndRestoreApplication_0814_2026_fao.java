// 代码生成时间: 2025-08-14 20:26:04
package com.example.backuprestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@SpringBootApplication
@RestController
public class BackupAndRestoreApplication {

    private static final String BACKUP_DIRECTORY = "backups/";
    private static final String DATA_DIRECTORY = "data/";

    public static void main(String[] args) {
        SpringApplication.run(BackupAndRestoreApplication.class, args);
    }

    @PostMapping("/backup")
    public ResponseEntity<String> backupData(@RequestParam String name) {
        try {
            String backupPath = BACKUP_DIRECTORY + name + ".sql";
            // 模拟备份操作
            Files.createDirectories(Paths.get(BACKUP_DIRECTORY));
            Files.write(Paths.get(backupPath), "Backup data...".getBytes());
            return ResponseEntity.ok("Backup created at: " + backupPath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create backup.");
        }
    }

    @GetMapping("/restore/{name}")
    public ResponseEntity<String> restoreData(@PathVariable String name) {
        try {
            String backupPath = BACKUP_DIRECTORY + name + ".sql";
            // 模拟恢复操作
            Files.copy(Paths.get(backupPath), Paths.get(DATA_DIRECTORY + "restored_data.sql"));
            return ResponseEntity.ok("Data restored from: " + backupPath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Backup file not found.");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
