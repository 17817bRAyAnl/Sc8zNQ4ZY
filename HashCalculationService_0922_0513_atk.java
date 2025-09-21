// 代码生成时间: 2025-09-22 05:13:42
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@RequestMapping("/api/hash")
public class HashCalculationService {

    @PostMapping
    public ResponseEntity<String> calculateHash(@RequestBody String input) {
        try {
            // Get the message digest
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Calculate the hash
            byte[] hashBytes = md.digest(input.getBytes());
            // Encode the hash in base64
            String hash = Base64.getEncoder().encodeToString(hashBytes);
            // Return the hash
            return ResponseEntity.ok(hash);
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hash algorithm not found");
        }
    }
}
