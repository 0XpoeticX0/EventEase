package UserDashboard;

import DataBase.DatabaseConnect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

public class EventBookingHelper {

    // Fetch events booked by the user (u_id is a String now)
    public static Set<String> getBookedEventIds(String u_id) {
        Set<String> bookedEventIds = new HashSet<>();
        String query = "SELECT eventId FROM bookings WHERE userId = ?";

        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, u_id);  // Set the userId (u_id as String)
            ResultSet rs = stmt.executeQuery();

            // Add all eventId to the set (stored as String)
            while (rs.next()) {
                bookedEventIds.add(rs.getString("eventId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedEventIds;
    }

    // Fetch the booking date for a specific user's booking of an event
    public static String getBookingDate(String userId, String eventId) {
        String eventDate = null;
        String query = "SELECT eventDate FROM bookings WHERE userId = ? AND eventId = ?"; // Adjusted column name to eventDate

        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set the userId and eventId as parameters
            stmt.setString(1, userId); // Assuming userId is a String
            stmt.setString(2, eventId); // Assuming eventId is a String

            // Execute the query and retrieve the result
            ResultSet rs = stmt.executeQuery();

            // Check if we have a result
            if (rs.next()) {
                // Fetch the eventDate from the result set
                Date date = rs.getDate("eventDate");

                // Format the date as per your desired format (e.g., dd/MM/yyyy)
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                eventDate = dateFormat.format(date); // Format the date to the desired format
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventDate; // Return the formatted event date as a String
    }


    // Delete a booking from the database
    public static void deleteBooking(String userId, String eventId) {
        String deleteQuery = "DELETE FROM bookings WHERE userId = ? AND eventId = ?";

        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {

            stmt.setString(1, userId); // Set the userId
            stmt.setString(2, eventId); // Set the eventId

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Event successfully Canceled!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting booking.");
        }
    }
}
