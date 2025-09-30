// 代码生成时间: 2025-10-01 02:11:29
// 重复文件检测器Spring Boot应用
import org.springframework.boot.CommandLineRunner;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class DuplicateFileDetector {

    // 存储上传文件的目录
    private static final String UPLOAD_DIR = "./uploads/";

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "Please select a file to upload";
            } else {
                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);

                // 检查文件是否已存在
                if (Files.exists(filePath)) {
                    return "Duplicate file detected: " + fileName;
                } else {
                    file.transferTo(filePath);
                    return "You successfully uploaded " + fileName + "!";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Could not upload the file: " + e.getMessage();
        }
    }

    // 运行应用时检查重复文件
    public static void main(String[] args) {
        // 运行Spring Boot应用
        SpringApplication.run(DuplicateFileDetector.class, args);
    }

    // 异常处理
    public static class DuplicateFileException extends RuntimeException {
        public DuplicateFileException(String message) {
            super(message);
        }
    }

    // CommandLineRunner用于运行时检查重复文件
    public static class CheckForDuplicates implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            List<String> duplicates = findDuplicates(UPLOAD_DIR);
            if (!duplicates.isEmpty()) {
                throw new DuplicateFileException(
                        "The following duplicate files were detected: " + duplicates);
            }
        }

        // 查找目录中的重复文件
        private List<String> findDuplicates(String directory) throws IOException {
            List<Path> allFiles = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            List<String> duplicates = new ArrayList<>();
            for (Path file : allFiles) {
                int count = (int) allFiles.stream().filter(f -> f.equals(file) ||
                        equalContent(file, f)).count();
                if (count > 1) {
                    duplicates.add(file.getFileName().toString());
                }
            }
            return duplicates;
        }

        // 比较文件内容是否相同
        private boolean equalContent(Path file1, Path file2) throws IOException {
            return Files.readAllBytes(file1).equals(Files.readAllBytes(file2));
        }
    }
}
