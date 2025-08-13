// 代码生成时间: 2025-08-13 11:08:12
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
# 扩展功能模块
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class ScheduledTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledTaskApplication.class, args);
# FIXME: 处理边界情况
    }

    @RestController
    @RequestMapping("/api/scheduled")
    public class ScheduledTaskController {

        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        // 定时任务
        @Scheduled(fixedRate = 5000)
        public void reportCurrentTime() {
            System.out.println("Current Time : " + dateFormat.format(new Date()));
        }

        // REST API 用于触发定时任务
        @GetMapping("/trigger")
        public String triggerTask() {
            reportCurrentTime();
# NOTE: 重要实现细节
            return "Task triggered at: " + dateFormat.format(new Date());
        }
    }
}
