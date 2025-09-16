// 代码生成时间: 2025-09-17 00:27:55
@SpringBootApplication
public class ScheduledTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledTaskApplication.class, args);
    }
}

/**
 * Scheduled task service with annotations for scheduling and exception handling.
 */
@Service
public class ScheduledTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);

    // Example of a scheduled task
    @Scheduled(cron = "0 * * * * ?") // Every minute
    public void scheduledTask() {
        try {
            // Simulate some task here
            logger.info("Scheduled task is running...");
        } catch (Exception e) {
            // Handle exceptions
            logger.error("Error while executing scheduled task", e);
        }
    }
}

/**
 * REST controller with a single endpoint to manually trigger the scheduled task.
 */
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduledTaskService scheduledTaskService;

    public ScheduleController(ScheduledTaskService scheduledTaskService) {
        this.scheduledTaskService = scheduledTaskService;
    }

    @PostMapping("/run")
    public ResponseEntity<String> runScheduledTask() {
        try {
            scheduledTaskService.scheduledTask();
            return ResponseEntity.ok("Scheduled task has been triggered manually.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to trigger scheduled task manually.");
        }
    }
}

/**
 * Global exception handler to handle any uncaught exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}