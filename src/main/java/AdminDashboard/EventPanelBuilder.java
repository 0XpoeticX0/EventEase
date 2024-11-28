package AdminDashboard;

import Events.Event;
import javax.swing.*;
import java.awt.*;

public class EventPanelBuilder {

    /**
     * Method to return a JLabel for the event name.
     */
    public JLabel getEventNameLabel(Event event) {
        JLabel nameLabel = new JLabel(event.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        return nameLabel;
    }

    /**
     * Method to return a JPanel for the event price.
     */
    public JPanel getEventPricePanel(Event event) {
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pricePanel.setOpaque(false); // Transparent background

        JLabel priceLabel = new JLabel("Price: $" + event.getPrice());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setForeground(Color.BLACK);

        pricePanel.add(priceLabel);
        return pricePanel;
    }

    /**
     * Method to return a JPanel for the event status.
     */
    public JPanel getEventStatusPanel(Event event) {
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        statusPanel.setOpaque(false); // Transparent background

        JLabel statusLabel = new JLabel("Status: Free");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(Color.BLACK);

        statusPanel.add(statusLabel);
        return statusPanel;
    }

    /**
     * Method to return a JPanel for the event activation buttons.
     */
    public JPanel getEventActivationPanel() {
        JPanel activationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        activationPanel.setOpaque(false); // Transparent background

        JButton publishButton = new JButton("Publish");
        publishButton.setBackground(Color.GREEN);
        publishButton.setForeground(Color.WHITE);
        publishButton.setFont(new Font("Arial", Font.BOLD, 12));
        publishButton.setFocusPainted(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setFocusPainted(false);

        activationPanel.add(publishButton);
        activationPanel.add(deleteButton);
        return activationPanel;
    }

    /**
     * Method to build a complete event panel using components.
     */
    public JPanel buildCompleteEventPanel(Event event) {
        JPanel eventPanel = new JPanel(new BorderLayout());
        eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        eventPanel.setBackground(Color.WHITE);

        // Add name label to the top
        eventPanel.add(getEventNameLabel(event), BorderLayout.NORTH);

        // Add price and status panels in the center
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.setOpaque(false);
        centerPanel.add(getEventPricePanel(event));
        centerPanel.add(getEventStatusPanel(event));
        eventPanel.add(centerPanel, BorderLayout.CENTER);

        // Add activation buttons at the bottom
        eventPanel.add(getEventActivationPanel(), BorderLayout.SOUTH);

        return eventPanel;
    }
}
