// 代码生成时间: 2025-08-22 10:16:30
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.Map;

// 定义Spring Boot应用
@SpringBootApplication
public class ConfigManagerService {

    public static void main(String[] args) {
        SpringApplication.run(ConfigManagerService.class, args);
    }
}

// 控制器类，提供REST API
@RestController
@RequestMapping("/api/config")
public class ConfigManagerController {

    private final Environment environment;

    // 通过构造器注入Environment对象
    @Autowired
    public ConfigManagerController(Environment environment) {
        this.environment = environment;
    }

    // 获取配置信息的API
    @GetMapping
    public ResponseEntity<Map<String, String>> getAllConfigs() {
        return ResponseEntity.ok(environment.getProperties());
    }

    // 异常处理
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
