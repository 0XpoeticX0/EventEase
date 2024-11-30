package AdminDashboard;

import Events.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventPanelBuilder {

    public static JPanel buildCompleteEventPanel(Event event) {
        // Main event panel using GridBagLayout for better control
        JPanel eventPanel = new JPanel(new GridBagLayout());
        eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        eventPanel.setBackground(Color.BLACK);
        eventPanel.setPreferredSize(new Dimension(600, 100)); // Adjust size as necessary

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Left: Image (Circular)
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(50, 50)); // Circle size
        imagePanel.setBackground(Color.GRAY); // Temporary background color for image
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        imagePanel.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(event.imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        eventPanel.add(imagePanel, gbc);

        // Middle part: Event Name, Price, and Status
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new GridBagLayout());

        GridBagConstraints textGBC = new GridBagConstraints();
        textGBC.fill = GridBagConstraints.HORIZONTAL;
        textGBC.insets = new Insets(5, 5, 5, 5);

        JLabel eventNameLabel = new JLabel("<html><div style='width: 250px;'>" + event.getName() + "</div></html>");  // HTML to wrap text
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        eventNameLabel.setForeground(Color.WHITE);
        eventNameLabel.setPreferredSize(new Dimension(250, 50));  // Ensure space for text wrap
        textGBC.gridx = 0;
        textGBC.gridy = 0;
        textPanel.add(eventNameLabel, textGBC);

        JLabel priceLabel = new JLabel("$" + event.getPrice());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setForeground(Color.WHITE);
        textGBC.gridx = 0;
        textGBC.gridy = 1;
        textPanel.add(priceLabel, textGBC);

        JLabel statusLabel = new JLabel(event.getStatus().equalsIgnoreCase("active") ? "Active" : "Inactive");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(event.getStatus().equalsIgnoreCase("active") ? Color.GREEN : Color.RED);
        textGBC.gridx = 0;
        textGBC.gridy = 2;
        textPanel.add(statusLabel, textGBC);

        gbc.gridx = 1;
        gbc.gridy = 0;
        eventPanel.add(textPanel, gbc);

        // Right side: Buttons (Publish, Delete)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);

        // "PUBLISH" Button
        JButton publishButton = new JButton();
        boolean isActive = event.getStatus().equalsIgnoreCase("active");
        publishButton.setIcon(new ImageIcon(EventPanelBuilder.class.getResource(isActive ? "/Icons/shield-minus.png" : "/Icons/shield-check.png")));
        publishButton.setPreferredSize(new Dimension(30, 30));
        publishButton.setBorderPainted(false);
        publishButton.setFocusPainted(false);
        publishButton.setContentAreaFilled(false);
        buttonPanel.add(publishButton);

        publishButton.addActionListener(new ActionListener() {
            private boolean isCurrentlyActive = isActive;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCurrentlyActive) {
                    // Deactivate the event
                    statusLabel.setText("Inactive");
                    statusLabel.setForeground(Color.RED);
                    publishButton.setIcon(new ImageIcon(EventPanelBuilder.class.getResource("/Icons/shield-check.png")));
                    DatabaseHelper.updateEventStatus(event.e_id, "inactive");

                    // Show confirmation dialog
                    JOptionPane.showMessageDialog(
                            null,
                            event.getName() + " deactivated successfully!",
                            "Status Update",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    // Activate the event
                    statusLabel.setText("Active");
                    statusLabel.setForeground(Color.GREEN);
                    publishButton.setIcon(new ImageIcon(EventPanelBuilder.class.getResource("/Icons/shield-minus.png")));
                    DatabaseHelper.updateEventStatus(event.e_id, "active");

                    // Show confirmation dialog
                    JOptionPane.showMessageDialog(
                            null,
                            event.getName() + " activated successfully!",
                            "Status Update",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                // Toggle the active state
                isCurrentlyActive = !isCurrentlyActive;
            }
        });

        // "Delete" Button
        JButton deleteButton = new JButton();
        deleteButton.setIcon(new ImageIcon(EventPanelBuilder.class.getResource("/Icons/trash-2.png")));
        deleteButton.setPreferredSize(new Dimension(30, 30));
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setContentAreaFilled(false);
        buttonPanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the event is booked
                String latestBookingDate = DatabaseHelper.getLatestBookingDate(event.getE_id());

                if (latestBookingDate != null) {
                    // If the event is booked, show a message and prevent deletion
                    JOptionPane.showMessageDialog(
                            null,
                            event.getName() + " event cannot be deleted because it is booked until " + latestBookingDate
                            + ".\n Please deactivate the event to prevent further bookings.",
                            "Event Deletion Not Allowed",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return; // Exit the action listener to prevent further execution
                }

                // Show a confirmation dialog to the user
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this event?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                // If the user selects "Yes", proceed with deletion
                if (response == JOptionPane.YES_OPTION) {
                    // Remove the event panel from the UI
                    Container parent = eventPanel.getParent();
                    if (parent != null) {
                        parent.remove(eventPanel);
                        parent.revalidate();  // Revalidate the container to update the layout
                        parent.repaint();     // Repaint the container to reflect changes
                    }

                    // Call method to delete the event from the database
                    DatabaseHelper.deleteEvent(event.getE_id());

                    // Show a confirmation message
                    JOptionPane.showMessageDialog(null, "Event deleted successfully.");
                } else {
                    // If the user selects "No", do nothing
                    JOptionPane.showMessageDialog(null, "Event deletion canceled.");
                }
            }
        });

        gbc.gridx = 2;
        gbc.gridy = 0;
        eventPanel.add(Box.createHorizontalStrut(30));
        eventPanel.add(buttonPanel, gbc);

        return eventPanel;
    }
}
