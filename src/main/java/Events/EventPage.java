package Events;

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
    private static final Insets CARD_INSETS = new Insets(5, 5, 5, 5); // Padding for event cards

    public EventPage() {
        eventList = new EventList(); // Initialize EventList
        setTitle("Event Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with BorderLayout for flexible resizing
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Central content panel, now resizable with BorderLayout
        contentPanel = new JPanel(new BorderLayout());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Header panel
        JPanel headerPanel = new JPanel();
        HeaderButtons headerButtons = new HeaderButtons();

        // Header buttons
        headerPanel.add(headerButtons.getHomeButton());
        headerPanel.add(Box.createHorizontalStrut(80));

        JButton eventsButton = new JButton("All Events");
        headerPanel.add(eventsButton);
        eventsButton.addActionListener(e -> loadEventCards(eventList.getAllEvents()));

        JButton aboutUsButton = new JButton("About Us");
        headerPanel.add(aboutUsButton);
        aboutUsButton.addActionListener(e -> AboutUsPanel.showAboutUs());

        JButton contactUsButton = new JButton("Contact Us");
        headerPanel.add(contactUsButton);
        contactUsButton.addActionListener(e -> ContactUsPanel.showContactUs());

        JTextField searchField = new JTextField(20);
        headerPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        headerPanel.add(searchButton);

        headerPanel.add(headerButtons.getProfileLogoButton());
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // Event panel with GridBagLayout for flexible event card positioning
        eventPanel = new JPanel(new GridBagLayout());
        eventPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Wrap eventPanel in a JScrollPane, allowing it to resize
        JScrollPane eventScrollPane = new JScrollPane(eventPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventScrollPane.getViewport().setPreferredSize(null); // Enables dynamic resizing of content
        eventScrollPane.setBorder(null); // Remove the border around eventPanel
        eventScrollPane.getVerticalScrollBar().setUnitIncrement(15); // Adjust this value for faster or slower scrolling
        eventScrollPane.getVerticalScrollBar().setBlockIncrement(100); // Adjust this for scroll track clicks
        contentPanel.add(eventScrollPane, BorderLayout.CENTER);

        loadEventCards(eventList.getEvents(6)); // Load the first 6 events

        // Search functionality
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            if (!query.isEmpty()) {
                List<Event> matchedEvents = EventSearch.searchEvents(eventList.getAllEvents(), query);
                if (matchedEvents.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No events found for your search.");
                } else {
                    loadEventCards(matchedEvents);
                }
            } else {
                loadEventCards(eventList.getEvents(6));
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    // Method to load event cards into the event panel within contentPanel
    private void loadEventCards(List<Event> events) {
        eventPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = CARD_INSETS;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int columns = 3; // Number of columns (events per row)

        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            JPanel eventCard = EventCardPanel.showevent(event);

            gbc.gridx = i % columns;
            gbc.gridy = i / columns;
            eventPanel.add(eventCard, gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = events.size() / columns + 1;
        gbc.weighty = 1.0; // Ensures remaining space stays at the bottom
        eventPanel.add(new JPanel(), gbc);

        eventPanel.revalidate();
        eventPanel.repaint();
    }
}
