package Buttons;

import javax.swing.*;

public class ContactUsPanel {
     public static void showContactUs() {
        String message = "Contact Us\nPhone: 123-456-7890\nEmail: contact@eventmanagement.com";
        JOptionPane.showMessageDialog(null, message, "Contact Us", JOptionPane.INFORMATION_MESSAGE);
    }
}
