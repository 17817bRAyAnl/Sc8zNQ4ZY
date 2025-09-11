// 代码生成时间: 2025-09-12 04:47:39
package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuditLogService implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(AuditLogService.class);
    private final ErrorAttributes errorAttributes;

    //注入ErrorAttributes
    public AuditLogService(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    // 记录安全审计日志的API
    @GetMapping("/log")
    public ResponseEntity<?> logAudit(HttpServletRequest request) {
        try {
            // 执行业务逻辑
            // 模拟日志记录
            String logMessage = "Accessed API: " + request.getRequestURI();
            logger.info(logMessage);
            return ResponseEntity.ok("Audit log recorded successfully");
        } catch (Exception e) {
            // 异常处理
            logger.error("Error occurred during logging", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during logging");
        }
    }

    // 异常处理
    @GetMapping("/error")
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = errorAttributes.getErrorAttributes(request, false);
        HttpStatus status = HttpStatus.valueOf((Integer) body.get("status"));
        return ResponseEntity.status(status).body(body);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}