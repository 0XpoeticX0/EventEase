package DataBase;

import CurrentUser.CurrentUser;

public class LoggedInUser {
    private static LoggedInUser instance;
    private CurrentUser currentUser;

    // Private constructor to prevent instantiation
    private LoggedInUser() {
    }

    // Get the instance of the Singleton
    public static LoggedInUser getInstance() {
        if (instance == null) {
            instance = new LoggedInUser();
        }
        return instance;
    }

    // Method to get the current logged-in user
    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    // Method to set the current logged-in user
    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    // Method to reset the current user (e.g., on logout)
    public void resetCurrentUser() {
        this.currentUser = null;
    }
}
