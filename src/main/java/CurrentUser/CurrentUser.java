package CurrentUser;

public class CurrentUser {
    private String u_id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String image;
    private int age;
    private String role;
    private String status;

    // Constructor
    public CurrentUser(String u_id, String firstName, String lastName, String email, String mobileNumber, String image,
            int age, String status,
            String role) {
        this.u_id = u_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.image = image;
        this.age = age;
        this.role = role;
        this.status = status;
    }

    // Getters
    public String getUserId() {
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getImage() {
        return image;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    // Setters (Optional, if needed)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
