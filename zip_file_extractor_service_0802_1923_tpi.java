// 代码生成时间: 2025-08-02 19:23:42
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SpringBootApplication
@RestController
public class ZipFileExtractorService {

    private static final String DESTINATION_FOLDER = "path/to/destination"; // Specify the destination folder path

    @PostMapping("/extractZip")
    public ResponseEntity<String> extractZipFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            byte[] bytes = file.getBytes();
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file.getInputStream()));

            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                String filePath = DESTINATION_FOLDER + File.separator + zipEntry.getName();

                if (zipEntry.isDirectory()) {
                    File dir = new File(filePath);
                    dir.mkdirs();
                } else {
                    extractFile(zipInputStream, filePath);
                }

                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.close();

            return ResponseEntity.ok("File successfully extracted");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error occurred while extracting file: " + e.getMessage());
        }
    }

    private void extractFile(ZipInputStream zipInputStream, String filePath) throws IOException {
        new File(filePath).getParentFile().mkdirs();
        byte[] bytes = new byte[1024];
        int length;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            while ((length = zipInputStream.read(bytes)) >= 0) {
                fos.write(bytes, 0, length);
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipFileExtractorService.class, args);
    }
}
