// 代码生成时间: 2025-09-02 15:47:17
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@RestController
@RequestMapping("/config")
public class ConfigManagerApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(ConfigManagerApplication.class, args);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllProperties() {
        return ResponseEntity.ok(env.getAllProperties());
    }

    @RestControllerAdvice
    public static class GlobalExceptionHandler {

        @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
            return ResponseEntity.badRequest().body("An error occurred: " + ex.getMessage());
        }
    }
}
