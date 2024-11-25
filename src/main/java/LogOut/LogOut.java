/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogOut;

import CurrentUser.CurrentUser;
import DataBase.DatabaseConnect;
import DataBase.LoggedInUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author MaHir0
 */
public class LogOut {
    static CurrentUser currentUser = LoggedInUser.getInstance().getCurrentUser();
    public static void logOut(){
        if (currentUser != null) {
                // Prepare the SQL query to update the user's login status and last_logout
                // timestamp
                String updateQuery = "UPDATE users SET islogin = false, last_logout = NOW() WHERE email = ?";

                try (Connection conn = DatabaseConnect.getConnection();
                        PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

                    // Set the email parameter (from currentUser) in the query
                    stmt.setString(1, currentUser.getEmail());

                    // Execute the update query
                    int rowsUpdated = stmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("User logout successfully updated in the database.");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Error updating logout status in the database.");
                }

                // Clear the logged-in user information by resetting the singleton
                LoggedInUser.getInstance().resetCurrentUser();
            }
    }
}
