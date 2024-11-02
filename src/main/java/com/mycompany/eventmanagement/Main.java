package com.mycompany.eventmanagement;
import Events.EventPage;
import DataBase.DatabaseConnect;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        try {
            DatabaseConnect.getConnection();
            SwingUtilities.invokeLater(() -> new EventPage().setVisible(true));
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
        }
    }
}
