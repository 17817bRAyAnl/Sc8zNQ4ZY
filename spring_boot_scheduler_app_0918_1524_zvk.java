// 代码生成时间: 2025-09-18 15:24:32
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@SpringBootApplication
@EnableScheduling
@RestController
@RequestMapping("/api")
public class SpringBootApplication {

    private final TaskScheduler taskScheduler;

    public SpringBootApplication(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    // REST API to start a scheduled task
    @GetMapping("/startTask")
    public String startTask(@RequestParam String cronExpression) {
        try {
            ScheduledFuture<?> future = taskScheduler.scheduleAtFixedRate(() -> {
                // Task to be executed
                System.out.println("Task executed at: " + new Date());
            }, cronExpression);
            // Return success message
            return "Scheduled task started with cron expression: " + cronExpression;
        } catch (Exception e) {
            // Handle exceptions and return error message
            return "Error starting scheduled task: " + e.getMessage();
        }
    }

    // REST API to stop a scheduled task
    @GetMapping("/stopTask")
    public String stopTask(@RequestParam String taskName) {
        try {
            // Logic to stop the task
            // Since the actual task stopping logic is not provided, this is a placeholder.
            return "Scheduled task stopped: " + taskName;
        } catch (Exception e) {
            return "Error stopping scheduled task: " + e.getMessage();
        }
    }

    // Scheduled task example
    @Scheduled(fixedRate = 5000)
    public void scheduledTask() {
        System.out.println("Scheduled task executed at: " + new Date());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}

// Exception handling configuration
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping("/api")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("Global exception caught: " + e.getMessage());
    }
}
