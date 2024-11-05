// EventPage.java
package Events;

import Buttons.AboutUsPanel;
import Buttons.ContactUsPanel;
import javax.swing.*;
import java.awt.*;

public class EventPage extends JFrame {

    private final EventList eventList;
    private final JPanel eventPanel;

    public EventPage() {
        eventList = new EventList(); // Initialize EventList
        setTitle("Event Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with BorderLayout for flexible resizing
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Create HeaderPanel with necessary callbacks
        HeaderPanel headerPanel = new HeaderPanel(
            eventList,
            () -> loadEventCards(eventList.getEvents()), // Load all events
            AboutUsPanel::showAboutUs,                  // Show About Us panel
            ContactUsPanel::showContactUs               // Show Contact Us panel
        );

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Event panel with GridBagLayout for flexible event card positioning
        eventPanel = new JPanel(new GridBagLayout());
        eventPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Wrap eventPanel in a JScrollPane, allowing it to resize
        JScrollPane eventScrollPane = new JScrollPane(eventPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventScrollPane.setBorder(null);
        mainPanel.add(eventScrollPane, BorderLayout.CENTER);

        loadEventCards(eventList.getEvents(6)); // Load initial events

        pack();
        setLocationRelativeTo(null);
    }

    private void loadEventCards(java.util.List<Event> events) {
        eventPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int columns = 3;
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            JPanel eventCard = EventCardPanel.showevent(event);

            gbc.gridx = i % columns;
            gbc.gridy = i / columns;
            eventPanel.add(eventCard, gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = events.size() / columns + 1;
        gbc.weighty = 1.0;
        eventPanel.add(new JPanel(), gbc);

        eventPanel.revalidate();
        eventPanel.repaint();
    }
}
