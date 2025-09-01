// 代码生成时间: 2025-09-01 12:02:41
package com.scraper.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/scraper")
public class WebScraperService {

    private final RestTemplate restTemplate;

    public WebScraperService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getContent")
    public ResponseEntity<String> getContent(@RequestParam String url) {
        try {
            // Fetch the content from the URL using RestTemplate
            String content = restTemplate.getForObject(url, String.class);
            // Parse the HTML content using Jsoup
            Document doc = Jsoup.parse(content);
            // Extract and return the body content
            return ResponseEntity.ok(doc.body().html());
        } catch (RestClientException e) {
            // Handle exceptions such as network errors
            return ResponseEntity.badRequest().body("Error fetching content: " + e.getMessage());
        }
    }

    // Additional endpoints or methods can be added here
}