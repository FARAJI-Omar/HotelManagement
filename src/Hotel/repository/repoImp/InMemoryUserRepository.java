package Hotel.repository.repoImp;

import Hotel.repository.UserRepository;
import Hotel.entity.User;
import java.util.*;

public class InMemoryUserRepository implements UserRepository {
    private final Map<UUID, User> users = new HashMap<>();
    // create a new user
    @Override
    public void save(User user){
        users.put(user.getId(), user);
    }

    // find user by id/email/all_users
    @Override
    public Optional<User> findById(UUID id){
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email){
        for (User user : users.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll(){
        return new ArrayList<>(users.values());
    }

    // update user data
    @Override
    public void update(User user){
        if (!users.containsKey(user.getId())) {
            throw new IllegalArgumentException("User with this Id not found");
        }
        users.put(user.getId(), user); // replaces the existing user data
        System.out.println("updated successfuly");
    }

    // delete user
    public void delete(User user){
        if (!users.containsKey(user.getId())) {
            throw new IllegalArgumentException("User with thhat Id not found");
        }
        users.remove(user.getId());
        System.out.println("Deleted successfuly");

    }


}
