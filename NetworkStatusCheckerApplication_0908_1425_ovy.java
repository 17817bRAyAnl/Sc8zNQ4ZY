// 代码生成时间: 2025-09-08 14:25:49
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class NetworkStatusCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkStatusCheckerApplication.class, args);
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkConnection(@RequestParam String host) {
        try {
            InetAddress.getByName(host);
            return ResponseEntity.ok("Connection to " + host + " is successful.");
        } catch (UnknownHostException e) {
            return ResponseEntity.badRequest().body("Connection to " + host + " failed: Host is unreachable.");
        }
    }

    // Exception handling
    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<String> handleUnknownHostException(UnknownHostException e) {
        return ResponseEntity.badRequest().body("Host is unknown: " + e.getMessage());
    }
}
