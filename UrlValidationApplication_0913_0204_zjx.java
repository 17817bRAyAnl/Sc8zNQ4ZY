// 代码生成时间: 2025-09-13 02:04:37
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;

@SpringBootApplication
public class UrlValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlValidationApplication.class, args);
    }

    @RestController
    class UrlValidationController {

        private final RestTemplate restTemplate;

        public UrlValidationController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @PostMapping("/validateUrl")
        public String validateUrl(@RequestParam String url) {
            try {
                new URL(url).toURI();
                // Check if the URL is accessible
                ResponseEntity<String> response = restTemplate.headForHeaders(url);
                if (response.getStatusCode().isError()) {
                    return "ERROR: The URL is not accessible or valid.";
                }
                return "SUCCESS: The URL is valid and accessible.";
            } catch (MalformedURLException e) {
                return "ERROR: The URL is not properly formatted.";
            } catch (Exception e) {
                return "ERROR: An error occurred while checking the URL.";
            }
        }
    }
}
