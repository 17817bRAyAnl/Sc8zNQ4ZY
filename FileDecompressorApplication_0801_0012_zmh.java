// 代码生成时间: 2025-08-01 00:12:29
package com.example.filedecompressor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileDecompressorApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileDecompressorApplication.class, args);
    }
}

/**
 * FileDecompressorController.java
 * REST Controller for handling file decompression requests.
 */

package com.example.filedecompressor.controller;

import com.example.filedecompressor.service.FileDecompressorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/decompress")
public class FileDecompressorController {
    private final FileDecompressorService decompressorService;

    @Autowired
    public FileDecompressorController(FileDecompressorService decompressorService) {
        this.decompressorService = decompressorService;
    }

    @PostMapping("/files")
    public ResponseEntity<Resource> decompressFile(@RequestParam("file") MultipartFile file) {
        try {
            Resource decompressedFile = decompressorService.decompress(file);
            return ResponseEntity.ok(decompressedFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

/**
 * FileDecompressorService.java
 * Service class to handle the file decompression logic.
 */

package com.example.filedecompressor.service;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileDecompressorService {

    public Resource decompress(MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile("decompressed", null);
        Files.deleteIfExists(tempFile); // Ensure the temp file is deleted on exit

        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = Files.newOutputStream(tempFile, StandardCopyOption.REPLACE_EXISTING);
             TarArchiveInputStream tarInput = new TarArchiveInputStream(
                     new GzipCompressorInputStream(inputStream))) {

            TarArchiveEntry entry;
            while ((entry = tarInput.getNextTarEntry()) != null) {
                if (!entry.isDirectory()) {
                    Files.copy(tarInput, outputStream, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }

        return new FilePart("decompressedFile", tempFile.getFileName().toString(), "application/x-tar", Files.probeContentType(tempFile), DataBufferUtils.read(tempFile, 1024));
    }
}

/**
 * FileDecompressorExceptionHandler.java
 * Global exception handler class.
 */

package com.example.filedecompressor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileDecompressorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body("File too large! Maximal file size allowed is 2MB.");
    }
}