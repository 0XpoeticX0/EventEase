package com.mycompany.eventmanagement;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class EventPage extends JFrame {

    private final EventList eventList;
    private final JPanel eventPanel; // Panel to display event cards
    private final JPanel contentPanel; // Panel to wrap eventPanel and other content

    public EventPage() {
        eventList = new EventList(); // Initialize EventList
        setTitle("Event Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);

        // Main panel with GridBagLayout for central alignment
        JPanel mainPanel = new JPanel(new GridBagLayout());
        setContentPane(mainPanel);

        // Central content panel
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(900, 700)); // Fixed size for main content

        // Header panel with centered alignment
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center alignment with spacing

        // Initialize HeaderButtons for reusable buttons
        HeaderButtons headerButtons = new HeaderButtons();

        // Add components in the desired order to the header panel
        headerPanel.add(headerButtons.getHomeButton()); // Home button
        headerPanel.add(Box.createHorizontalStrut(180)); // Adjusted spacing
        headerPanel.add(new JButton("Events")); // Events button
        headerPanel.add(new JButton("About Us")); // About Us button
        headerPanel.add(new JButton("Contact Us")); // Contact Us button

        // Search field and button
        JTextField searchField = new JTextField(20);
        headerPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        headerPanel.add(searchButton);

        // Profile logo button
        headerPanel.add(headerButtons.getProfileLogoButton()); // Profile logo

        // Add header panel to the top of the content panel
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // Event panel with GridLayout to show multiple event cards
        eventPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, unlimited rows, with 10px gaps

        // Wrap eventPanel in a JScrollPane
        JScrollPane eventScrollPane = new JScrollPane(eventPanel);
        eventScrollPane.setPreferredSize(new Dimension(700, 400)); // Fixed size for scroll pane
        contentPanel.add(eventScrollPane, BorderLayout.CENTER);

        // Load initial events into content panel
        loadEventCards(eventList.getEvents(9)); // Load the first 9 events

        // Add content panel to the main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(contentPanel, gbc);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Action listener for search functionality
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            if (!query.isEmpty()) {
                searchEvents(query);
            } else {
                loadEventCards(eventList.getEvents(9)); // Reload the initial events if search is empty
            }
        });

        // Wrap the entire contentPanel in a JScrollPane for full-page scrolling
        JScrollPane fullScrollPane = new JScrollPane(contentPanel);
        setContentPane(fullScrollPane);
    }

    // Method to load event cards into the event panel within contentPanel
    private void loadEventCards(List<Event> events) {
        eventPanel.removeAll(); // Clear existing event cards
        eventPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around each card
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align top left
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        int columns = 3; // Number of columns (events per row)
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            JPanel eventCard = EventCardPanel.showevent(event);

            gbc.gridx = i % columns; // Set the column
            gbc.gridy = i / columns; // Set the row

            eventPanel.add(eventCard, gbc); // Add card to eventPanel
        }

        eventPanel.revalidate(); // Refresh layout
        eventPanel.repaint(); // Redraw panel
    }

    // Method to search events
    private void searchEvents(String query) {
        List<Event> matchedEvents = eventList.getAllEvents().stream()
                .filter(event -> event.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        loadEventCards(matchedEvents); // Load the matching events into the event panel
    }

}
