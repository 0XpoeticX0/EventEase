package Events;

import Buttons.HeaderButtons;
import Buttons.Search;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class HeaderPanel extends JPanel {

    private final HeaderButtons headerButtons;
    private final JButton eventsButton;
    private final JButton aboutUsButton;
    private final JButton contactUsButton;
    private final JTextField searchField;
    private final JButton searchButton;
    private final Search search;

    public HeaderPanel(JFrame parentFrame, EventList eventList, java.util.function.Consumer<List<Event>> loadEventCardsCallback, Runnable showAboutUsCallback, Runnable showContactUsCallback) {
        // Set background color and layout
        setBackground(Color.decode("#343a40"));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Initialize HeaderButtons with reference to parentFrame
        headerButtons = new HeaderButtons(parentFrame);

        // Add EventEase logo
        add(headerButtons.getEventEaseLogo());
        add(Box.createHorizontalStrut(50)); // Horizontal spacing

        // Initialize buttons with styling
        eventsButton = createStyledButton(new JButton("All Events"), "#ffffff", "#343a40");
        eventsButton.addActionListener(e -> loadEventCardsCallback.accept(eventList.getEvents())); // Load all events
        add(eventsButton);

        aboutUsButton = createStyledButton(new JButton("About Us"), "#ffffff", "#343a40");
        aboutUsButton.addActionListener(e -> showAboutUsCallback.run());
        add(aboutUsButton);

        contactUsButton = createStyledButton(new JButton("Contact Us"), "#ffffff", "#343a40");
        contactUsButton.addActionListener(e -> showContactUsCallback.run());
        add(contactUsButton);

        // Initialize search field
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createLineBorder(Color.decode("#cccccc")));
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        searchField.setMinimumSize(new Dimension(150, 30));
        add(searchField);

        // Initialize and style search button
        searchButton = createStyledButton(new JButton("Search"), "#ffffff", "#007bff");
        searchButton.setMaximumSize(new Dimension(80, 30));
        add(searchButton);

        search = new Search(eventList.getEvents());

        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            if (!query.isEmpty()) {
                List<Event> matchedEvents = search.searchEvents(query);
                if (matchedEvents.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No events found for your search.");
                } else {
                    loadEventCardsCallback.accept(matchedEvents);
                }
            } else {
                loadEventCardsCallback.accept(eventList.getEvents().stream().limit(6).toList());
            }
        });

        add(Box.createHorizontalStrut(20)); // Horizontal spacing
        add(headerButtons.getProfileLogoButton());
    }

    private JButton createStyledButton(JButton button, String textColor, String bgColor) {
        button.setForeground(Color.decode(textColor));
        button.setBackground(Color.decode(bgColor));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }
}
