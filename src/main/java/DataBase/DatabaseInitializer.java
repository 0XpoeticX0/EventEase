package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import CurrentUser.CurrentUser;
import utils.PasswordUtils;

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
                        image VARCHAR(255) DEFAULT 'src/main/java/Resorces/Images/demoUser.png',
                        islogin BOOLEAN DEFAULT false,
                        last_login TIMESTAMP DEFAULT NULL,
                        last_logout TIMESTAMP DEFAULT NULL,
                        isDeleted BOOLEAN NOT NULL DEFAULT FALSE
                    );
                """;

        String createEventsTable = """
                    CREATE TABLE IF NOT EXISTS events (
                        e_id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        description TEXT,
                        location VARCHAR(255) NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,
                        image VARCHAR(255),
                        status VARCHAR(50) NOT NULL DEFAULT 'active',  -- Added status column with default value 'active'
                        isDeleted BOOLEAN NOT NULL DEFAULT FALSE
                    );
                """;

        String createBookingsTable = """
                    CREATE TABLE IF NOT EXISTS bookings (
                        bookingId INT AUTO_INCREMENT PRIMARY KEY,
                        userId INT NOT NULL,
                        eventId INT NOT NULL,
                        eventDate DATE NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,
                        FOREIGN KEY (userId) REFERENCES users(u_id) ON DELETE CASCADE,
                        FOREIGN KEY (eventId) REFERENCES events(e_id) ON DELETE CASCADE
                    );
                """;

        String insertQuery = "INSERT INTO events (name, description, location, price, image, status) VALUES "
                + "('Music Concert', 'A live music event with various artists performing.', 'New York', 50, 'src/main/java/Resorces/Images/1.jfif', 'active'),"
                + "('Art Exhibition', 'A showcase of contemporary art by local artists.', 'Los Angeles', 30, 'src/main/java/Resorces/Images/2.jfif', 'active'),"
                + "('Tech Conference', 'A gathering of tech enthusiasts and professionals.', 'San Francisco', 100, 'src/main/java/Resorces/Images/3.jfif', 'active'),"
                + "('Food Festival', 'A variety of local and international cuisines.', 'Chicago', 20, 'src/main/java/Resorces/Images/4.jfif', 'active'),"
                + "('Book Fair', 'A collection of books from different genres and authors.', 'Seattle', 10, 'src/main/java/Resorces/Images/5.jfif', 'active'),"
                + "('Theater Play', 'A dramatic performance showcasing talent on stage.', 'Boston', 25, 'src/main/java/Resorces/Images/6.jfif', 'active'),"
                + "('Film Screening', 'An evening of classic and contemporary films.', 'Austin', 15, 'src/main/java/Resorces/Images/7.jfif', 'active'),"
                + "('Charity Gala', 'A fundraising event for local charities.', 'Miami', 200, 'src/main/java/Resorces/Images/8.jfif', 'active'),"
                + "('Dance Workshop', 'Learn different dance styles from professional instructors.', 'Las Vegas', 40, 'src/main/java/Resorces/Images/9.jfif', 'active'),"
                + "('Sports Day', 'A fun-filled day of various sports and activities.', 'Denver', 5, 'src/main/java/Resorces/Images/10.jfif', 'active'),"
                + "('Fashion Show', 'An exhibition of the latest fashion trends.', 'New Orleans', 75, 'src/main/java/Resorces/Images/11.jfif', 'active');";

        String insertAdminUser = """
                INSERT INTO users (firstname, lastname, email, mobileNumber, password, age, role, status)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """;

        String adminEmail = DatabaseConfig.getAdminEmail();
        String adminPassword = DatabaseConfig.getAdminPassword();

        String hashedPassword = PasswordUtils.hashPassword(adminPassword);

        try (Connection connection = DatabaseConnect.getConnection(); Statement statement = connection.createStatement()) {

            // Execute table creation queries
            statement.executeUpdate(createEventsTable);
            statement.executeUpdate(createUsersTable);
            statement.executeUpdate(createBookingsTable);

            // Check if an admin exists; if not, create it
            String checkAdminQuery = "SELECT COUNT(*) AS rowCount FROM users WHERE role = 'admin'";
            try (ResultSet resultSet = statement.executeQuery(checkAdminQuery)) {
                if (resultSet.next() && resultSet.getInt("rowCount") == 0) {
                    try (PreparedStatement adminStmt = connection.prepareStatement(insertAdminUser)) {
                        adminStmt.setString(1, "Utsho");
                        adminStmt.setString(2, "Roy");
                        adminStmt.setString(3, adminEmail);
                        adminStmt.setString(4, "1234567890");
                        adminStmt.setString(5, hashedPassword);
                        adminStmt.setInt(6, 30); // Age
                        adminStmt.setString(7, "admin");
                        adminStmt.setString(8, "active");
                        adminStmt.executeUpdate();
                        System.out.println("Default admin user created.");
                    }
                }
            }

            // Check if events table is empty, and if so, insert default events
            String countQuery = "SELECT COUNT(*) AS rowCount FROM events";
            ResultSet countResultSet = statement.executeQuery(countQuery);
            if (countResultSet.next() && countResultSet.getInt("rowCount") == 0) {
                System.out.println("The events table is empty. Inserting default data.");
                statement.executeUpdate(insertQuery);
            }

            // Query to check if there are any users who are logged in
            String getLoggedInUsersQuery = "SELECT * FROM users WHERE islogin = true LIMIT 1";
            try (ResultSet loggedInUsersResultSet = statement.executeQuery(getLoggedInUsersQuery)) {
                if (loggedInUsersResultSet.next()) {
                    // If a logged-in user is found, create a CurrentUser object
                    CurrentUser currentUser = new CurrentUser(
                            loggedInUsersResultSet.getString("u_id"),
                            loggedInUsersResultSet.getString("firstname"),
                            loggedInUsersResultSet.getString("lastname"),
                            loggedInUsersResultSet.getString("email"),
                            loggedInUsersResultSet.getString("mobileNumber"),
                            loggedInUsersResultSet.getString("image"),
                            loggedInUsersResultSet.getInt("age"),
                            loggedInUsersResultSet.getString("role"));

                    // Set the current user in the LoggedInUser singleton
                    LoggedInUser.getInstance().setCurrentUser(currentUser);
                }
            }

            System.out.println("Database tables initialized successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error initializing database tables.");
        }
    }
}
