/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminDashboard;

/**
 *
 * @author MaHir0
 */
import java.util.ArrayList;
import java.util.List;

public class UserSearch {

    private final List<User> users; // List of users for searching

    public UserSearch(List<User> users) {
        this.users = users; // Initialize users through constructor
    }

    public List<User> searchResult(String searchTerm) {
        List<User> matchedUsers = new ArrayList<>();
        String lowerSearchTerm = searchTerm.toLowerCase(); // Normalize search term to lowercase

        for (User user : users) {
            // Concatenate the full name
            String fullName = (user.getFirstName() + " " + user.getLastName()).toLowerCase();

            // Check if the searchTerm matches the full name or email
            if (fullName.contains(lowerSearchTerm) || user.getEmail().toLowerCase().contains(lowerSearchTerm)) {
                matchedUsers.add(user);
            }
        }

        return matchedUsers;
    }
}

