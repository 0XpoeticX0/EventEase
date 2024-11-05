package Buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HeaderButtons {

    private JButton profileLogoButton;
    private JButton homeButton;

    public HeaderButtons() {
        createProfileLogoButton();
        createEventEaseLogo();
    }

    private void createProfileLogoButton() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("src/main/java/Resorces/Images/p.jpg"));
            BufferedImage croppedImage = originalImage.getSubimage(1000, 1000, 2000, 2000);
            Image scaledImage = croppedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon profileIcon = new ImageIcon(scaledImage);

            profileLogoButton = new JButton(profileIcon) {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getIcon() != null) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setClip(new Ellipse2D.Float(0, 0, getWidth(), getHeight()));
                        super.paintComponent(g2);
                        g2.dispose();
                    } else {
                        super.paintComponent(g);
                    }
                }
            };
        } catch (IOException e) {
            profileLogoButton = new JButton("Profile"); // Fallback button in case of error
        }

        profileLogoButton.setPreferredSize(new Dimension(50, 50));
        profileLogoButton.setBorderPainted(false);
        profileLogoButton.setContentAreaFilled(false);
        profileLogoButton.setFocusPainted(false);

        JPopupMenu profilePopupMenu = new JPopupMenu();
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        JMenuItem recordsMenuItem = new JMenuItem("Records");

        logoutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Logged out successfully."));
        recordsMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Displaying records."));

        profilePopupMenu.add(recordsMenuItem);
        profilePopupMenu.add(logoutMenuItem);

        profileLogoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    profilePopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public static JLabel createEventEaseLogo() {
        JLabel eventEaseLogo = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // Enable anti-aliasing for smooth text
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set gradient color for text
                GradientPaint gradientPaint = new GradientPaint(
                        0, 0, new Color(58, 123, 213), // Start color (blue)
                        getWidth(), getHeight(), new Color(58, 213, 151) // End color (green)
                );
                g2d.setPaint(gradientPaint);

                // Set font style and size
                g2d.setFont(new Font("SansSerif", Font.BOLD, 22));

                // Draw the text centered
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth("EventEase")) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2d.drawString("EventEase", x, y);

                g2d.dispose();
            }
        };

        eventEaseLogo.setHorizontalAlignment(SwingConstants.CENTER);
        eventEaseLogo.setVerticalAlignment(SwingConstants.CENTER);
        eventEaseLogo.setPreferredSize(new Dimension(150, 50)); // Set preferred size for layout purposes
        eventEaseLogo.setOpaque(false); // Transparent background

        return eventEaseLogo;
    }

    public JButton getProfileLogoButton() {
        return profileLogoButton;
    }

    public JButton getHomeButton() {
        return homeButton;
    }
}
