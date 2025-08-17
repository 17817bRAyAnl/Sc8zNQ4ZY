// 代码生成时间: 2025-08-18 07:50:53
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class UserInterfaceKitApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserInterfaceKitApplication.class, args);
    }

    @GetMapping("/components")
    public ResponseEntity<?> listComponents() {
        Map<String, String> componentInfo = new HashMap<>();
        componentInfo.put("button", "Button component");
        componentInfo.put("input", "Input component");
        componentInfo.put("select", "Select component");
        return ResponseEntity.ok(componentInfo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("message", "An error occurred");
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
