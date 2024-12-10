package AdminDashboard;

import UserDashboard.BookedEvent;
import java.awt.*;
import javax.swing.*;

/**
 * EventBookingCheckByAdmin: Generates a panel for displaying event details with image on the left.
 */
public class EventBookingCheckByAdmin {
    public static JPanel buildCompleteEventPanel(BookedEvent event, String u_id) {
        // Main event panel using BorderLayout for a clean structure
        JPanel eventPanel = new JPanel(new BorderLayout(10, 0)); // Reduced gap between sections to 10px
        eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        eventPanel.setBackground(Color.BLACK);
        eventPanel.setPreferredSize(new Dimension(480, 110));

        // ===== LEFT: Image Panel =====
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(110, 100)); // Circular size
        imagePanel.setOpaque(false); // Makes background transparent
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0)); // Circular border
        imagePanel.setLayout(new BorderLayout());

        // Image label
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(event.getEventImage()).getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Add the image panel to the left
        eventPanel.add(Box.createHorizontalStrut(20));
        eventPanel.add(imagePanel, BorderLayout.WEST);

        // ===== CENTER: Event Details =====
        JPanel detailsPanel = new JPanel();
        detailsPanel.setOpaque(false);
        detailsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5); // Reduced vertical spacing between labels

        // Event Name
        JLabel eventNameLabel = new JLabel(
                "<html><div style='width: 250px;'>" + event.getEventName() + "</div></html>");
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        eventNameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(eventNameLabel, gbc);

        // Price
        JLabel priceLabel = new JLabel("$" + event.getEventPrice());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsPanel.add(priceLabel, gbc);

        // Status
        JLabel statusLabel = new JLabel("Paid");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(Color.GREEN);
        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsPanel.add(statusLabel, gbc);

        // Booking Date
        JLabel bookingDateLabel = new JLabel("Booking Date: " + event.getBookingDate());
        bookingDateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        bookingDateLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        detailsPanel.add(bookingDateLabel, gbc);

        // Add the details panel to the center
        eventPanel.add(Box.createHorizontalStrut(-40));
        eventPanel.add(detailsPanel, BorderLayout.CENTER);

        return eventPanel;
    }
}
