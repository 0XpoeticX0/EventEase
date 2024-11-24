package Login;

import DataBase.DatabaseConnect;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.PasswordUtils; // Import the PasswordUtils class

public class ValidateLogin {
    // SQL query to select password for an active user
    String query = "SELECT password FROM users WHERE email = ? AND status = 'active'";

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
                        return true;
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
