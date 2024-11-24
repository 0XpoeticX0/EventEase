package CurrentUser;

public class CurrentUser {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String image;
    private int age;
    private String role;

    // Constructor
    public CurrentUser(String firstName, String lastName, String email, String mobileNumber, String image, int age,
            String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.image = image;
        this.age = age;
        this.role = role;
    }

    // Getters
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

}
