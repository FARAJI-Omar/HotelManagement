package Hotel.entity;

import java.util.UUID;

public class User {
    protected UUID id;
    protected String name;
    protected String email;
    protected Role role;
    protected String password;

    public enum Role {
        ADMIN,
        CLIENT
    }

    public User(String name, String email, String password, Role role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public String getPassword() { return password; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(Role role) { this.role = role; }
    public void setPassword(String password) { this.password = password; }

    // Helper method to check user's role 
    public boolean isAdmin() { return role == Role.ADMIN; }
    public boolean isClient() { return role == Role.CLIENT; }
}
