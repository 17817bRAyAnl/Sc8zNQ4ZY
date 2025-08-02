// 代码生成时间: 2025-08-03 03:32:10
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class CsvBatchProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsvBatchProcessorApplication.class, args);
    }

    @PostMapping(value = "/batchProcess", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> batchProcessCsv(@RequestParam("files") List<MultipartFile> files) {
        List<String> processedResults = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    throw new RuntimeException("Empty file uploaded");
                }
                String content = new String(file.getBytes());
                // Process the CSV file content
                // For demonstration, we just return the content
                processedResults.add(content);
            }
            return ResponseEntity.ok(processedResults);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing CSV files: " + e.getMessage());
    }
}
