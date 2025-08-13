// 代码生成时间: 2025-08-13 16:13:44
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
# 扩展功能模块
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
# FIXME: 处理边界情况
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
# NOTE: 重要实现细节
@RequestMapping("/api/hash")
public class HashCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HashCalculatorApplication.class, args);
    }

    @PostMapping("/calculate")
    public ResponseEntity<String> calculateHash(@RequestBody String input) {
# 扩展功能模块
        try {
            return ResponseEntity.ok(hash(input));
# 扩展功能模块
        } catch (NoSuchAlgorithmException e) {
# 优化算法效率
            return new ResponseEntity<>("Error: Algorithm not available", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service is up and running");
    }
# FIXME: 处理边界情况

    private String hash(String input) throws NoSuchAlgorithmException {
# TODO: 优化性能
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes());
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xff & b);
# NOTE: 重要实现细节
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
# 优化算法效率
        return hexString.toString();
    }
}
