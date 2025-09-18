package Hotel.repository;

import java.util.*;

import Hotel.entity.User;

public interface UserRepository {
    // CREATE a new user
    void save(User user); 

    // READ, get a user by ID or email or get all users
    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    // UPDATE existing user
    void update(User user);

    // DELETE a user
    void delete(User user);
}
