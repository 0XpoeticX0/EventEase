package Buttons;

import Login.Login;
import Login.ValidateLogin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.geom.Ellipse2D;

public final class HeaderButtons {

    private JButton profileLogoButton;
    private final JLabel eventEaseLogo; // Store the logo here
    private JFrame parentFrame = null; // Reference to the parent frame
    private JPanel menuPanel; // Panel that will hold the menu options
    private boolean isMenuOpen = false; // To track if the menu is open or closed

    public HeaderButtons(JFrame parentFrame) {
        this.parentFrame = parentFrame; // Set the parent frame
        createProfileLogoButton();
        eventEaseLogo = createEventEaseLogo(); // Create and store the logo
        createMenuPanel(); // Initialize the menu panel

        // Add MouseListener to the parent frame to close the menu if clicked outside
        parentFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isMenuOpen && !menuPanel.getBounds().contains(e.getPoint())
                        && !profileLogoButton.getBounds().contains(e.getPoint())) {
                    // Close the menu if clicked outside
                    toggleMenu();
                }
            }
        });
    }

    public HeaderButtons() {
        createProfileLogoButton();
        eventEaseLogo = createEventEaseLogo(); // Create and store the logo
        createMenuPanel(); // Initialize the menu panel
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

                // Add action listener to open the menu
                profileLogoButton.addActionListener(e -> toggleMenu());

            } catch (IOException e) {
                System.out.println(e);
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

    // Helper method to style buttons
    private JButton createStyledButton(JButton button, String foregroundColor, String backgroundColor) {
        button.setForeground(Color.decode(foregroundColor));
        button.setBackground(Color.decode(backgroundColor));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    // Create the "EventEase" logo
    public static JLabel createEventEaseLogo() {
        JLabel eventEaseLogo = new JLabel("EventEase", SwingConstants.CENTER);
        eventEaseLogo.setFont(new Font("SansSerif", Font.BOLD, 22));
        eventEaseLogo.setPreferredSize(new Dimension(150, 50)); // Set preferred size for layout purposes
        eventEaseLogo.setForeground(new Color(58, 123, 213)); // Set a visible text color
        eventEaseLogo.setOpaque(false); // Transparent background
        return eventEaseLogo;
    }

    // Create a simple menu panel
    private void createMenuPanel() {
        // Main menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Use vertical BoxLayout for buttons
        menuPanel.setBackground(Color.decode("#343a40")); // Menu background color
        menuPanel.setPreferredSize(new Dimension(150, 200)); // Adjust size as needed
        menuPanel.setVisible(false); // Initially hidden

        
    }

    // Toggle the visibility of the menu (slide in/out effect)
    private void toggleMenu() {
        if (isMenuOpen) {
            menuPanel.setVisible(false);
        } else {
            menuPanel.setVisible(true);
        }
        isMenuOpen = !isMenuOpen;

        // Revalidate and repaint the parent frame to refresh the layout
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    public JButton getProfileLogoButton() {
        return profileLogoButton;
    }

    public JLabel getEventEaseLogo() {
        return eventEaseLogo; // Provide a getter for the logo
    }

    // Add the menu panel to the parent frame (you'll need to call this from your main window setup)
    public void addMenuToFrame(JFrame frame) {
        frame.add(menuPanel, BorderLayout.EAST); // Adjust the position as needed
    }
}
