package Buttons;

import javax.swing.*;

public class AboutUsPanel {
    public static void showAboutUs() {
        String message = "About Us\nWe are an event management company dedicated to providing quality events.";
        JOptionPane.showMessageDialog(null, message, "About Us", JOptionPane.INFORMATION_MESSAGE);
    }
}
