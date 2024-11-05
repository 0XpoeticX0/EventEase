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
    }

    public void actionPerformed(ActionEvent e){

    }

    public static void main(String[] args){

    }
}
