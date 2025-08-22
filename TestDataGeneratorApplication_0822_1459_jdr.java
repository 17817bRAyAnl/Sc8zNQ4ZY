// 代码生成时间: 2025-08-22 14:59:55
import org.springframework.boot.SpringApplication;
# 改进用户体验
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
# TODO: 优化性能
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
# NOTE: 重要实现细节
import org.springframework.http.ResponseEntity;
import java.util.Random;

@SpringBootApplication
@RestController
@RequestMapping("/api/testdata")
public class TestDataGeneratorApplication {

    @GetMapping
    public ResponseEntity<String> generateTestData(@RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        try {
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < quantity; i++) {
                sb.append("Name: ").append("John Doe ").append(i).append("
# TODO: 优化性能
");
                sb.append("Email: ").append("johndoe").append(i).append("@example.com").append("
");
                sb.append("ID: ").append(random.nextInt(1000)).append("

");
            }
            return ResponseEntity.ok(sb.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error generating test data: " + e.getMessage());
        }
    }
# 扩展功能模块

    public static void main(String[] args) {
        SpringApplication.run(TestDataGeneratorApplication.class, args);
    }
}
