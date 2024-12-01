package AdminDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanelBuilder {

    public static JPanel buildCompleteUserPanel(User user) {
        // Main user panel using GridBagLayout for better control
        JPanel userPanel = new JPanel(new GridBagLayout());
        userPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        userPanel.setBackground(Color.BLACK);
        userPanel.setPreferredSize(new Dimension(600, 100)); // Adjust size as necessary

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Left: Profile picture as a button
        JButton imageButton = new JButton();
        imageButton.setPreferredSize(new Dimension(50, 50)); // Circle size
        imageButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Border around the image
        imageButton.setFocusPainted(false); // Remove focus border
        imageButton.setContentAreaFilled(false); // Transparent button background

        // Set the user's image as the button icon
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(user.getImage())
                .getImage()
                .getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        imageButton.setIcon(imageIcon);

        // Add an action listener to handle button clicks
        imageButton.addActionListener(e -> {
            // Fetch user details
            String userInfo = "User Details:\n"
                    + "Name: " + user.getFullName() + "\n"
                    + "Email: " + user.getEmail() + "\n"
                    + "Status: " + user.getStatus();

            // Show user details in a dialog
            int response = JOptionPane.showConfirmDialog(
                    userPanel,
                    userInfo + "\nDo you want to view this user's booked events?",
                    "User Info",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            // If user selects "YES", load booked events
            if (response == JOptionPane.YES_OPTION) {
                String u_id = String.valueOf(user.getU_id()); // Assuming user has a method getU_id()
                EventBookedByUser load = new EventBookedByUser(user);
                load.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                load.setVisible(true);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        userPanel.add(imageButton, gbc);

        // Space between the image and the text
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 5); // 10px gap
        userPanel.add(Box.createHorizontalStrut(10), gbc);

        // Middle: User Name, Email, and Status
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new GridBagLayout());

        GridBagConstraints textGBC = new GridBagConstraints();
        textGBC.fill = GridBagConstraints.HORIZONTAL;
        textGBC.insets = new Insets(5, 5, 5, 5);

        // User Name Label
        JLabel userNameLabel = new JLabel("<html><div style='width: 250px;'>" + user.getFullName() + "</div></html>"); // HTML to wrap text
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setPreferredSize(new Dimension(250, 50)); // Ensure space for text wrap
        textGBC.gridx = 0;
        textGBC.gridy = 0;
        textPanel.add(userNameLabel, textGBC);

        // Email Label
        JLabel emailLabel = new JLabel(user.getEmail());
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(Color.WHITE);
        textGBC.gridx = 0;
        textGBC.gridy = 1;
        textPanel.add(emailLabel, textGBC);

        // Status Label
        JLabel statusLabel = new JLabel(user.getStatus().equalsIgnoreCase("active") ? "Active" : "Blocked");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(user.getStatus().equalsIgnoreCase("active") ? Color.GREEN : Color.RED);
        textGBC.gridx = 0;
        textGBC.gridy = 2;
        textPanel.add(statusLabel, textGBC);

        gbc.gridx = 2;
        gbc.gridy = 0;
        userPanel.add(textPanel, gbc);

        // Right: Block/Unblock Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);

        // Block/Unblock button with dynamic icon based on status
        JButton blockButton = new JButton();
        blockButton.setPreferredSize(new Dimension(30, 30));

        // Set the background to transparent
        blockButton.setContentAreaFilled(false);
        blockButton.setBorderPainted(false); // Remove border
        blockButton.setFocusPainted(false); // Remove focus border if clicked

        // Dynamically set the block/unblock icon based on user status
        blockButton.setIcon(new ImageIcon(UserPanelBuilder.class.getResource(user.getStatus().equalsIgnoreCase("active")
                ? "/Icons/lock-keyhole.png" // Block icon when Active
                : "/Icons/lock-keyhole-open.png"))); // Unblock icon when Blocked

        blockButton.addActionListener(new ActionListener() {
            private boolean isCurrentlyBlocked = user.getStatus().equalsIgnoreCase("inactive");

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCurrentlyBlocked) {
                    statusLabel.setText("Active");
                    statusLabel.setForeground(Color.GREEN);
                    blockButton.setIcon(new ImageIcon(UserPanelBuilder.class.getResource("/Icons/lock-keyhole.png"))); // Show block icon
                    DatabaseHelper.updateUserStatus(user.getU_id(), "active");
                    JOptionPane.showMessageDialog(userPanel, "User has been unblocked.");
                } else {
                    statusLabel.setText("Blocked");
                    statusLabel.setForeground(Color.RED);
                    blockButton.setIcon(new ImageIcon(UserPanelBuilder.class.getResource("/Icons/lock-keyhole-open.png"))); // Show unblock icon
                    DatabaseHelper.updateUserStatus(user.getU_id(), "inactive");
                    JOptionPane.showMessageDialog(userPanel, "User has been blocked.");
                }
                isCurrentlyBlocked = !isCurrentlyBlocked;
            }
        });

        buttonPanel.add(blockButton);

        // Adjusting button's position
        gbc.gridx = 3; // Right-most position
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 0); // Adjust as necessary
        userPanel.add(buttonPanel, gbc);

        return userPanel;
    }
}
