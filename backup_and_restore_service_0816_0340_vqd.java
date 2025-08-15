// 代码生成时间: 2025-08-16 03:40:36
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@SpringBootApplication
@RestController
public class BackupAndRestoreService {
    // 定义文件存储的路径
    private static final String UPLOAD_DIR = "./uploads/";

    public static void main(String[] args) {
        SpringApplication.run(BackupAndRestoreService.class, args);
    }

    @PostMapping("/backup")
    public String backupDatabase(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("文件不能为空");
            }
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "备份成功";
        } catch (IOException e) {
            return "备份失败";
        }
    }

    @PostMapping("/restore")
    public String restoreDatabase(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("文件不能为空");
            }
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "恢复成功";
        } catch (IOException e) {
            return "恢复失败";
        }
    }
}
