package Events;

import Events.Event;
import DataBase.DatabaseConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Class to manage the list of events
public class EventList {
    private final List<Event> events;

    public EventList() {
        events = new ArrayList<>();
        initializeEventsFromDatabase(); // Fetch events from the database
    }    

    // Method to initialize event data from the database
    private void initializeEventsFromDatabase() {
        try (Connection connection = DatabaseConnect.getConnection();
             Statement statement = connection.createStatement()) {
    
            // Query to count rows in the events table
            String countQuery = "SELECT COUNT(*) AS rowCount FROM events";
            ResultSet countResultSet = statement.executeQuery(countQuery);
    
            if (countResultSet.next() && countResultSet.getInt("rowCount") == 0) {
                System.out.println("The events table is empty. Inserting default data.");
    
                // Insert default data if table is empty
                String insertQuery = "INSERT INTO events (name, description, location, price, image) VALUES " +
                    "('Music Concert', 'A live music event with various artists performing.', 'New York', 50, 'src/main/java/Resorces/Images/1.jfif')," +
                    "('Art Exhibition', 'A showcase of contemporary art by local artists.', 'Los Angeles', 30, 'src/main/java/Resorces/Images/2.jfif')," +
                    "('Tech Conference', 'A gathering of tech enthusiasts and professionals.', 'San Francisco', 100, 'src/main/java/Resorces/Images/3.jfif')," +
                    "('Food Festival', 'A variety of local and international cuisines.', 'Chicago', 20, 'src/main/java/Resorces/Images/4.jfif')," +
                    "('Book Fair', 'A collection of books from different genres and authors.', 'Seattle', 10, 'src/main/java/Resorces/Images/5.jfif')," +
                    "('Theater Play', 'A dramatic performance showcasing talent on stage.', 'Boston', 25, 'src/main/java/Resorces/Images/6.jfif')," +
                    "('Film Screening', 'An evening of classic and contemporary films.', 'Austin', 15, 'src/main/java/Resorces/Images/7.jfif')," +
                    "('Charity Gala', 'A fundraising event for local charities.', 'Miami', 200, 'src/main/java/Resorces/Images/8.jfif')," +
                    "('Dance Workshop', 'Learn different dance styles from professional instructors.', 'Las Vegas', 40, 'src/main/java/Resorces/Images/9.jfif')," +
                    "('Sports Day', 'A fun-filled day of various sports and activities.', 'Denver', 5, 'src/main/java/Resorces/Images/10.jfif')," +
                    "('Fashion Show', 'An exhibition of the latest fashion trends.', 'New Orleans', 75, 'src/main/java/Resorces/Images/11.jfif');";

    
                statement.executeUpdate(insertQuery);
            }
    
            // Retrieve event data from the database
            String query = "SELECT * FROM events";
            ResultSet resultSet = statement.executeQuery(query);
    
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                int price = resultSet.getInt("price");
                String imagePath = resultSet.getString("image");
                events.add(new Event(name, imagePath, description, location,price));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to get a subset of events (limited to a specified count)
    public List<Event> getEvents(int count) {
        return events.subList(0, Math.min(count, events.size()));
    }

    // Method to get all events
    public List<Event> getAllEvents() {
        return events;
    }
}
