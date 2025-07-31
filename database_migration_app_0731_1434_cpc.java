// 代码生成时间: 2025-07-31 14:34:52
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import java.sql.Connection;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
@RequestMapping("/api/migrations")
public class DatabaseMigrationApp {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @GetMapping
    public ResponseEntity<String> migrateDatabase() {
        Resource resource = new ClassPathResource("migration.sql");
        DatabasePopulator populator = new ResourceDatabasePopulator(resource);
        try (Connection connection = dataSource.getConnection();
             TransactionTemplate template = new TransactionTemplate(transactionManager)) {
            template.execute(status -> DatabasePopulatorUtils.execute(populator, connection));
            return ResponseEntity.ok("Database migration successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database migration failed: " + e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(DatabaseMigrationApp.class, args);
    }
}