package Events;

import DataBase.DatabaseConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Class to manage the list of events
public class EventList {
    // No need to initialize events in the constructor
    public final List<Event> events;

    public EventList() {
        events = new ArrayList<>();
    }

    // Method to get all events from the database
    public List<Event> getEvents() {
        List<Event> allEvents = new ArrayList<>();
        try (Connection connection = DatabaseConnect.getConnection();
             Statement statement = connection.createStatement()) {

            // Query to fetch all events
            String query = "SELECT * FROM events";
            ResultSet resultSet = statement.executeQuery(query);

            // Populate the list with events from the database
            while (resultSet.next()) {
                String e_id = resultSet.getString("e_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                int price = resultSet.getInt("price");
                String imagePath = resultSet.getString("image");
                String status = resultSet.getString("status"); // Fetch the status

                // Add the event to the list
                allEvents.add(new Event(e_id, name, imagePath, description, location, price, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEvents;
    }

    // Method to get a limited number of active events
    public List<Event> getEvents(int count) {
        List<Event> activeEvents = new ArrayList<>();
        try (Connection connection = DatabaseConnect.getConnection();
             Statement statement = connection.createStatement()) {

            // Query to fetch all events
            String query = "SELECT * FROM events";
            ResultSet resultSet = statement.executeQuery(query);

            // Populate the list with active events from the database
            while (resultSet.next()) {
                String e_id = resultSet.getString("e_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                int price = resultSet.getInt("price");
                String imagePath = resultSet.getString("image");
                String status = resultSet.getString("status"); // Fetch the status

                // Add only active events to the list
                if ("active".equalsIgnoreCase(status)) {
                    activeEvents.add(new Event(e_id, name, imagePath, description, location, price, status));
                }

                // Stop adding if the limit is reached
                if (activeEvents.size() >= count) {
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activeEvents;
    }
}
