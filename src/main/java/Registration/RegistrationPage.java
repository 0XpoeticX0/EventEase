package Registration;

import javax.swing.*;

import DataBase.DatabaseConnect;
import utils.PasswordUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Login.Login;

public class RegistrationPage extends JFrame implements ActionListener {

    private JTextField firstNameField, lastNameField, emailField, mobileField, ageField;
    private JPasswordField passwordField;
    private JLabel imageLabel;
    private JButton registerButton, uploadButton;
    private File selectedImage;

    public RegistrationPage() {
        setTitle("User Registration");
        setSize(850, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerLabel = new JLabel("Register New User", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(0x3B5998));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("First Name:"), gbc);

        gbc.gridx = 1;
        firstNameField = new JTextField(15);
        firstNameField.setToolTipText("Enter your first name");
        inputPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Last Name:"), gbc);

        gbc.gridx = 1;
        lastNameField = new JTextField(15);
        lastNameField.setToolTipText("Enter your last name");
        inputPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        emailField.setToolTipText("Enter your email address");
        inputPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Mobile:"), gbc);

        gbc.gridx = 1;
        mobileField = new JTextField(15);
        mobileField.setToolTipText("Enter your mobile number");
        inputPanel.add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Age:"), gbc);

        gbc.gridx = 1;
        ageField = new JTextField(15);
        ageField.setToolTipText("Enter your age");
        inputPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setToolTipText("Enter your password");
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Profile Image:"), gbc);

        gbc.gridx = 1;
        uploadButton = new JButton("Upload Image");
        uploadButton.setBackground(new Color(0x4CAF50));
        uploadButton.setForeground(Color.WHITE);
        uploadButton.addActionListener(this);
        inputPanel.add(uploadButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        imageLabel = new JLabel("No image uploaded", JLabel.CENTER);
        imageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        imageLabel.setForeground(Color.GRAY);
        inputPanel.add(imageLabel, gbc);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(0x008CBA)); // Blue button
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.addActionListener(this);
        mainPanel.add(registerButton, BorderLayout.SOUTH);
        add(mainPanel);

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
                pstmt.setString(7, selectedImage != null ? selectedImage.getAbsolutePath() : null);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registration Successful!\n" +
                        "Name: " + firstName + " " + lastName +
                        "\nEmail: " + email +
                        "\nMobile: " + mobile +
                        "\nAge: " + ageText,
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
