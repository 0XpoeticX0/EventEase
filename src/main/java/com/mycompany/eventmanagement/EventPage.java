package com.mycompany.eventmanagement;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;

public class EventPage extends JFrame {

    private final EventList eventList;
    private final JPanel eventPanel; // Make this a class variable to update it later

    public EventPage() {
        eventList = new EventList(); // Initialize EventList
        setTitle("Event Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false); // Prevent resizing

        // Main panel with GridBagLayout for central alignment
        JPanel mainPanel = new JPanel(new GridBagLayout());
        setContentPane(mainPanel);

        // Create central content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(700, 500)); // Fixed size

        // Header panel with BorderLayout for top-left and top-right alignment
        JPanel headerPanel = new JPanel(new BorderLayout());

        // Initialize HeaderButtons
        HeaderButtons headerButtons = new HeaderButtons();

        // Add the home button at the top-left corner
        headerPanel.add(headerButtons.getHomeButton(), BorderLayout.WEST);

        // Right-aligned panel for header buttons
        JPanel rightHeaderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Add other header buttons
        JButton eventsButton = new JButton("Events");
        JButton aboutUsButton = new JButton("About Us");
        JButton contactUsButton = new JButton("Contact Us");
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        // Add ActionListener to the search button
        searchButton.addActionListener(e -> searchEvents(searchField.getText()));

        rightHeaderPanel.add(eventsButton);
        rightHeaderPanel.add(aboutUsButton);
        rightHeaderPanel.add(contactUsButton);
        rightHeaderPanel.add(searchField);
        rightHeaderPanel.add(searchButton);
        rightHeaderPanel.add(headerButtons.getProfileLogoButton());

        // Add the right-aligned panel to the header
        headerPanel.add(rightHeaderPanel, BorderLayout.CENTER);

        // Add the header panel to the main content panel
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // Event panel with grid layout for event cards
        eventPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        eventPanel.setPreferredSize(new Dimension(700, 400)); // Fixed size for event cards

        // Load initial events
        loadEventCards(eventList.getEvents(6)); // Load the first 6 events

        contentPanel.add(eventPanel, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = new JPanel();
        JButton viewAllEventsButton = new JButton("View All Events");
        footerPanel.add(viewAllEventsButton);

        contentPanel.add(footerPanel, BorderLayout.SOUTH);

        // Center the contentPanel in mainPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(contentPanel, gbc);

        // Center the window on the screen
        setLocationRelativeTo(null);
    }

    // Method to load event cards into the event panel
    private void loadEventCards(List<Event> events) {
        eventPanel.removeAll(); // Clear existing event cards
        for (Event event : events) {
            JPanel eventCard = new JPanel(new BorderLayout());

            // Define fixed dimensions for the image
            int imageWidth = 250;  // Desired width
            int imageHeight = 100; // Desired height

            // Create ImageIcon and scale it to the fixed size
            File file = new File(event.getImagePath());
            ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
            Image scaledImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            JLabel eventImageLabel = new JLabel(new ImageIcon(scaledImage), SwingConstants.CENTER); // Show scaled image

            // Create a panel to hold the image and name label
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.add(eventImageLabel, BorderLayout.CENTER); // Add the image label

            // Create a label for the event name and set properties
            JLabel eventNameLabel = new JLabel(event.getName(), SwingConstants.CENTER);
            eventNameLabel.setPreferredSize(new Dimension(250, 30));
            eventNameLabel.setForeground(Color.BLACK); // Set text color to white for visibility
            eventNameLabel.setOpaque(true); // Make the label opaque
            eventNameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding

            // Add the event name label to the image panel, on top of the image
            imagePanel.add(eventNameLabel, BorderLayout.NORTH); // This adds the name above the image

            // Create view details button
            JButton viewDetailsButton = new JButton("View Details");
            viewDetailsButton.setPreferredSize(new Dimension(250, 30)); // Set width and height

            // Add action listener to view event details
            viewDetailsButton.addActionListener(e -> showEventDetails(event));

            // Create a panel for the button to control its size better
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the button
            buttonPanel.add(viewDetailsButton); // Add button to the panel

            // Add components to the event card
            eventCard.add(imagePanel, BorderLayout.NORTH);
            eventCard.add(buttonPanel, BorderLayout.SOUTH); // Add button panel

            eventPanel.add(eventCard);
        }
        eventPanel.revalidate(); // Refresh the panel
        eventPanel.repaint(); // Redraw the panel
    }

    // Method to search events
    private void searchEvents(String query) {
        List<Event> matchedEvents = eventList.getAllEvents().stream()
                .filter(event -> event.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        loadEventCards(matchedEvents); // Load the matching events into the event panel
    }

    // Method to show event details (popup or new window)
    private void showEventDetails(Event event) {
        JOptionPane.showMessageDialog(this, "Event Name: " + event.getName(), "Event Details", JOptionPane.INFORMATION_MESSAGE);
        // Add more details as needed
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventPage().setVisible(true));
    }
}
