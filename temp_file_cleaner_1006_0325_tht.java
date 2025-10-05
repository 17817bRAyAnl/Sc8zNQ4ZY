// 代码生成时间: 2025-10-06 03:25:19
package com.example.tempfilecleaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class TempFileCleaner {

    private static final String TEMP_DIR = "/tmp/";

    public static void main(String[] args) {
        SpringApplication.run(TempFileCleaner.class, args);
    }

    @GetMapping("/clean")
    public String cleanTempDirectory() {
        try {
            File tempDirectory = new File(TEMP_DIR);
            if (!tempDirectory.exists()) {
                return "Temporary directory does not exist.";
            }

            File[] files = tempDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFile(file);
                }
            }

            return "Temporary files have been cleaned.";
        } catch (IOException e) {
            return "Error occurred while cleaning temporary files: " + e.getMessage();
        }
    }

    private void deleteFile(File file) throws IOException {
        if (file.isDirectory()) {
            try (Stream<Path> paths = Files.walk(file.toPath())) {
                paths.sorted((p1, p2) -> -p1.compareTo(p2))
                    .map(Path::toFile)
                    .forEach(this::deleteFile);
            }
        } else {
            Files.delete(file.toPath());
        }
    }
}

class GlobalExceptionHandler {
    // Add global exception handling if needed
}