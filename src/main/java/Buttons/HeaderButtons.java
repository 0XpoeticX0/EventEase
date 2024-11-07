package Buttons;

import Login.Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class HeaderButtons {

    private JButton profileLogoButton;
    private final JLabel eventEaseLogo; // Store the logo here
    private JFrame parentFrame = null; // Reference to the parent frame

    public HeaderButtons(JFrame parentFrame) {
        this.parentFrame = parentFrame; // Set the parent frame
        createProfileLogoButton();
        eventEaseLogo = createEventEaseLogo(); // Create and store the logo
    }
     public HeaderButtons() {
        createProfileLogoButton();
        eventEaseLogo = createEventEaseLogo(); // Create and store the logo
    }

    private JButton createProfileLogoButton() {
        // Check if the user is logged in (this is a placeholder; replace with your actual login check)
        boolean isLoggedIn = checkUserLoginStatus();

        if (isLoggedIn) {
            // If the user is logged in, show the profile photo
            try {
                BufferedImage originalImage = ImageIO.read(new File("src/main/java/Resources/Images/p.jpg"));
                BufferedImage croppedImage = originalImage.getSubimage(1000, 1000, 2000, 2000);
                Image scaledImage = croppedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon profileIcon = new ImageIcon(scaledImage);

                profileLogoButton = new JButton(profileIcon) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        if (getIcon() != null) {
                            Graphics2D g2 = (Graphics2D) g.create();
                            g2.setClip(new Ellipse2D.Float(0, 0, getWidth(), getHeight()));
                            super.paintComponent(g2);
                            g2.dispose();
                        } else {
                            super.paintComponent(g);
                        }
                    }
                };

                profileLogoButton.setPreferredSize(new Dimension(50, 50));
                profileLogoButton.setBorderPainted(false);
                profileLogoButton.setContentAreaFilled(false);
                profileLogoButton.setFocusPainted(false);

                // Add popup menu for logged-in user
                JPopupMenu profilePopupMenu = new JPopupMenu();
                JMenuItem logoutMenuItem = new JMenuItem("Logout");
                JMenuItem recordsMenuItem = new JMenuItem("Records");

                logoutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Logged out successfully."));
                recordsMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Displaying records."));

                profilePopupMenu.add(recordsMenuItem);
                profilePopupMenu.add(logoutMenuItem);

                profileLogoButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            profilePopupMenu.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                });

            } catch (IOException e) {
                profileLogoButton = new JButton("Profile"); // Fallback button in case of error
            }
        } else {
            // If the user is not logged in, show a "Login" button
            profileLogoButton = createStyledButton(new JButton("Login"), "#ffffff", "#343a40");

            // Add action listener to open the Login interface and close the current frame
            profileLogoButton.addActionListener((ActionEvent e) -> {
                Login loginInterface = new Login();
                loginInterface.setVisible(true); // Show the login frame
                parentFrame.dispose(); // Close the current frame
            });
        }

        return profileLogoButton;
    }

    // Placeholder method to check if the user is logged in
    private boolean checkUserLoginStatus() {
        // Replace with actual login logic
        return false; // Return true if the user is logged in, otherwise false
    }

    // Helper method to style buttons (based on your existing createStyledButton method)
    private JButton createStyledButton(JButton button, String foregroundColor, String backgroundColor) {
        button.setForeground(Color.decode(foregroundColor));
        button.setBackground(Color.decode(backgroundColor));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    public JLabel createEventEaseLogo() {
        JLabel eventEaseLogo = new JLabel("EventEase", SwingConstants.CENTER);
        eventEaseLogo.setFont(new Font("SansSerif", Font.BOLD, 22));
        eventEaseLogo.setPreferredSize(new Dimension(150, 50)); // Set preferred size for layout purposes
        eventEaseLogo.setForeground(new Color(58, 123, 213)); // Set a visible text color
        eventEaseLogo.setOpaque(false); // Transparent background
        return eventEaseLogo;
    }

    public JButton getProfileLogoButton() {
        return profileLogoButton;
    }

    public JLabel getEventEaseLogo() {
        return eventEaseLogo; // Provide a getter for the logo
    }
}
