package com.mycompany.eventmanagement;

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
        createHomeButton();
    }

    private void createProfileLogoButton() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("src\\main\\java\\Resorces\\Images\\p.jpg"));
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

    private void createHomeButton() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("src\\main\\java\\Resorces\\Images\\p.jpg"));

            // Crop or resize image if needed;
            BufferedImage croppedImage = originalImage.getSubimage(1000, 1000, 2000, 2000);
            Image scaledImage = croppedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon homeIcon = new ImageIcon(scaledImage);

            homeButton = new JButton(homeIcon) {
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
            homeButton = new JButton("Home"); // Fallback text if image fails to load
        }

        homeButton.setPreferredSize(new Dimension(50, 50)); // Set button size to match image
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusPainted(false);
    }

    public JButton getProfileLogoButton() {
        return profileLogoButton;
    }

    public JButton getHomeButton() {
        return homeButton;
    }
}
