// 代码生成时间: 2025-08-29 12:41:11
package com.example.networkstatuschecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.InetAddress;
# NOTE: 重要实现细节
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
# 优化算法效率
public class NetworkStatusCheckerApplication {
# 增强安全性

    private final RestTemplate restTemplate;

    public NetworkStatusCheckerApplication(RestTemplate restTemplate) {
# 改进用户体验
        this.restTemplate = restTemplate;
    }

    @GetMapping("/check")
# FIXME: 处理边界情况
    public String checkConnection(@RequestParam String host) {
        try {
            InetAddress.getByName(host);
            return "Connection to host is successful";
        } catch (UnknownHostException e) {
            return "Connection to host failed: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(NetworkStatusCheckerApplication.class, args);
    }
}

// 异常处理类
# NOTE: 重要实现细节
package com.example.networkstatuschecker;

import org.springframework.http.HttpStatus;
# FIXME: 处理边界情况
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnknownHostException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleUnknownHostException(UnknownHostException e) {
        return "Error: The host could not be resolved";
    }
}
