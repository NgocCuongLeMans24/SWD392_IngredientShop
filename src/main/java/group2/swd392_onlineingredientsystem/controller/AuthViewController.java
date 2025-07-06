package group2.swd392_onlineingredientsystem.controller;

import group2.swd392_onlineingredientsystem.model.User;
import group2.swd392_onlineingredientsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AuthViewController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user,
                                  RedirectAttributes redirectAttributes) {
        try {
            userService.register(user);
            redirectAttributes.addFlashAttribute("message", "✅ Đăng ký thành công!");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "❌ Đăng ký thất bại: " + e.getMessage());
            return "redirect:/register";
        }
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes) {
        Optional<User> user = userService.login(username, password);
        if (user.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "✅ Đăng nhập thành công!");
            return "redirect:/dashboard"; // hoặc trang chính
        } else {
            redirectAttributes.addFlashAttribute("error", "❌ Sai tên đăng nhập hoặc mật khẩu");
            return "redirect:/login";
        }
    }
}
