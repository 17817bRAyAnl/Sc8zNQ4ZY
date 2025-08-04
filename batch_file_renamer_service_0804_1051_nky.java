// 代码生成时间: 2025-08-04 10:51:10
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/rename")
public class BatchFileRenamerService {

    private static final String DIRECTORY_PATH = "/path/to/your/directory"; // Change this to the directory path where files are located

    @PostMapping
    public ResponseEntity<?> renameFiles(@RequestBody List<RenameRequest> renameRequests) {
        try {
            renameRequests.forEach(request -> {
                File oldFile = new File(DIRECTORY_PATH + File.separator + request.getOldName());
                File newFile = new File(DIRECTORY_PATH + File.separator + request.getNewName());

                if (oldFile.exists() && !newFile.exists()) {
                    Files.move(Paths.get(oldFile.getPath()), Paths.get(newFile.getPath()));
                } else {
                    throw new FileRenameException("File does not exist or new file name already exists");
                }
            });

            return ResponseEntity.ok("Files renamed successfully");
        } catch (FileRenameException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }

    static class FileRenameException extends RuntimeException {
        public FileRenameException(String message) {
            super(message);
        }
    }

    static class RenameRequest {
        private String oldName;
        private String newName;

        public String getOldName() {
            return oldName;
        }

        public void setOldName(String oldName) {
            this.oldName = oldName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }
}