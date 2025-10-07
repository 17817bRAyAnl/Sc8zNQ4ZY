// 代码生成时间: 2025-10-08 00:00:20
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.HashMap;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class MedicationInteractionService {

    private final MedicationInteractionRepository medicationInteractionRepository;

    public MedicationInteractionService(MedicationInteractionRepository medicationInteractionRepository) {
        this.medicationInteractionRepository = medicationInteractionRepository;
    }

    @GetMapping("/checkInteraction")
    public ResponseEntity<Map<String, String>> checkMedicationInteraction(@RequestParam String drug1, @RequestParam String drug2) {
        try {
            Map<String, String> interactionInfo = medicationInteractionRepository.findInteractionInfo(drug1, drug2);
            if (interactionInfo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new HashMap<>(){
                        {
                            put("error", "No interaction found for the given drugs");
                        }
                    });
            }
            return ResponseEntity.ok(interactionInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new HashMap<>(){
                    {
                        put("error", e.getMessage());
                    }
                });
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + e.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(MedicationInteractionService.class, args);
    }
}
