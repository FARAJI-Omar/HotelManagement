package Hotel.service;

import java.util.List;
import java.util.Optional;

import Hotel.entity.User;

public interface AuthService {
    void register(String email, String name, String password, User.Role role);

    Optional<User> login(String email, String password);

    void updateProfile(User user, String newEmail, String newName, String newPassword);

    boolean emailExists(String email, User.Role role);
}
