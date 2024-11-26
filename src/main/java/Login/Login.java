package Login;

import Events.EventPage;
import Registration.RegistrationPage;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class Login extends JFrame {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;
    private final JLabel newHereLabel;
    private boolean isPasswordVisible = false;

    public Login() {
        setTitle("Login Interface");
        setSize(850, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, getWidth(), getHeight(), Color.MAGENTA);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel);

        // Create login panel components
        JLabel titleLabel = new JLabel("Hi There! Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);

        // Define the fixed width and height for the text fields
        Dimension fixedSize = new Dimension(200, 30);

        // Username panel with fixed size and null layout for manual positioning
        JPanel usernamePanel = new JPanel(null);
        usernamePanel.setPreferredSize(fixedSize);
        usernamePanel.setMinimumSize(fixedSize);
        usernamePanel.setMaximumSize(fixedSize);
        usernamePanel.setOpaque(false); // Make it transparent to match the background

        // Username field with fixed size and font
        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 25));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        usernameField.setPreferredSize(fixedSize);
        usernameField.setBounds(0, 0, fixedSize.width, fixedSize.height);

        // Add username field to the username panel
        usernamePanel.add(usernameField);

        // Password layered panel with fixed size
        JLayeredPane passwordLayeredPane = new JLayeredPane();
        passwordLayeredPane.setPreferredSize(fixedSize);
        passwordLayeredPane.setMinimumSize(fixedSize);
        passwordLayeredPane.setMaximumSize(fixedSize);

        // Password field
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 25));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        passwordField.setPreferredSize(fixedSize);
        passwordField.setMinimumSize(fixedSize);
        passwordField.setMaximumSize(fixedSize);
        passwordField.setBounds(0, 0, fixedSize.width, fixedSize.height);
        passwordField.setEchoChar('\u2022'); // Set default masking character

        // Checkbox overlay
        JCheckBox togglePasswordCheckbox = new JCheckBox();
        togglePasswordCheckbox.setOpaque(false); // Transparent background for the checkbox
        togglePasswordCheckbox.setFocusable(false);
        togglePasswordCheckbox.setBorder(null); // Remove border for a cleaner look
        togglePasswordCheckbox.setBounds(fixedSize.width - 21, (fixedSize.height - 20) / 2, 20, 20); // Align to the
                                                                                                     // middle-right

        // Create a filled white square for the unchecked icon
        Icon uncheckedIcon = new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB) {
            {
                Graphics2D g2 = createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(3, 3, 12, 12); // Fill the square with white
                g2.setColor(Color.BLACK); // Border color
                g2.drawRect(3, 3, 12, 12); // Draw border
                g2.dispose();
            }
        });

        // Create a filled white square with a black checkmark for the checked icon
        Icon checkedIcon = new ImageIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB) {
            {
                Graphics2D g2 = createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(2, 2, 16, 16); // Fill the square with white
                g2.setColor(Color.BLACK); // Border color
                g2.drawRect(2, 2, 16, 16); // Draw border

                // Set the stroke (line width) to make the checkmark bolder
                g2.setStroke(new BasicStroke(2)); // 3 pixels thickness for bolder lines

                // Draw the checkmark with the bolder lines
                g2.drawLine(5, 10, 9, 14); // Draw first line of checkmark
                g2.drawLine(9, 14, 15, 6); // Draw second line of checkmark

                g2.dispose();
            }
        });

        // Set the custom icons for the checkbox
        togglePasswordCheckbox.setIcon(uncheckedIcon);
        togglePasswordCheckbox.setSelectedIcon(checkedIcon);
        // Add checkbox action listener to toggle password visibility
        togglePasswordCheckbox
                .addActionListener(e -> togglePasswordVisibility());

        // Add password field and checkbox to the layered pane
        passwordLayeredPane.add(passwordField, JLayeredPane.DEFAULT_LAYER);
        passwordLayeredPane.add(togglePasswordCheckbox, JLayeredPane.PALETTE_LAYER);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.BLACK);

        // Register button
        newHereLabel = new JLabel("New here?");
        newHereLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        newHereLabel.setForeground(Color.WHITE);

        registerButton = createStyledButton("Register", "#ffffff", "#343a40");
        registerButton.addActionListener(e -> {
            RegistrationPage registrationPage = new RegistrationPage();
            registrationPage.setVisible(true);
            this.dispose();
        });

        // New here panel
        JPanel newHerePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        newHerePanel.setOpaque(false);
        newHerePanel.add(Box.createHorizontalStrut(80));
        newHerePanel.add(newHereLabel);
        newHerePanel.add(Box.createHorizontalStrut(-10));
        newHerePanel.add(registerButton);

        // Adding components to main panel using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 60, 10, 10);
        mainPanel.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(usernamePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(passwordLayeredPane, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Aligns the button to the CENTER
        gbc.insets = new Insets(10, -5, 10, 10);
        mainPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(newHerePanel, gbc);

        // Action listener for login button
        loginButton.addActionListener((ActionEvent e) -> {
            String username = getUsername();
            String password = getPassword();

            ValidateLogin validator = new ValidateLogin();

            if (validator.validateLogin(username, password)) {
                // Open the EventPage after successful login
                EventPage eventPage = new EventPage(); // Instantiate the EventPage
                eventPage.setVisible(true); // Show the EventPage

                // Close the current login window
                dispose(); // Close the login frame

            } else {
                JOptionPane.showMessageDialog(mainPanel, "Username or password invalid.");
            }
        });
    }

    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;
        passwordField.setEchoChar(isPasswordVisible ? (char) 0 : '\u2022');
    }

    private JButton createStyledButton(String text, String textColorHex, String bgColorHex) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setForeground(Color.decode(textColorHex));
        button.setBackground(Color.decode(bgColorHex));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public void setUsername(String username) {
        this.usernameField.setText(username);
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setPassword(String password) {
        this.passwordField.setText(password);
    }
}
