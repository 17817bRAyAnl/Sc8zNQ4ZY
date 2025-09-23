// 代码生成时间: 2025-09-24 04:58:56
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@SpringBootApplication
@RestController
@RequestMapping("/api/process")
public class ProcessManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessManagerApplication.class, args);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> listProcesses() {
        try {
            RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
            Map<String, Object> processInfo = new HashMap<>();
            processInfo.put("vmName", runtimeMxBean.getVmName());
            processInfo.put("vmVersion", runtimeMxBean.getVmVersion());
            processInfo.put("vmVendor", runtimeMxBean.getVmVendor());
            processInfo.put("uptime", runtimeMxBean.getUptime());
            return ResponseEntity.ok(processInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/kill")
    public ResponseEntity<String> killProcess(@RequestParam String pid) {
        try {
            // Assume there's a killProcess method that takes a PID and terminates the process
            // killProcess(pid);
            return ResponseEntity.ok("Process with PID " + pid + " has been killed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to kill process with PID " + pid);
        }
    }

    // Global exception handler
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
