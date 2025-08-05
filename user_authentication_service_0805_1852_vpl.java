// 代码生成时间: 2025-08-05 18:52:14
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootApplication
@RestController
@EnableWebSecurity
public class UserAuthenticationService extends WebSecurityConfigurerAdapter {

    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                return ResponseEntity.ok("User is authenticated");
            } else {
                return ResponseEntity.status(401).body("User is not authenticated");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Authentication error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());
            if (userDetails.getPassword().equals(credentials.getPassword())) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok("User authenticated successfully");
            } else {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(404).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Login error: " + e.getMessage());
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // Implement your user details loading logic here
                // For demonstration purposes, returning a dummy user
                return User.withDefaultPasswordEncoder()
                    .username(username)
                    .password("password")
                    .roles("USER")
                    .build();
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(UserAuthenticationService.class, args);
    }

    static class UserCredentials {
        String username;
        String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
