package Hotel.repository;

import Hotel.entity.User;

public interface UserRepository {
    void save(User user);  // creates a new user
}
