package AdminDashboard;

public class User {

    private int u_id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String status;
    private String image;

    // Constructor
    public User(int u_id, String firstName, String lastName, String email, String role, String status, String image) {
        this.u_id = u_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.image = image;
    }

    // Getters for non-confidential fields
    public int getU_id() {
        return u_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    // Method to return the full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
