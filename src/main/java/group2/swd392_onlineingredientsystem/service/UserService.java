package group2.swd392_onlineingredientsystem.service;

import group2.swd392_onlineingredientsystem.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Integer id);

    void save(User user);

    void delete(Integer id);
}