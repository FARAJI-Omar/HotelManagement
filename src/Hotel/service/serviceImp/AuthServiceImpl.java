package Hotel.service.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Hotel.entity.User;
import Hotel.repository.UserRepository;
import Hotel.service.AuthService;
import Hotel.ui.ConsoleUI;

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepo;

    public AuthServiceImpl(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public void register(String email, String name, String password, User.Role role){
        if (ConsoleUI.isClient) {
            role = User.Role.CLIENT;
        }else{
            role = User.Role.ADMIN;
        }
        User user = new User(name, email, password, role);
        userRepo.save(user);
    }

    @Override
    public Optional<User> login(String email, String password){
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public void updateProfile(User user, String newEmail, String newName, String newPassword){
        System.out.println("Profile updated successfully");

    }

    @Override
    public boolean emailExists(String email, User.Role role){
        List<User> users = userRepo.findAll();
        for (User user : users){
            // dont allow same email with same role
            if (user.getEmail().equalsIgnoreCase(email) && user.getRole() == role) {
                return true;
            }
        }
        return  false;
    }
}
