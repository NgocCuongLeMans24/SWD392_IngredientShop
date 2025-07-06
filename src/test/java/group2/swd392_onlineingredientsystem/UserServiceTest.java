package group2.swd392_onlineingredientsystem;
import group2.swd392_onlineingredientsystem.service.UserService;


import group2.swd392_onlineingredientsystem.model.User;
import group2.swd392_onlineingredientsystem.repository.UserRepository;
import group2.swd392_onlineingredientsystem.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testRegisterAndLoginSuccess() throws Exception {
        // Tạo user mới
        User user = new User();
        user.setUsername("testuser123");
        user.setPassword("testpass123");
        user.setEmail("testuser123@example.com");

        User savedUser = userService.register(user);

        assertNotNull(savedUser.getUserId());
        assertEquals("testuser123", savedUser.getUsername());

        // Kiểm tra login
        Optional<User> loginResult = userService.login("testuser123", "testpass123");
        assertTrue(loginResult.isPresent());
        assertEquals("testuser123", loginResult.get().getUsername());
    }

    @Test
    public void testRegisterWithExistingUsername() {
        User user = new User();
        user.setUsername("baker_khanh"); // đã có sẵn trong DB
        user.setPassword("anypass");
        user.setEmail("another_email@example.com");

        Exception exception = assertThrows(Exception.class, () -> {
            userService.register(user);
        });

        assertTrue(exception.getMessage().contains("Username already exists"));
    }
}
