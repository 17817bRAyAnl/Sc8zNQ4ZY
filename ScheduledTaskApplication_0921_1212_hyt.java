// 代码生成时间: 2025-09-21 12:12:34
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class ScheduledTaskApplication {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        SpringApplication.run(ScheduledTaskApplication.class, args);
    }

    @RestController
    class TaskController {

        @GetMapping("/start")
        public String startTask() {
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    performTask();
                } catch (Exception e) {
                    handleException(e);
                }
            }, 0, 5, TimeUnit.SECONDS); // 每5秒执行一次
            return "Task started";
        }

        private void performTask() {
            // 实现定时任务逻辑
            System.out.println("Task executed at: " + new Date());
        }

        private void handleException(Exception e) {
            // 实现异常处理逻辑
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
