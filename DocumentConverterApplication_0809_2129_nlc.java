// 代码生成时间: 2025-08-09 21:29:15
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class DocumentConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentConverterApplication.class, args);
    }
}

@RestController
@RequestMapping("/api/convert")
class DocumentController {

    @GetMapping
    public ResponseEntity<String> convertDocument(@RequestParam String sourceType,
                                                    @RequestParam String targetType,
                                                    @RequestParam(required = false) String content) {
        try {
            // 模拟文档转换逻辑
            if (content == null || content.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Content cannot be empty");
            }
            // 这里可以添加实际的文档转换逻辑
            return ResponseEntity.ok("Converted content from " + sourceType + " to " + targetType);
        } catch (Exception e) {
            // 异常处理逻辑
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while converting document: " + e.getMessage());
        }
    }

    // 异常处理方法
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
    }
}
