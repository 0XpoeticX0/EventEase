package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel registerLabel;
    private JButton togglePasswordButton;
    private boolean isPasswordVisible = false;

    public Login() {
        setTitle("Login Interface");
        setSize(700, 600);
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

        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 19));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        // Create password panel to hold password field and toggle button
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setOpaque(false); // Make the background transparent

        // Initialize the toggle button with "Show"
        togglePasswordButton = new JButton("Show");
        togglePasswordButton.setFont(new Font("Arial", Font.PLAIN, 12));
        togglePasswordButton.setFocusable(false);

        // Add an action listener to the button to toggle password visibility
        togglePasswordButton.addActionListener(e -> togglePasswordVisibility());

        // Add password field and toggle button to password panel
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(togglePasswordButton, BorderLayout.EAST);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.BLACK);

        // Register Label
        registerLabel = new JLabel("Don't have an account? Register");
        registerLabel.setFont(new Font("Arial", Font.BOLD, 15));
        registerLabel.setForeground(Color.WHITE);

        // Mouse listener to simulate a link effect
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(mainPanel, "Redirecting to Registration Page...");
                // Code to open the registration window can go here
            }
        });

        // Adding components to main panel using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Password:"), gbc);
        
// Add the password panel (which contains the password field and toggle button) instead of the password field directly
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(passwordPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(registerLabel, gbc); // Add the register label below the login button

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getUsername();
                String password = getPassword();
                JOptionPane.showMessageDialog(mainPanel, "Login attempt by: " + username);
            }
        });
    }

    // Method to toggle the visibility of the password
    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;
        if (isPasswordVisible) {
            passwordField.setEchoChar((char) 0); // Show password
            togglePasswordButton.setText("Hide");
        } else {
            togglePasswordButton.setText("Show");
        }
    }

    // Getter and Setter for Username
    public String getUsername() {
        return usernameField.getText();
    }

    public void setUsername(String username) {
        this.usernameField.setText(username);
    }

    // Getter and Setter for Password
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setPassword(String password) {
        this.passwordField.setText(password);
    }

}