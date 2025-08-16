// 代码生成时间: 2025-08-16 11:04:03
package com.example.processmanager.controller;

import com.example.processmanager.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/processes")
public class ProcessManagerController {

    private final ProcessService processService;

    @Autowired
    public ProcessManagerController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping
    public ResponseEntity<?> listProcesses() {
        try {
            return ResponseEntity.ok(processService.listAllProcesses());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to list processes", e);
        }
    }

    @PostMapping
    public ResponseEntity<?> startProcess(@RequestBody String processName) {
        try {
            processService.startProcess(processName);
            return new ResponseEntity<>("Process started", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to start process", e);
        }
    }

    @DeleteMapping(