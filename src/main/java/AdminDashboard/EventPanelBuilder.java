package AdminDashboard;

import Events.Event;
import javax.swing.*;
import java.awt.*;

public class EventPanelBuilder {

    /**
     * Method to build the complete event panel as per your design.
     */
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

        // Make the image circular by setting the border
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        imagePanel.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(event.imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Add image panel to the event panel (column 0, row 0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        eventPanel.add(imagePanel, gbc);

        // Middle part: Event Name, Price, and Status
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new GridBagLayout()); // Nested GridBagLayout for name, price, and status

        // GridBagConstraints for textPanel's child components
        GridBagConstraints textGBC = new GridBagConstraints();
        textGBC.fill = GridBagConstraints.HORIZONTAL;
        textGBC.insets = new Insets(5, 5, 5, 5);

        // Event Name (Wrap text if too long)
        JLabel eventNameLabel = new JLabel("<html><div style='width: 250px;'>" + event.getName() + "</div></html>");  // HTML to wrap text
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        eventNameLabel.setForeground(Color.WHITE);
        eventNameLabel.setPreferredSize(new Dimension(250, 50));  // Ensure space for text wrap
        textGBC.gridx = 0;
        textGBC.gridy = 0;
        textPanel.add(eventNameLabel, textGBC);

        // Price (20px right from the event name)
        JLabel priceLabel = new JLabel("$" + event.getPrice()); // Assuming Event class has getPrice method
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setForeground(Color.WHITE);
        textGBC.gridx = 0;
        textGBC.gridy = 1;
        textPanel.add(priceLabel, textGBC);

        // Status (20px right from the price)
        JLabel statusLabel = new JLabel("Active"); // or dynamic status from Event class
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(Color.GREEN);
        textGBC.gridx = 0;
        textGBC.gridy = 2;
        textPanel.add(statusLabel, textGBC);

        // Add text panel to the event panel (column 1, row 0)
        gbc.gridx = 1;
        gbc.gridy = 0;
        eventPanel.add(textPanel, gbc);

        // Right side: Buttons (Publish, Delete)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Use standard gap for buttons
        buttonPanel.setOpaque(false);

        // "PUBLISH" Button
        JButton publishButton = new JButton();
        publishButton.setIcon(new ImageIcon(EventPanelBuilder.class.getResource("/Icons/shield-check.png"))); // Adjust the path to your image
        publishButton.setPreferredSize(new Dimension(30, 30));
        publishButton.setBorderPainted(false);
        publishButton.setFocusPainted(false);
        publishButton.setContentAreaFilled(false);
        buttonPanel.add(publishButton);

        // "Delete" Icon Button
        JButton deleteButton = new JButton();
        deleteButton.setIcon(new ImageIcon(EventPanelBuilder.class.getResource("/Icons/trash-2.png"))); // Adjust the path to your image
        deleteButton.setPreferredSize(new Dimension(30, 30));
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setContentAreaFilled(false);
        buttonPanel.add(deleteButton);

        // Add button panel to the event panel (column 2, row 0)
        gbc.gridx = 2;
        gbc.gridy = 0;
        eventPanel.add(Box.createHorizontalStrut(30)); // Adjust the space on the left (Move buttons to the left)
        eventPanel.add(buttonPanel, gbc);

        return eventPanel;
    }
}
