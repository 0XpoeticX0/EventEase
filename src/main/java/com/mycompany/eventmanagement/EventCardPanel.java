package com.mycompany.eventmanagement;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EventCardPanel extends JPanel {

    // Method to show the event
    public static JPanel showevent(Event event) {
        JPanel eventCard = new JPanel();
        eventCard.setLayout(new BoxLayout(eventCard, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        eventCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding around the event card

        // Define fixed dimensions for the image
        int width = 250;  // Desired width
        int imageHeight = 100; // Desired height

        // Create ImageIcon and scale it to the fixed size
        File file = new File(event.getImagePath());
        ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, imageHeight, Image.SCALE_SMOOTH);
        JLabel eventImageLabel = new JLabel(new ImageIcon(scaledImage), SwingConstants.CENTER); // Show scaled image
        eventImageLabel.setPreferredSize(new Dimension(width, imageHeight)); // Set preferred size for the image

        // Create a label for the event name and set properties
        JLabel eventNameLabel = new JLabel(event.getName(), SwingConstants.CENTER);
        eventNameLabel.setPreferredSize(new Dimension(width, 30)); // Set width to match image
        eventNameLabel.setForeground(Color.BLACK); // Set text color to black for visibility
        eventNameLabel.setOpaque(true); // Make the label opaque
        eventNameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding

        // Create view details button
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setPreferredSize(new Dimension(width, 30)); // Set width to match image, height is customizable
        viewDetailsButton.setMinimumSize(new Dimension(width, 30)); // Set minimum size
        viewDetailsButton.setMaximumSize(new Dimension(width, 30)); // Set maximum size
        viewDetailsButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button in the box

        // Center the components
        eventImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewDetailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewDetailsButton.addActionListener(e -> showEventDetails(event)); // Add action listener to the button

        // Add components to the event card
        eventCard.add(eventNameLabel);
        eventCard.add(eventImageLabel);
        eventCard.add(viewDetailsButton);
        eventCard.add(Box.createRigidArea(new Dimension(0, 10))); // Add a bit more space below the button


   

        return eventCard;
    }

    // Show details of the event in a dialog
    private static void showEventDetails(Event event) {
        JOptionPane.showMessageDialog( null,"Event Name: " + event.getName(), "Event Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
