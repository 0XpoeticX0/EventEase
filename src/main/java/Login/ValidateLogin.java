package Login;

import DataBase.DatabaseConnect;
import javax.swing.JOptionPane;

import CurrentUser.CurrentUser;
import DataBase.LoggedInUser; // Import the LoggedInUser singleton

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.PasswordUtils;

public class ValidateLogin {
    // SQL query to select password for an active user
    String query = "SELECT u_id,password, firstname, lastname, email, mobileNumber, image, age,status, role FROM users WHERE email = ? AND status = 'active'";

    // Static variable to store the logged-in user's email
    public static String loggedInUserEmail = null;

    // Method to validate the login
    public boolean validateLogin(String username, String password) {
        try (Connection conn = DatabaseConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set the email parameter (username) in the query
            stmt.setString(1, username);

            // Execute the query to fetch the user's stored password
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String dbPassword = rs.getString("password"); // Get the hashed password from DB

                    // Use BCrypt to verify the entered password against the stored hashed password
                    if (PasswordUtils.verifyPassword(password, dbPassword)) {
                        // Login successful, store the email
                        loggedInUserEmail = username;

                        // Update the database to set islogin = true
                        String updateLoginStatusQuery = "UPDATE users SET islogin = true, last_login = NOW() WHERE email = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateLoginStatusQuery)) {
                            updateStmt.setString(1, username);
                            updateStmt.executeUpdate();
                        }

                        // Create the CurrentUser object
                        CurrentUser loggedInUser = new CurrentUser(
                                rs.getString("u_id"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("email"),
                                rs.getString("mobileNumber"),
                                rs.getString("image"),
                                rs.getInt("age"),
                                rs.getString("status"),
                                rs.getString("role"));

                        // Set the current user in the LoggedInUser singleton
                        LoggedInUser.getInstance().setCurrentUser(loggedInUser);

                        return true; // Login successful
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
        }

        // If the username or password is incorrect, clear the email
        loggedInUserEmail = null;
        return false;
    }

    // Method to check if the user is logged in
    public static boolean isLoggedIn() {
        return loggedInUserEmail != null; // If email is not null, user is logged in
    }
}
