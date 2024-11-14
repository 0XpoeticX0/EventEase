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
                        return true;  // Login successful
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
        }

        return false;  // If the username or password is incorrect
    }
}
