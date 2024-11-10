package Login;

import DataBase.DatabaseConnect;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateLogin {

    public boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ? AND status = 'active'";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username); // Assuming username is email
            stmt.setString(2, password); // Password should ideally be hashed

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // If there's a result, login is successful
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
            return false;
        }
    }
}
