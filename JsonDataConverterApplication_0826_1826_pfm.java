// 代码生成时间: 2025-08-26 18:26:14
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class JsonDataConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonDataConverterApplication.class, args);
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convertJson(@Valid @RequestBody Map<String, Object> jsonData) {
        try {
            // JSON数据转换逻辑，此处省略
            // 假设转换结果也是Map类型
            Map<String, Object> convertedData = new HashMap<>();
            // 填充转换后的数据
            convertedData.putAll(jsonData);
            // 返回转换后的结果
            return ResponseEntity.ok(convertedData);
        } catch (Exception e) {
            // 异常处理逻辑，此处省略
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to JSON Data Converter API";
    }
}
