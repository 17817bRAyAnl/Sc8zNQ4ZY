// 代码生成时间: 2025-08-15 06:32:27
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class ConfigFileManager {

    @Value("classpath:application.properties")
    private Resource resource;

    public static void main(String[] args) {
        SpringApplication.run(ConfigFileManager.class, args);
    }

    @RestController
    @RequestMapping("/config")
    class ConfigFileController implements WebMvcConfigurer {

        private final ResourceLoader resourceLoader;

        public ConfigFileController(ResourceLoader resourceLoader) {
            this.resourceLoader = resourceLoader;
        }

        @GetMapping("/load")
        public ResponseEntity<String> loadConfigFile() {
            try {
                Path path = resource.getFile().toPath();
                String content = new String(Files.readAllBytes(path));
                return ResponseEntity.ok(content);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file");
            }
        }

        @ExceptionHandler(IOException.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public void handleIOException(IOException e) {
            // Log and handle exception
        }

        @Override
        public void configureHandlerExceptionResolvers(HandlerExceptionResolver resolver) {
            // You can add custom exception resolvers here
        }
    }
}
