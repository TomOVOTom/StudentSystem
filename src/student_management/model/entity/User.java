package student_management.model.entity;

public class User implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String role; // "admin" 或 "user"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}