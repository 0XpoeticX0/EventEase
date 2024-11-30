package UserDashboard;

import DataBase.DatabaseConnect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

public class EventBookingHelper {

    // Fetch events booked by the user (u_id is a String now)
    public static Set<BookedEvent> getBookedEvents(String u_id) {
        Set<BookedEvent> bookedEvents = new HashSet<>();
        String query = "SELECT " +
                "    events.e_id AS eventId, " +
                "    events.name AS eventName, " +
                "    events.description AS eventDescription, " +
                "    events.location AS eventLocation, " +
                "    events.price AS eventPrice, " +
                "    events.image AS eventImage, " +
                "    bookings.bookingId AS b_id, " +
                "    bookings.eventDate AS bookingDate, " +
                "    bookings.price AS bookingPrice " +
                "FROM " +
                "    bookings " +
                "INNER JOIN " +
                "    events ON bookings.eventId = events.e_id " +
                "WHERE " +
                "    bookings.userId = ?";

        try (Connection connection = DatabaseConnect.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, u_id); // Set the userId (u_id as String)
            ResultSet rs = stmt.executeQuery();

            // Add all booked events to the set
            while (rs.next()) {
                BookedEvent event = new BookedEvent(
                        rs.getString("eventId"),
                        rs.getString("eventName"),
                        rs.getString("eventDescription"),
                        rs.getString("eventLocation"),
                        rs.getDouble("eventPrice"),
                        rs.getString("eventImage"),
                        rs.getString("bookingDate"),
                        rs.getDouble("bookingPrice"),
                        rs.getString("b_id"));
                bookedEvents.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedEvents; // Return the set of BookedEvent objects
    }

    // Fetch the booking date for a specific user's booking of an event
    // Fetch the booking date for a specific bookingId and eventId
    public static String getBookingDate(String bookingId, String eventId) {
        String eventDate = null;
        String query = "SELECT eventDate FROM bookings WHERE bookingId = ? AND eventId = ?"; // Query by bookingId and
                                                                                             // eventId

        try (Connection connection = DatabaseConnect.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, bookingId); // Set the bookingId
            stmt.setString(2, eventId); // Set the eventId

            ResultSet rs = stmt.executeQuery();

            // If there's a result, fetch the eventDate
            if (rs.next()) {
                Date date = rs.getDate("eventDate");

                // Format the date as per your desired format (e.g., dd/MM/yyyy)
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                eventDate = dateFormat.format(date); // Format to String
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventDate; // Return the formatted event date as a String
    }

    // Delete a booking from the database
    public static void deleteBooking(String bookingId, String userId) {
        String deleteQuery = "DELETE FROM bookings WHERE userId = ? AND bookingId = ?";

        try (Connection connection = DatabaseConnect.getConnection();
                PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {

            stmt.setString(1, userId); // Set the userId
            stmt.setString(2, bookingId); // Set the eventId

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Event successfully Canceled!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting booking.");
        }
    }

    // Fetch the eventId for a specific bookingId
    public static String getEventIdFromBooking(String bookingId) {
        String eventId = null;
        String query = "SELECT eventId FROM bookings WHERE bookingId = ?"; // Adjusted to get eventId based on bookingId

        try (Connection connection = DatabaseConnect.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, bookingId); // Set the bookingId
            ResultSet rs = stmt.executeQuery();

            // Check if we have a result
            if (rs.next()) {
                eventId = rs.getString("eventId"); // Fetch the eventId associated with this bookingId
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventId; // Return the eventId
    }

}
