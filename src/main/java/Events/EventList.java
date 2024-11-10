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
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                int price = resultSet.getInt("price");
                String imagePath = resultSet.getString("image");
                events.add(new Event(name, imagePath, description, location, price));
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
        return events.subList(0, count);
    }
}
