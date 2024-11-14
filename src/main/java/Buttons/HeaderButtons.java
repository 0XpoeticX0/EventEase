package Buttons;

import Login.Login;
import Login.ValidateLogin;  // Import the ValidateLogin class to check the login status
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
        // Check if the user is logged in using ValidateLogin
        boolean isLoggedIn = ValidateLogin.isLoggedIn();

        if (isLoggedIn) {
            // If the user is logged in, show the profile photo
            try {
                BufferedImage originalImage = ImageIO.read(new File("src/main/java/Resorces/Images/p.jpg"));
                BufferedImage croppedImage = originalImage.getSubimage(1000, 1000, 2000, 2000); // Cropping to a square
                Image scaledImage = croppedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon profileIcon = new ImageIcon(scaledImage);

                profileLogoButton = new JButton(profileIcon) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        // Ensure the button remains circular
                        if (getIcon() != null) {
                            Graphics2D g2 = (Graphics2D) g.create();
                            g2.setClip(new Ellipse2D.Float(0, 0, getWidth(), getHeight())); // Round the button's content
                            super.paintComponent(g2);
                            g2.dispose();
                        } else {
                            super.paintComponent(g);
                        }
                    }

                    @Override
                    public Dimension getPreferredSize() {
                        // Set preferred size as square for a round shape
                        return new Dimension(50, 50); // Maintain square size to ensure it's round
                    }
                };

                profileLogoButton.setPreferredSize(new Dimension(50, 50)); // Set the size of the button
                profileLogoButton.setBorderPainted(false);
                profileLogoButton.setContentAreaFilled(false);
                profileLogoButton.setFocusPainted(false);
                profileLogoButton.setBackground(new Color(0, 0, 0, 0)); // Set transparent background

                // Add popup menu for logged-in user
                JPopupMenu profilePopupMenu = new JPopupMenu();
                JMenuItem logoutMenuItem = new JMenuItem("Logout");
                JMenuItem recordsMenuItem = new JMenuItem("Records");

                logoutMenuItem.addActionListener(e -> {
                    // You can handle the logout logic here
                    ValidateLogin.loggedInUserEmail = null; // Clear the logged-in email on logout
                    JOptionPane.showMessageDialog(null, "Logged out successfully.");
                });

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
                System.out.println(e);
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

    // Helper method to style buttons (based on your existing createStyledButton method)
    private JButton createStyledButton(JButton button, String foregroundColor, String backgroundColor) {
        button.setForeground(Color.decode(foregroundColor));
        button.setBackground(Color.decode(backgroundColor));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    public static JLabel createEventEaseLogo() {
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
