// 代码生成时间: 2025-08-23 09:30:44
import org.springframework.boot.SpringApplication;
# FIXME: 处理边界情况
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/config")
# 添加错误处理
public class ConfigManagerApplication {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
# FIXME: 处理边界情况
        SpringApplication.run(ConfigManagerApplication.class, args);
    }

    @GetMapping("/properties")
    public ResponseEntity<?> getAllProperties() {
        Map<String, String> propertiesMap = new HashMap<>();
        environment.getPropertySources().forEach((name, propertySource) -> 
            propertiesMap.putAll((Map<String, Object>) propertySource.getSource()));
        return ResponseEntity.ok(propertiesMap);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }
# 扩展功能模块
}
