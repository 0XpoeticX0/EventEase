package AdminDashboard;

import DataBase.DatabaseConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserList {

    // Method to retrieve all users from the database
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnect.getConnection();
             Statement statement = connection.createStatement()) {

            // SQL query to fetch non-confidential user details
            String query = """
                SELECT u_id, firstname, lastname, email, role, status, image 
                FROM users 
                WHERE isDeleted = false
            """;
            ResultSet resultSet = statement.executeQuery(query);

            // Populate the user list
            while (resultSet.next()) {
                int u_id = resultSet.getInt("u_id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String status = resultSet.getString("status");
                String image = resultSet.getString("image");

                // Add a new User object to the list
                users.add(new User(u_id, firstName, lastName, email, role, status, image));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
