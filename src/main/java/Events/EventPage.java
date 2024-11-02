package Events;

import Events.EventCardPanel;
import Events.EventList;
import Events.Event;
import Buttons.EventSearch;
import Buttons.HeaderButtons;
import Buttons.AboutUsPanel;
import Buttons.ContactUsPanel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EventPage extends JFrame {

    private final EventList eventList;
    private final JPanel eventPanel; // Panel to display event cards
    private final JPanel contentPanel; // Panel to wrap eventPanel and other content
    private static final int PANEL_WIDTH = 900;
    private static final int PANEL_HEIGHT = 700;
    private static final int SCROLL_WIDTH = 700;
    private static final int SCROLL_HEIGHT = 400;
    private static final Insets CARD_INSETS = new Insets(5, 5, 5, 5); // Padding for event cards

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
        contentPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT)); // Fixed size for main content

        // Header panel with centered alignment
        JPanel headerPanel = new JPanel(); // Center alignment with spacing

        // Initialize HeaderButtons for reusable buttons
        HeaderButtons headerButtons = new HeaderButtons();
        
        // Add components in the desired order to the header panel
        headerPanel.add(headerButtons.getHomeButton()); // Home button
        headerPanel.add(Box.createHorizontalStrut(180)); // Adjusted spacing

        JButton eventsButton = new JButton("All Events"); // Events button
        headerPanel.add(eventsButton);
        // Action listener for the "Events" button to show all events
        eventsButton.addActionListener(e -> loadEventCards(eventList.getAllEvents()));

        JButton aboutUsButton = new JButton("About Us"); // About Us button
        headerPanel.add(aboutUsButton);
        // Action listener for the "About Us" button to show about us details
        aboutUsButton.addActionListener(e -> AboutUsPanel.showAboutUs());

        JButton contactUsButton = new JButton("Contact Us"); // Contact Us button
        headerPanel.add(contactUsButton);
        // Action listener for the "Contact Us" button to show contact details
        contactUsButton.addActionListener(e -> ContactUsPanel.showContactUs());

        // Search field and button
        JTextField searchField = new JTextField(20);
        headerPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        headerPanel.add(searchButton);

        // Profile logo button
        headerPanel.add(headerButtons.getProfileLogoButton()); // Profile logo

        // Add header panel to the top of the content panel
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // Event panel with GridBagLayout to show multiple event cards
        eventPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for flexible positioning

        // Wrap eventPanel in a JScrollPane
        JScrollPane eventScrollPane = new JScrollPane(eventPanel);
        eventScrollPane.setPreferredSize(new Dimension(SCROLL_WIDTH, SCROLL_HEIGHT)); // Fixed size for scroll pane
        contentPanel.add(eventScrollPane, BorderLayout.CENTER);

        // Load initial events into content panel
        loadEventCards(eventList.getEvents(9)); // Load the first 9 events

        mainPanel.add(contentPanel);

        // Action listener for search functionality
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            if (!query.isEmpty()) {
                List<Event> matchedEvents = EventSearch.searchEvents(eventList.getAllEvents(), query);
                if (matchedEvents.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No events found for your search.");
                } else {
                    loadEventCards(matchedEvents); // Load the matching events into the event panel
                }
            } else {
                loadEventCards(eventList.getEvents(9)); // Reload the initial events if search is empty
            }
        });
    }

    // Method to load event cards into the event panel within contentPanel
    private void loadEventCards(List<Event> events) {
        eventPanel.removeAll(); // Clear existing event cards
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = CARD_INSETS; // Use defined insets
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align to top left
        gbc.weighty = 1; // Keep vertical space consistent

        int columns = 3; // Number of columns (events per row)

        // Iterate over events and add them to the panel
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            JPanel eventCard = EventCardPanel.showevent(event);

            gbc.gridx = i % columns; // Set the column
            gbc.gridy = i / columns; // Set the row
            eventPanel.add(eventCard, gbc); // Add card to eventPanel
        }

        eventPanel.revalidate(); // Refresh layout once after all components are added
        eventPanel.repaint(); // Redraw panel
    }
}
