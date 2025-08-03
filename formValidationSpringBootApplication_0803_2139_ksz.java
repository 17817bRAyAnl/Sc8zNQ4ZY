// 代码生成时间: 2025-08-03 21:39:40
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class FormValidationSpringBootApplication {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Form Validation API";
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateFormData(@Valid @RequestBody FormData formData) {
        // Logic to process the validated data
        return ResponseEntity.ok("Data is valid");
    }

    static class FormData {
        @NotNull(message = "Name cannot be null")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        return "Validation error: " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }

    public static void main(String[] args) {
        SpringApplication.run(FormValidationSpringBootApplication.class, args);
    }
}
