// 代码生成时间: 2025-09-08 21:56:25
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import liquibase.exception.LiquibaseException;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.Contexts;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.ChangeSet;
import liquibase.resource.ResourceAccessor;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@RestController
@RequestMapping("/api/migrate")
public class DatabaseMigrationService {

    private final DataSource dataSource;

    public DatabaseMigrationService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping
    public ResponseEntity<String> migrateDatabase() throws LiquibaseException, SQLException {
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();
        Liquibase liquibase = new Liquibase("changelog.xml", resourceAccessor, dataSource);
        liquibase.update(new Contexts());
        return ResponseEntity.ok("Database migration completed successfully.");
    }

    @ExceptionHandler(LiquibaseException.class)
    public ResponseEntity<String> handleLiquibaseException(LiquibaseException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Liquibase exception occurred: " + e.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(DatabaseMigrationService.class, args);
    }
}
