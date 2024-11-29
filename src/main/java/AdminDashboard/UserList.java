package AdminDashboard;

import DataBase.DatabaseConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserList {
    private final List<User> users;

    public UserList() {
        users = new ArrayList<>();
        initializeUsersFromDatabase(); // Fetch user data from the database
    }

    // Method to initialize user data from the database
    private void initializeUsersFromDatabase() {
        try (Connection connection = DatabaseConnect.getConnection();
             Statement statement = connection.createStatement()) {

            // SQL query to fetch non-confidential user details
            String query = """
                SELECT u_id, firstname, lastname, email, role, status, image 
                FROM users 
                WHERE isDeleted = false
            """;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int u_id = resultSet.getInt("u_id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String status = resultSet.getString("status");
                String image = resultSet.getString("image");

                // Create a User object and add it to the list
                users.add(new User(u_id, firstName, lastName, email, role, status, image));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all users
    public List<User> getUsers() {
        return users;
    }
}
