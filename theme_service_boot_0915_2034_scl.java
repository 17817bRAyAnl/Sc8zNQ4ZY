// 代码生成时间: 2025-09-15 20:34:21
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@SpringBootApplication
public class ThemeServiceBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThemeServiceBootApplication.class, args);
    }
}

@RestController
@RequestMapping("/api")
class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("/theme/{theme}")
    public ResponseEntity<?> changeTheme(@PathVariable String theme) {
        try {
            themeService.setTheme(theme);
            return ResponseEntity.ok().body("Theme set to: " + theme);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

class ThemeService {
    private String theme;

    public void setTheme(String theme) {
        if (!isValidTheme(theme)) {
            throw new IllegalArgumentException("Invalid theme: " + theme);
        }
        this.theme = theme;
    }

    private boolean isValidTheme(String theme) {
        return "dark".equals(theme) || "light".equals(theme);
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(IllegalArgumentException.class)
class ThemeExceptionHandler extends RuntimeException {
    public ThemeExceptionHandler(String message) {
        super(message);
    }
}