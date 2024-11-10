package DataBase;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        String createUsersTable = """
                    CREATE TABLE IF NOT EXISTS users (
                        u_id INT AUTO_INCREMENT PRIMARY KEY,
                        firstname VARCHAR(100) NOT NULL,
                        lastname VARCHAR(100) NOT NULL,
                        email VARCHAR(255) UNIQUE NOT NULL,
                        mobileNumber VARCHAR(15),
                        password VARCHAR(255) NOT NULL,
                        age INT CHECK (age >= 18),
                        role ENUM('admin', 'user') NOT NULL DEFAULT 'user',
                        status ENUM('active', 'inactive') NOT NULL DEFAULT 'active',
                        image VARCHAR(255)
                    );
                """;

        String createEventsTable = """
                    CREATE TABLE IF NOT EXISTS events (
                        e_id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        description TEXT,
                        location VARCHAR(255) NOT NULL,
                        price INT CHECK (price >= 0),
                        image VARCHAR(255)
                    );
                """;

        String insertQuery = "INSERT INTO events (name, description, location, price, image) VALUES " +
                "('Music Concert', 'A live music event with various artists performing.', 'New York', 50, 'src/main/java/Resorces/Images/1.jfif'),"
                +
                "('Art Exhibition', 'A showcase of contemporary art by local artists.', 'Los Angeles', 30, 'src/main/java/Resorces/Images/2.jfif'),"
                +
                "('Tech Conference', 'A gathering of tech enthusiasts and professionals.', 'San Francisco', 100, 'src/main/java/Resorces/Images/3.jfif'),"
                +
                "('Food Festival', 'A variety of local and international cuisines.', 'Chicago', 20, 'src/main/java/Resorces/Images/4.jfif'),"
                +
                "('Book Fair', 'A collection of books from different genres and authors.', 'Seattle', 10, 'src/main/java/Resorces/Images/5.jfif'),"
                +
                "('Theater Play', 'A dramatic performance showcasing talent on stage.', 'Boston', 25, 'src/main/java/Resorces/Images/6.jfif'),"
                +
                "('Film Screening', 'An evening of classic and contemporary films.', 'Austin', 15, 'src/main/java/Resorces/Images/7.jfif'),"
                +
                "('Charity Gala', 'A fundraising event for local charities.', 'Miami', 200, 'src/main/java/Resorces/Images/8.jfif'),"
                +
                "('Dance Workshop', 'Learn different dance styles from professional instructors.', 'Las Vegas', 40, 'src/main/java/Resorces/Images/9.jfif'),"
                +
                "('Sports Day', 'A fun-filled day of various sports and activities.', 'Denver', 5, 'src/main/java/Resorces/Images/10.jfif'),"
                +
                "('Fashion Show', 'An exhibition of the latest fashion trends.', 'New Orleans', 75, 'src/main/java/Resorces/Images/11.jfif');";

        try (Connection connection = DatabaseConnect.getConnection();
                Statement statement = connection.createStatement()) {

            // Execute table creation queries
            statement.executeUpdate(createUsersTable);
            statement.executeUpdate(createEventsTable);
            statement.executeUpdate(insertQuery);
            System.out.println("Database tables initialized successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error initializing database tables.");
        }
    }
}
