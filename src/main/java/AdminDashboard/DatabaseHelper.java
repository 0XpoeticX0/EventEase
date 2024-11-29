package AdminDashboard;

import DataBase.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    // Update user status method, userId is an integer
    public static void updateUserStatus(int userId, String status) {
        String updateStatusQuery = "UPDATE users SET status = ? WHERE u_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateStatusQuery)) {

            stmt.setString(1, status); // Set new status ("active" or "inactive")
            stmt.setInt(2, userId);    // Set userId
            stmt.executeUpdate();      // Execute the update

        } catch (SQLException e) {
            System.err.println("Error updating user status: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update event status method, eventId is a String
    public static void updateEventStatus(String eventId, String status) {
        String updateStatusQuery = "UPDATE events SET status = ? WHERE e_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateStatusQuery)) {

            stmt.setString(1, status); // Set new status ("active" or "inactive")
            stmt.setString(2, eventId); // Set eventId
            stmt.executeUpdate();       // Execute the update

        } catch (SQLException e) {
            System.err.println("Error updating event status: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete event method, eventId is a String
    public static void deleteEvent(String eventId) {
        String deleteQuery = "DELETE FROM events WHERE e_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {

            stmt.setString(1, eventId); // Set eventId
            stmt.executeUpdate();       // Execute the delete

        } catch (SQLException e) {
            System.err.println("Error deleting event: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
