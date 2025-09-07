// 代码生成时间: 2025-09-07 19:49:48
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class CsvBatchProcessorApplication {

    private static final String UPLOAD_DIR = "/uploads/";

    public static void main(String[] args) {
        SpringApplication.run(CsvBatchProcessorApplication.class, args);
    }

    @PostMapping("/process-csv")
    public String processCsvFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);

            List<String> lines = Files.readAllLines(path);
            return lines.stream().limit(10) // Process only first 10 lines for batch
                        .map(line -> "Processed: " + line)
                        .collect(Collectors.joining("
"));

        } catch (IOException e) {
            return "Error processing file: " + e.getMessage();
        }
    }
}
