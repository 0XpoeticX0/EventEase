package UserDashboard;

import Events.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEventPanel {

    /**
     * Method to build the complete event panel as per your design.
     */
    public static JPanel buildCompleteEventPanel(BookedEvent event, String u_id) {
        // Main event panel using GridBagLayout for better control
        JPanel eventPanel = new JPanel(new GridBagLayout());
        eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        eventPanel.setBackground(Color.BLACK);
        eventPanel.setPreferredSize(new Dimension(600, 120)); // Adjust size as necessary

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Left: Image (Circular)
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(50, 50)); // Circle size
        imagePanel.setBackground(Color.GRAY); // Temporary background color for image
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Circular border
        imagePanel.setLayout(new BorderLayout());

        // Image label
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(event.getEventImage()).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Add image panel to the event panel (column 0, row 0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        eventPanel.add(imagePanel, gbc);

        // Middle part: Event Name, Price, Status, and Date
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new GridBagLayout()); // Nested GridBagLayout for name, price, status, and date

        GridBagConstraints textGBC = new GridBagConstraints();
        textGBC.fill = GridBagConstraints.HORIZONTAL;
        textGBC.insets = new Insets(5, 5, 5, 5);

        // Event Name
        JLabel eventNameLabel = new JLabel(
                "<html><div style='width: 250px;'>" + event.getEventName() + "</div></html>"); // HTML
        // to
        // wrap
        // text
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        eventNameLabel.setForeground(Color.WHITE);
        eventNameLabel.setPreferredSize(new Dimension(250, 50)); // Ensure space for text wrap
        textGBC.gridx = 0;
        textGBC.gridy = 0;
        textPanel.add(eventNameLabel, textGBC);

        // Price
        JLabel priceLabel = new JLabel("$" + event.getEventPrice()); // Assuming Event class has getPrice method
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setForeground(Color.WHITE);
        textGBC.gridx = 0;
        textGBC.gridy = 1;
        textPanel.add(priceLabel, textGBC);

        // Status (Changed to Paid)
        JLabel statusLabel = new JLabel("Paid"); // Replacing "Active" with "Paid"
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(Color.GREEN);
        textGBC.gridx = 0;
        textGBC.gridy = 2;
        textPanel.add(statusLabel, textGBC);

        // Booking Date
        JLabel bookingDateLabel = new JLabel("Booking Date: " + event.getBookingDate()); // Show the booking date
        bookingDateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        bookingDateLabel.setForeground(Color.WHITE);
        textGBC.gridx = 0;
        textGBC.gridy = 3;
        textPanel.add(bookingDateLabel, textGBC);

        // Add text panel to the event panel (column 1, row 0)
        gbc.gridx = 1;
        gbc.gridy = 0;
        eventPanel.add(textPanel, gbc);

        // Right side: Delete button (Removed Publish button)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Use standard gap for buttons
        buttonPanel.setOpaque(false);

        // "Delete" Icon Button
        JButton cancleEvent = new JButton();
        cancleEvent.setIcon(new ImageIcon(UserEventPanel.class.getResource("/Icons/x.png"))); // Adjust the path to your
                                                                                              // image
        cancleEvent.setPreferredSize(new Dimension(30, 30));
        cancleEvent.setBorderPainted(false);
        cancleEvent.setFocusPainted(false);
        cancleEvent.setContentAreaFilled(false);
        buttonPanel.add(cancleEvent);

        // Add button panel to the event panel (column 2, row 0)
        gbc.gridx = 2;
        gbc.gridy = 0;
        eventPanel.add(Box.createHorizontalStrut(30)); // Adjust the space on the left (Move buttons to the left)
        eventPanel.add(buttonPanel, gbc);

        // Add ActionListener to cancleEvent
        cancleEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a confirmation dialog to the user
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to Cancle this event?",
                        "Confirm Cancelation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                // If the user selects "Yes", proceed with deletion
                if (response == JOptionPane.YES_OPTION) {
                    // Remove the event panel from the UI
                    Container parent = eventPanel.getParent();
                    if (parent != null) {
                        parent.remove(eventPanel);
                        parent.revalidate(); // Revalidate the container to update the layout
                        parent.repaint(); // Repaint the container to reflect changes
                    }

                    // Call method to delete the event from the database
                    EventBookingHelper.deleteBooking(event.getBookingId(), u_id);

                }
            }
        });

        return eventPanel;
    }
}
