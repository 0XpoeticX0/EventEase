/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package EventBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import CurrentUser.CurrentUser;
import DataBase.DatabaseConnect;
import DataBase.LoggedInUser;

/**
 *
 * @author MaHir0
 */
public class EventBooking extends javax.swing.JFrame {
    private static JFrame currentlyBookingEvent = null;
    CurrentUser currentUser = LoggedInUser.getInstance().getCurrentUser();
    static String e_id;
    static double e_price;

    double price;

    /**
     * Creates new form EventBooking
     */
    public EventBooking() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(224, 225, 221));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));

        jLabel1.setFont(new java.awt.Font("Bitstream Charter", 1, 36)); // NOI18N
        jLabel1.setText("Event Booking");

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel2.setText("Select a Date:");

        jLabel4.setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
        jLabel4.setText("You have to pay $");

        jLabel5.setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
        jLabel5.setText(String.valueOf(e_price));

        jButton1.setBackground(new java.awt.Color(244, 162, 97));
        jButton1.setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/credit-card.png"))); // NOI18N
        jButton1.setText("Pay");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setPreferredSize(new java.awt.Dimension(60, 35));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(180, 180, 180)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(214, 214, 214)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(123, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addGap(60, 60, 60)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(90, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseClicked
        java.util.Date selectedDate = jDateChooser1.getDate();

        if (selectedDate != null) {
            // Format the date for display
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(selectedDate);

            // Print the date along with e_id and e_price (for debugging)
            System.out.println("Event ID: " + e_id);
            System.out.println("Price: " + e_price);
            System.out.println("Selected Date: " + formattedDate);

            // Prepare the SQL query to insert booking into the database
            String insertQuery = "INSERT INTO bookings (userId, eventId, eventDate, price) VALUES (?, ?, ?, ?)";

            try (Connection connection = DatabaseConnect.getConnection(); // Replace DatabaseConnect with your DB
                                                                          // connection class
                    PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {

                // Set the parameters for the query
                System.out.println("userId" + currentUser.getUserId());
                pstmt.setInt(1, Integer.parseInt(currentUser.getUserId())); // Assuming currentUser has a method to get
                                                                            // user ID
                pstmt.setInt(2, Integer.parseInt(e_id)); // Event ID
                pstmt.setString(3, formattedDate); // Event Date
                pstmt.setDouble(4, e_price); // Price

                // Execute the insert query
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Booking Successful!\n" +
                            "Event ID: " + e_id +
                            "\nPrice: " + e_price +
                            "\nDate: " + formattedDate,
                            "Booking Details",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();

                    // Optionally navigate to another screen, for example:
                    // EventDetails eventDetailsInterface = new EventDetails();
                    // eventDetailsInterface.setVisible(true);
                    // this.dispose(); // Close the current frame
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to book the event. Please try again.");
                }
            } catch (SQLException err) {
                // Catch any database errors (e.g., duplicate entry, etc.)
                JOptionPane.showMessageDialog(this, "An error occurred while booking the event.\n" + err.getMessage());
                err.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a date before proceeding.");
        }
    }// GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EventBooking.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventBooking.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventBooking.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventBooking.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventBooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public static void bookNow(String id, double price) {
        e_id = id;
        e_price = price;
        if (currentlyBookingEvent != null) {
            currentlyBookingEvent.dispose(); // Close the existing frame
        }
        EventBooking bookingEvent = new EventBooking();
        bookingEvent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentlyBookingEvent = bookingEvent;
        bookingEvent.setVisible(true);
    }

}
