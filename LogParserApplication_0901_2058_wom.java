// 代码生成时间: 2025-09-01 20:58:15
package com.example.logparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SpringBootApplication
public class LogParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogParserApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addExceptionHandlers(ExceptionHandlingRegistry registry) {
                registry.registerExceptionHandler(Exception.class, new ResponseEntityExceptionHandler());
            }
        };
    }
}
