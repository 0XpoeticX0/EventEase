package Events;

import Buttons.EventSearch;
import Buttons.HeaderButtons;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HeaderPanel extends JPanel {

    private final HeaderButtons headerButtons;
    private final JButton eventsButton;
    private final JButton aboutUsButton;
    private final JButton contactUsButton;
    private final JTextField searchField;
    private final JButton searchButton;

    public HeaderPanel(EventList eventList, Runnable loadEventCardsCallback, Runnable showAboutUsCallback, Runnable showContactUsCallback) {
        // Initialize HeaderButtons
        headerButtons = new HeaderButtons();

        // Add EventEase logo
        add(HeaderButtons.createEventEaseLogo());
        add(Box.createHorizontalStrut(50));

        // Initialize buttons
        eventsButton = new JButton("All Events");
        eventsButton.addActionListener(e -> loadEventCardsCallback.run());
        add(eventsButton);

        aboutUsButton = new JButton("About Us");
        aboutUsButton.addActionListener(e -> showAboutUsCallback.run());
        add(aboutUsButton);

        contactUsButton = new JButton("Contact Us");
        contactUsButton.addActionListener(e -> showContactUsCallback.run());
        add(contactUsButton);

        // Initialize search field and button
        searchField = new JTextField(20);
        add(searchField);

        searchButton = new JButton("Search");
        add(searchButton);

        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            if (!query.isEmpty()) {
                List<Event> matchedEvents = EventSearch.searchEvents(eventList.getEvents(), query);
                if (matchedEvents.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No events found for your search.");
                } else {
                    loadEventCardsCallback.run();
                }
            } else {
                loadEventCardsCallback.run();
            }
        });

        // Add profile button from HeaderButtons
        add(headerButtons.getProfileLogoButton());

        // Set layout and spacing
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }
}
