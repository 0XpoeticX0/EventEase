package Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.Timer;
import static Events.ViewEventDetails.showEventDetailsInDialog;

public class EventCardPanel extends JPanel {

    public static JPanel showevent(Event event) {
        // Main card panel
        JPanel eventCard = new JPanel(new BorderLayout());
        eventCard.setPreferredSize(new Dimension(250, 240));
        eventCard.setOpaque(false); // Ensure the main panel is transparent

        // Layered pane for precise positioning of components
        // Layered pane for precise positioning of components
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(250, 240)); // Adjust to full size of the card

// Load and scale the event image to fit the panel size
        File file = new File(event.imagePath);
        ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
        Image scaledImage = originalIcon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
        JLabel eventImageLabel = new JLabel(new ImageIcon(scaledImage));
        eventImageLabel.setBounds(0, 0, 250, 200); // Full size of the image

// Add the image as the background of the layered pane
        layeredPane.add(eventImageLabel, JLayeredPane.DEFAULT_LAYER); // Background layer

// Overlay panel for event name with semi-transparent background
        JPanel nameOverlayPanel = new JPanel(new BorderLayout());
        nameOverlayPanel.setBackground(new Color(0, 0, 0, 80)); // Semi-transparent black
        nameOverlayPanel.setOpaque(true); // Make the panel opaque
        nameOverlayPanel.setBounds(0, -60, 250, 60); // Start hidden above the image

// Label for event name
        JLabel eventNameLabel = new JLabel(event.name, SwingConstants.CENTER);
        eventNameLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        eventNameLabel.setForeground(Color.WHITE); // White text for contrast
        nameOverlayPanel.add(eventNameLabel, BorderLayout.CENTER);

// Add the name overlay panel on top of the image
        layeredPane.add(nameOverlayPanel, JLayeredPane.PALETTE_LAYER); // Foreground layer

// Add the layered pane to the main card panel
        eventCard.setLayout(new BorderLayout(0, 0)); // No gaps
        eventCard.add(layeredPane, BorderLayout.CENTER);

// "View Details" button
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setFocusPainted(false);
        viewDetailsButton.setFont(new Font("Roboto", Font.PLAIN, 16));
        viewDetailsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewDetailsButton.setBackground(new Color(52, 152, 219));
        viewDetailsButton.setForeground(Color.WHITE);

// Button hover effect
        viewDetailsButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                viewDetailsButton.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(MouseEvent evt) {
                viewDetailsButton.setBackground(new Color(52, 152, 219));
            }
        });

// Action listener for "View Details"
        viewDetailsButton.addActionListener(e -> showEventDetailsInDialog(event));

        viewDetailsButton.setBounds(0, 200, 250, 40); // Positioned right below the image
        layeredPane.add(viewDetailsButton, JLayeredPane.DEFAULT_LAYER);

// Hover animation for the name overlay
        Timer showTimer = new Timer(10, null);
        Timer hideTimer = new Timer(10, null);

        eventCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hideTimer.stop();
                showTimer.addActionListener(evt -> {
                    int y = nameOverlayPanel.getY();
                    if (y < 0) {
                        nameOverlayPanel.setLocation(0, y + 4); // Slide down
                    } else {
                        showTimer.stop();
                    }
                });
                showTimer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                showTimer.stop();
                hideTimer.addActionListener(evt -> {
                    int y = nameOverlayPanel.getY();
                    if (y > -60) {
                        nameOverlayPanel.setLocation(0, y - 4); // Slide up
                    } else {
                        hideTimer.stop();
                    }
                });
                hideTimer.start();
            }
        });

        return eventCard;

    }

}
