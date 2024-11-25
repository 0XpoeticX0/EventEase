package Events;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import static Events.ViewEventDetails.showEventDetailsInDialog;

public class EventCardPanel extends JPanel {

    // Method to create and display the event card
    public static JPanel showevent(Event event) {
        JPanel eventCard = new JPanel();
        eventCard.setLayout(new BorderLayout());
        eventCard.setPreferredSize(new Dimension(220, 200)); // Adjust height as needed

        // Load and scale the event image to fit the panel size
        File file = new File(event.imagePath);
        ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
        Image scaledImage = originalIcon.getImage().getScaledInstance(220, 200, Image.SCALE_SMOOTH);
        JLabel eventImageLabel = new JLabel(new ImageIcon(scaledImage));
        eventImageLabel.setLayout(new BorderLayout());

        // Overlay panel for event name with semi-transparent background
        JPanel nameOverlayPanel = new JPanel();
        nameOverlayPanel.setLayout(new BorderLayout());
        nameOverlayPanel.setOpaque(true);
        nameOverlayPanel.setBackground(new Color(0, 0, 0, 220)); // Semi-transparent black background

        JLabel eventNameLabel = new JLabel(event.name, SwingConstants.CENTER);
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        eventNameLabel.setForeground(Color.WHITE); // Set text color to white for visibility
        nameOverlayPanel.add(eventNameLabel, BorderLayout.CENTER);

        // Add the name overlay to the bottom of the image
        eventImageLabel.add(nameOverlayPanel, BorderLayout.SOUTH);

        // "View Details" button
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setFocusPainted(false);
        viewDetailsButton.setForeground(Color.WHITE);
        viewDetailsButton.setBackground(new Color(76, 133, 214));
        viewDetailsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        viewDetailsButton.setPreferredSize(new Dimension(220, 40)); // Make button width match panel width
        viewDetailsButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        viewDetailsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewDetailsButton.addActionListener(e -> showEventDetailsInDialog(event));

        // Add components to the card
        eventCard.add(eventImageLabel, BorderLayout.CENTER);
        eventCard.add(viewDetailsButton, BorderLayout.SOUTH);

        return eventCard;
    }

}
