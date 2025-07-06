package group2.swd392_onlineingredientsystem.controller;

import group2.swd392_onlineingredientsystem.model.User;
import group2.swd392_onlineingredientsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try {
            userService.register(user);
            return "✅ Register successful!";
        } catch (Exception e) {
            return "❌ Register failed: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.login(username, password);
        return user.map(u -> "✅ Login successful! Hello, " + u.getUsername())
                .orElse("❌ Login failed: Invalid username or password");
    }
}
