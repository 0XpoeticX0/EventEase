package Registration;

import javax.swing.*;

import DataBase.DatabaseConnect;
import utils.PasswordUtils;

import static Buttons.HeaderButtons.createEventEaseLogo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Login.Login;

public class RegistrationPage extends JFrame implements ActionListener {

    private final JTextField firstNameField;

    final private JTextField lastNameField, emailField, mobileField, ageField;
    final private JPasswordField passwordField;
    final private JLabel imageLabel;
    final private JButton registerButton, uploadButton;
    private File selectedImage;

    public RegistrationPage() {
        setTitle("User Registration");
        setSize(850, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20)) {
            private final Image backgroundImage = new ImageIcon("src/main/java/Resorces/Bg/bg-reg.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Draw the image scaled to fit the panel
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel eventEaseLogo = createEventEaseLogo();
        eventEaseLogo.setBounds((850 - 200) / 2, 80, 220, 80); // Centered at the top
        eventEaseLogo.setHorizontalAlignment(SwingConstants.CENTER);
        eventEaseLogo.setOpaque(false);
        mainPanel.add(eventEaseLogo, BorderLayout.NORTH);

        JLabel headerLabel = new JLabel("Register New User", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(new Color(122, 178, 211));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        topPanel.setOpaque(false);
        topPanel.add(eventEaseLogo);
        topPanel.add(headerLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        addLabelAndField("First Name:", inputPanel, gbc, 0);
        firstNameField = createTextField(inputPanel, gbc, 0);

        addLabelAndField("Last Name:", inputPanel, gbc, 1);
        lastNameField = createTextField(inputPanel, gbc, 1);

        addLabelAndField("Email:", inputPanel, gbc, 2);
        emailField = createTextField(inputPanel, gbc, 2);

        addLabelAndField("Mobile:", inputPanel, gbc, 3);
        mobileField = createTextField(inputPanel, gbc, 3);

        addLabelAndField("Age:", inputPanel, gbc, 4);
        ageField = createTextField(inputPanel, gbc, 4);

        addLabelAndField("Password:", inputPanel, gbc, 5);
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        inputPanel.add(passwordField, gbc);

        addLabelAndField("Profile Image:", inputPanel, gbc, 6);
        uploadButton = new JButton("Upload Image");
        uploadButton.setFont(new Font("Arial", Font.BOLD, 14));
        uploadButton.setPreferredSize(new Dimension(250, 30));
        uploadButton.setBackground(new Color(87, 197, 182));
        uploadButton.setForeground(Color.WHITE);
        uploadButton.addActionListener(this);
        gbc.gridx = 1;
        inputPanel.add(uploadButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        imageLabel = new JLabel("No image uploaded", JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        imageLabel.setForeground(Color.GRAY);
        inputPanel.add(imageLabel, gbc);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setOpaque(false);
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.insets = new Insets(5, 10, 10, 10);

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));
        registerButton.setPreferredSize(new Dimension(300, 40));
        registerButton.setBackground(new Color(8, 131, 149));
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(this);
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 0;
        bottomPanel.add(registerButton, gbcBottom);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(-15, 10, 10, 10));
        footerPanel.setOpaque(false);
        footerPanel.setBackground(Color.red);

        JLabel alreadyRegisteredLabel = new JLabel("Already registered?");
        alreadyRegisteredLabel.setOpaque(false);
        alreadyRegisteredLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        alreadyRegisteredLabel.setForeground(Color.WHITE);
        footerPanel.add(alreadyRegisteredLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setBackground(Color.decode("#343a40")); // Dark gray background
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.addActionListener(e -> {
            Login loginPage = new Login();
            loginPage.setVisible(true);
            this.dispose();
        });
        footerPanel.add(Box.createHorizontalStrut(-20)); // Horizontal spacing
        footerPanel.add(loginButton);

        gbcBottom.gridy = 1; // Place below the registerButton
        bottomPanel.add(footerPanel, gbcBottom);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);

    }

    private void addLabelAndField(String text, JPanel panel, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(text, JLabel.RIGHT);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);
    }

    private JTextField createTextField(JPanel panel, GridBagConstraints gbc, int row) {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(textField, gbc);
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uploadButton) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImage = fileChooser.getSelectedFile();

                imageLabel.setText("Selected: " + selectedImage.getName());
                imageLabel.setForeground(new Color(0x3B5998));
            }
        } else if (e.getSource() == registerButton) {

            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String mobile = mobileField.getText();
            String ageText = ageField.getText();
            String plainPassword = new String(passwordField.getPassword());
            String password = PasswordUtils.hashPassword(plainPassword);

            // Validation checks
            if (!firstName.matches("[A-Za-z]+")) {
                JOptionPane.showMessageDialog(this, "Invalid first name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!lastName.matches("[A-Za-z]+")) {
                JOptionPane.showMessageDialog(this, "Invalid last name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(this, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!mobile.matches("\\d{10,15}")) {
                JOptionPane.showMessageDialog(this, "Invalid mobile number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int age;
            try {
                age = Integer.parseInt(ageText);
                if (age < 18) {
                    JOptionPane.showMessageDialog(this, "You must be at least 18 years old.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid age. Please enter a number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (plainPassword.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password should be at least 6 characters long.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            ImageIcon icon = null;
            if (selectedImage != null) {
                icon = new ImageIcon(new ImageIcon(selectedImage.getAbsolutePath()).getImage()
                        .getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            }

            // Database insertion
            try (Connection connection = DatabaseConnect.getConnection();
                    PreparedStatement pstmt = connection.prepareStatement(
                            "INSERT INTO users (firstname, lastname, email, mobileNumber, age, password, image) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, email);
                pstmt.setString(4, mobile);
                pstmt.setInt(5, age);
                pstmt.setString(6, password);
                pstmt.setString(7, selectedImage != null ? selectedImage.getAbsolutePath()
                        : "src/main/java/Resorces/Images/demoUser.png");
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registration Successful!\n"
                        + "Name: " + firstName + " " + lastName
                        + "\nEmail: " + email
                        + "\nMobile: " + mobile
                        + "\nAge: " + ageText,
                        "Registration Details",
                        JOptionPane.INFORMATION_MESSAGE,
                        icon);

                Login loginInterface = new Login();
                loginInterface.setVisible(true); // Show the login frame
                this.dispose(); // Close the current frame
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(this, "Already Exist A user Under Same Email");
            }

        }
    }

}
