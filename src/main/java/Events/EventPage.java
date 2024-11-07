package Events;

import java.awt.*;
import javax.swing.*;
import Buttons.AboutUsPanel;
import Buttons.ContactUsPanel;

public final class EventPage extends JFrame {

    private final EventList eventList;
    private final JPanel eventPanel;

    public EventPage() {
        eventList = new EventList(); // Initialize EventList
        setTitle("Event Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with BorderLayout for flexible resizing
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Apply gradient background: from cyan to magenta
                GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, getWidth(), getHeight(), Color.MAGENTA);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());  // Fill the whole panel with the gradient
            }
        };

        // Set the background color for the main panel (to ensure the gradient is visible)
        mainPanel.setBackground(Color.CYAN);  // Or any color you prefer as base

        // Pass this frame as the parentFrame to HeaderButtons
        HeaderPanel headerPanel = new HeaderPanel(
                this, // Pass reference to the current frame
                eventList,
                this::loadEventCards, // Pass loadEventCards method reference
                AboutUsPanel::showAboutUs, // Show About Us panel
                ContactUsPanel::showContactUs // Show Contact Us panel
        );

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Event panel with GridBagLayout for flexible event card positioning
        eventPanel = new JPanel(new GridBagLayout());
        eventPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set background color for event panel so the gradient is visible behind it
        eventPanel.setBackground(new Color(255, 255, 255, 180)); // Semi-transparent white background

        // Wrap eventPanel in a JScrollPane, allowing it to resize
        JScrollPane eventScrollPane = new JScrollPane(eventPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventScrollPane.setBorder(null);
        mainPanel.add(eventScrollPane, BorderLayout.CENTER);

        loadEventCards(eventList.getEvents(6));

        // Set up the JFrame
        setSize(800, 850);
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Make the window size fixed
        add(mainPanel); // Add the main panel to the JFrame
        setVisible(true);
    }

    public void loadEventCards(java.util.List<Event> events) {
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

        // Create a fully transparent JPanel and add it to the layout
        JPanel transparentPanel = new JPanel();
        transparentPanel.setBackground(new Color(0, 0, 0, 0));  // Fully transparent background
        eventPanel.add(transparentPanel, gbc);

        eventPanel.revalidate();
        eventPanel.repaint();
    }
}
