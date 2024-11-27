package Buttons;

import Events.EventPage;
import Login.Login;
import javax.swing.*;

import AdminDashboard.AdminDash;
import CurrentUser.CurrentUser;
import DataBase.LoggedInUser;
import static LogOut.LogOut.logOut;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.geom.Ellipse2D;
import UserDashboard.UserDash;

public final class HeaderButtons {

    private JButton profileLogoButton;
    private final JLabel eventEaseLogo; // Store the logo here
    private JFrame parentFrame = null; // Reference to the parent frame
    private JPanel menuPanel; // Panel that will hold the menu options
    private JPanel overlayPanel; // Transparent overlay panel
    private boolean isMenuOpen = false; // To track if the menu is open or closed

    public HeaderButtons(JFrame parentFrame) {
        this.parentFrame = parentFrame; // Set the parent frame
        createProfileLogoButton();
        eventEaseLogo = createEventEaseLogo(); // Create and store the logo
        createMenuPanel(); // Initialize the menu panel
        createOverlayPanel(); // Initialize the overlay panel
    }

    public HeaderButtons() {
        createProfileLogoButton();
        eventEaseLogo = createEventEaseLogo(); // Create and store the logo
        createMenuPanel(); // Initialize the menu panel
        createOverlayPanel(); // Initialize the overlay panel
    }

    CurrentUser currentUser = LoggedInUser.getInstance().getCurrentUser();

    private JButton createProfileLogoButton() {
        // Check if the user is logged in using ValidateLogin
        // boolean isLoggedIn = ValidateLogin.isLoggedIn();

        if (currentUser != null) {
            // If the user is logged in, show the profile photo
            try {
                // Get the image path; if user image is null, use default image
                String imagePath = currentUser.getImage() != null ? currentUser.getImage()
                        : "src/main/java/Resorces/Images/p.jpg";

                // Load the image
                BufferedImage originalImage = ImageIO.read(new File(imagePath));

                // Determine the cropping size based on the image's dimensions
                int width = originalImage.getWidth();
                int height = originalImage.getHeight();

                // If the image is too small to crop, we'll just use the full image
                int cropSize = Math.min(width, height);

                // Crop the image to a square (use the top-left corner for simplicity)
                BufferedImage croppedImage = originalImage.getSubimage(0, 0, cropSize, cropSize);

                // Resize the cropped image to 50x50 pixels
                Image scaledImage = croppedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon profileIcon = new ImageIcon(scaledImage);

                // Create the profile button with circular shape
                profileLogoButton = new JButton(profileIcon) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        // Ensure the button remains circular
                        if (getIcon() != null) {
                            Graphics2D g2 = (Graphics2D) g.create();

                            // Set the clip to circular
                            g2.setClip(new Ellipse2D.Float(0, 0, getWidth(), getHeight()));

                            // Center the image in the circular button
                            int x = (getWidth() - profileIcon.getIconWidth()) / 2; // Center horizontally
                            int y = (getHeight() - profileIcon.getIconHeight()) / 2; // Center vertically
                            g2.drawImage(profileIcon.getImage(), x, y, this); // Draw the image at the calculated
                            // position

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

                // Set properties for the profile button
                profileLogoButton.setPreferredSize(new Dimension(50, 50)); // Set the size of the button
                profileLogoButton.setBorderPainted(false);
                profileLogoButton.setContentAreaFilled(false);
                profileLogoButton.setFocusPainted(false);
                profileLogoButton.setBackground(new Color(0, 0, 0, 0)); // Set transparent background

                // Add action listener to open the menu (toggleMenu method should be implemented
                // elsewhere)
                profileLogoButton.addActionListener(e -> toggleMenu());

            } catch (IOException e) {
                e.printStackTrace(); // Log the exception
                // Optionally, handle the error by setting a fallback image or showing a default
                // icon
                try {
                    BufferedImage defaultImage = ImageIO.read(new File("src/main/java/Resorces/Images/p.jpg"));
                    Image scaledImage = defaultImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    ImageIcon profileIcon = new ImageIcon(scaledImage);

                    profileLogoButton = new JButton(profileIcon);
                } catch (IOException ex) {
                    ex.printStackTrace(); // Log the second exception if it occurs
                }
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
        eventEaseLogo.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 32)); // Bold italic Cambria
        eventEaseLogo.setForeground(Color.WHITE); // Set text color (white for blue background)

        // Load the icon
        ImageIcon calendarIcon = new ImageIcon(
                HeaderButtons.class.getResource("/Icons/calendar-range.png"));
        eventEaseLogo.setIcon(calendarIcon);
        eventEaseLogo.setHorizontalTextPosition(SwingConstants.RIGHT); // Place text to the right of the icon
        eventEaseLogo.setIconTextGap(10); // Add some spacing between icon and text

        return eventEaseLogo;
    }

    // Create a simple menu panel
    private void createMenuPanel() {
        // Main menu panel with BorderLayout to separate header and body
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout()); // Use BorderLayout for header and body separation
        menuPanel.setPreferredSize(new Dimension(150, 200)); // Adjust size as needed
        menuPanel.setOpaque(true);
        menuPanel.setVisible(false); // Initially hidden

        // Header panel (fixed height)
        JPanel headerPanel = new JPanel(); // Panel for header section
        headerPanel.setBackground(Color.decode("#343a40")); // Set header background color (blue)
        headerPanel.setPreferredSize(new Dimension(150, 80)); // Set header height to 50px

        JButton profileLogoButton = getProfileLogoButton(); // Get the profile logo button
        headerPanel.add(Box.createHorizontalStrut(60)); // Optional spacing to the right of the button
        headerPanel.add(profileLogoButton, BorderLayout.EAST); // Add it to the header

        // Add the headerPanel to the top of the menuPanel
        menuPanel.add(headerPanel, BorderLayout.NORTH); // Add header to the top (north) position

        // Body panel (remaining space)
        JPanel bodyPanel = new JPanel(); // Panel for body section
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS)); // Stack buttons vertically
        bodyPanel.setBackground(Color.decode("#343a40")); // Set body background color (dark gray)

        // Profile and Logout buttons for the body
        JButton profileItem = createStyledButton(new JButton("Profile"), "#ffffff", "#343a40");
        profileItem.addActionListener(e -> {
            // Create and show the UserDash JFrame
            // This is generated by NetBeans from your .form file

            // Make the dashboard visible
            if (currentUser.getRole().equals("user")) {
                UserDash u_dashboard = new UserDash();
                u_dashboard.setVisible(true);
            } else {
                AdminDash a_dashboard = new AdminDash();
                a_dashboard.setVisible(true);
            }

            // Close the parent frame if it is not null
            if (parentFrame != null) {
                parentFrame.dispose();
            }
        });

        JButton logoutItem = createStyledButton(new JButton("Logout"), "#ffffff", "#343a40");
        // Logout button action
        logoutItem.addActionListener((ActionEvent e) -> {
            // Clear the logged-in user information (if any)
            if (currentUser != null) {
                logOut();
            } // or any relevant user information

            // Close the current EventPage
            parentFrame.dispose(); // Close the current frame, assuming parentFrame is EventPage

            // Open a new instance of the Login page
            EventPage eventPage = new EventPage();
            eventPage.setVisible(true);
        });

        // Add buttons to bodyPanel
        profileItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        bodyPanel.add(profileItem);
        bodyPanel.add(Box.createVerticalStrut(5)); // Small gap between buttons
        bodyPanel.add(logoutItem);

        // Add the bodyPanel to the center of the menuPanel
        menuPanel.add(bodyPanel, BorderLayout.CENTER); // Add body to the center (remaining space)

        // Optionally add vertical glue at the bottom of bodyPanel if needed
        bodyPanel.add(Box.createVerticalGlue());
    }

    // Create the overlay panel
    private void createOverlayPanel() {
        overlayPanel = new JPanel();
        overlayPanel.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent black
        overlayPanel.setOpaque(true);
        overlayPanel.setVisible(false);

        overlayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isMenuOpen) {
                    toggleMenu(); // Close menu when clicking on the overlay
                }
            }
        });
    }

    // Toggle menu and overlay visibility
    private void toggleMenu() {
        isMenuOpen = !isMenuOpen;
        menuPanel.setVisible(isMenuOpen);
        overlayPanel.setVisible(isMenuOpen);
    }

    public JButton getProfileLogoButton() {
        return createProfileLogoButton();
    }

    public JLabel getEventEaseLogo() {
        return eventEaseLogo;
    }

    // Add components to the parent frame
    public void addMenuToFrame(JFrame frame) {
        JLayeredPane layeredPane = frame.getLayeredPane();

        // Set overlay and menu bounds
        overlayPanel.setBounds(0, 0, 690, 850);
        menuPanel.setBounds(690, 10, 210, 850);

        // Add components to layered pane
        layeredPane.add(overlayPanel, JLayeredPane.DEFAULT_LAYER); // Lower layer for overlay
        layeredPane.add(menuPanel, JLayeredPane.POPUP_LAYER); // Higher layer for menu
    }
}
