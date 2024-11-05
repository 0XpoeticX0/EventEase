package Registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.File;

public class RegistrationPage extends JFrame implements ActionListener {

    private JTextField firstNameField, lastNameField, emailField, mobileField, ageField;
    private JPasswordField passwordField;
    private JLabel imageLabel;
    private JButton registerButton, uploadButton;
    private File selectedImage;

    public RegistrationPage() {
        setTitle("User Registration");
        setSize(500, 500);
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

    }

    public void actionPerformed(ActionEvent e){

    }

    public static void main(String[] args){

    }
}
