// 代码生成时间: 2025-08-30 20:40:10
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartService {

    private List<String> cartItems = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartService.class, args);
    }

    @PostMapping("/add-item")
    public ResponseEntity<String> addItem(String item) {
        cartItems.add(item);
        return ResponseEntity.ok("Item added successfully");
    }

    @GetMapping("/items")
    public ResponseEntity<List<String>> getItems() {
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/remove-item")
    public ResponseEntity<String> removeItem(String item) {
        if (cartItems.contains(item)) {
            cartItems.remove(item);
            return ResponseEntity.ok("Item removed successfully");
        } else {
            return ResponseEntity.badRequest().body("Item not found in the cart");
        }
    }
}
