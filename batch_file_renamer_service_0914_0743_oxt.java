// 代码生成时间: 2025-09-14 07:43:07
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class BatchFileRenamerService {

    public static void main(String[] args) {
        SpringApplication.run(BatchFileRenamerService.class, args);
    }

    @PostMapping("/rename")
    public String renameFiles(@RequestBody Map<String, String> fileMap) {
        try {
            for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                Path oldPath = Paths.get(entry.getKey());
                Path newPath = Paths.get(entry.getValue());
                Files.move(oldPath, newPath);
            }
            return "All files have been renamed successfully.";
        } catch (IOException e) {
            return "Error occurred during renaming: " + e.getMessage();
        }
    }
}
