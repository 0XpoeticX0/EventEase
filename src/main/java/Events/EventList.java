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
    public final List<Event> events;

    public EventList() {
        events = new ArrayList<>();
        initializeEventsFromDatabase(); // Fetch events from the database
    }

    // Method to initialize event data from the database
    private void initializeEventsFromDatabase() {
        try (Connection connection = DatabaseConnect.getConnection();
                Statement statement = connection.createStatement()) {

            // Retrieve event data from the database
            String query = "SELECT * FROM events";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String e_id = resultSet.getString("e_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                int price = resultSet.getInt("price");
                String imagePath = resultSet.getString("image");
                String status = resultSet.getString("status"); // Fetch the status

                // Create an Event object and add it to the list
                events.add(new Event(e_id, name, imagePath, description, location, price, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a subset of events (limited to a specified count)
    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getEvents(int count) {
    List<Event> activeEvents = new ArrayList<>();
    for (Event event : events) {
        if ("active".equalsIgnoreCase(event.getStatus())) {
            activeEvents.add(event);
        }
        // Stop adding if we reach the requested count
        if (activeEvents.size() >= count) {
            break;
        }
    }
    return activeEvents; // If count exceeds the size, this will just return all active events
}

}
