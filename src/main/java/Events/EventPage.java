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

                // Create a Graphics2D object from the Graphics object
                Graphics2D g2d = (Graphics2D) g;

                // Enable anti-aliasing for smoother gradient edges
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Apply the gradient from cyan to magenta
                GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, getWidth(), getHeight(), Color.MAGENTA);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight()); // Fill the panel with the gradient

                // Now, apply the semi-transparent white background over the gradient
                g2d.setColor(new Color(255, 255, 255, 180)); // Semi-transparent white
                g2d.fillRect(0, 0, getWidth(), getHeight()); // Overlay with the semi-transparent white

                // The semi-transparent background will overlay on top of the gradient
            }

        };

        // Set the background color for the main panel (to ensure the gradient is
        // visible)
        mainPanel.setBackground(Color.CYAN); // Or any color you prefer as base

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

        // Make the eventPanel opaque so it doesn't show the content behind, just the
        // gradient
        eventPanel.setOpaque(false); // Ensures the eventPanel does not block the gradient

        // Wrap eventPanel in a JScrollPane, allowing it to resize
        JScrollPane eventScrollPane = new JScrollPane(eventPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventScrollPane.setBorder(null);

        // Set faster scrolling speed
        eventScrollPane.getVerticalScrollBar().setUnitIncrement(20); // Adjust the value to control scroll speed
        eventScrollPane.getVerticalScrollBar().setBlockIncrement(50); // Adjust the value for larger jumps

        // Set background of eventScrollPane to transparent to preserve the gradient
        // effect
        eventScrollPane.setOpaque(false);
        eventScrollPane.getViewport().setOpaque(false); // Keep viewport transparent for smooth scroll behavior

        // Now add this eventScrollPane to the main panel
        mainPanel.add(eventScrollPane, BorderLayout.CENTER);

        loadEventCards(eventList.getEvents(6));

        // Set up the JFrame
        setSize(900, 850);
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Make the window size fixed
        add(mainPanel); // Add the main panel to the JFrame
        setVisible(true);
    }

    public void loadEventCards(java.util.List<Event> events) {
        // Clear any existing event cards
        eventPanel.removeAll();
        eventPanel.revalidate(); // Revalidate the layout to remove any lingering components
        eventPanel.repaint(); // Repaint to clear out anything left in the panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add event cards to the panel
        int columns = 3;
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            JPanel eventCard = EventCardPanel.showevent(event);

            gbc.gridx = i % columns;
            gbc.gridy = i / columns;
            eventPanel.add(eventCard, gbc);
        }

        // Optionally, you can add a transparent filler to keep the layout stable if
        // there are fewer items than the grid can fit
        gbc.gridx = 0;
        gbc.gridy = events.size() / columns + 1;
        gbc.weighty = 1.0;
        JPanel transparentPanel = new JPanel();
        transparentPanel.setBackground(new Color(0, 0, 0, 0)); // Fully transparent
        eventPanel.add(transparentPanel, gbc);

        // Finally, revalidate and repaint to make sure the panel reflects the new state
        eventPanel.revalidate();
        eventPanel.repaint();
    }
}
