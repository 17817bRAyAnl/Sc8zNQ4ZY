// 代码生成时间: 2025-10-11 01:48:19
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

@SpringBootApplication
@RestController
@RibbonClient(name = "backend-service") // Configure Ribbon to handle backend-service
public class ProxyLoadBalancerService {

    public static void main(String[] args) {
        SpringApplication.run(ProxyLoadBalancerService.class, args);
    }

    // Define a RestTemplate bean with load balancing
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Define a REST API to proxy requests
    @GetMapping("/proxy")
    public String proxyRequest() {
        try {
            // Use the RestTemplate to make a request to the backend service
            String backendResponse = restTemplate().getForObject("http://backend-service/api/data", String.class);
            return backendResponse;
        } catch (Exception e) {
            throw new RuntimeException("Error proxying request", e);
        }
    }

    // Exception handling
    @GetMapping("/error")
    public String handleError() {
        throw new RuntimeException("Simulated error");
    }
}

// This code sets up a Spring Boot application with a REST API that acts as a proxy and balances the load using Ribbon. It also includes basic exception handling.