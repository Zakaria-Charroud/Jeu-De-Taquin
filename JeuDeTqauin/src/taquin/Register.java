package taquin;

import java.sql.*;
import java.awt.Color;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Register extends javax.swing.JFrame {

    public Register() {
        initComponents();
        Border borderPanel = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.red);
        panelRegister.setBorder(borderPanel);
        Border borderLabel2 = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red);
        lblClose.setBorder(borderLabel2);
        lblMinimize.setBorder(borderLabel2);

        Border borderTextFile = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red);
        txtName.setBorder(borderTextFile);
        txtUsername.setBorder(borderTextFile);
        txtPassword.setBorder(borderTextFile);
        txtConfirmPassword.setBorder(borderTextFile);
        txtMail.setBorder(borderTextFile);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        panelRegister = new javax.swing.JPanel();
        lblClose = new javax.swing.JLabel();
        lblMinimize = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnImage = new javax.swing.JButton();
        lblImagePath = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtConfirmPassword = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setPreferredSize(new java.awt.Dimension(443, 550));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRegister.setBackground(new java.awt.Color(153, 153, 153));
        panelRegister.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblClose.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblClose.setForeground(new java.awt.Color(255, 0, 0));
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setText("X");
        lblClose.setMaximumSize(new java.awt.Dimension(12, 25));
        lblClose.setMinimumSize(new java.awt.Dimension(12, 25));
        lblClose.setPreferredSize(new java.awt.Dimension(17, 17));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCloseMouseExited(evt);
            }
        });
        panelRegister.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 10, -1, -1));

        lblMinimize.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblMinimize.setForeground(new java.awt.Color(255, 0, 0));
        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setText("-");
        lblMinimize.setMaximumSize(new java.awt.Dimension(15, 48));
        lblMinimize.setMinimumSize(new java.awt.Dimension(15, 48));
        lblMinimize.setPreferredSize(new java.awt.Dimension(17, 17));
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseExited(evt);
            }
        });
        panelRegister.add(lblMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 10, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Register");
        panelRegister.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 19, 241, 53));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\omaim\\Desktop\\JeuDeTqauin\\Ressources\\Images\\technology-wallpaper-preview.jpg")); // NOI18N
        jLabel1.setText("jLabel1");
        panelRegister.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 0, 570, 90));

        jPanel1.add(panelRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 88));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 255, 204));
        jLabel17.setText("Name :");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 106, 60, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 255, 204));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Username :");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 146, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 255, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Password :");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 100, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 255, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Confirm Password :");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 234, 150, 16));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 255, 204));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Mail :");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 272, 60, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 255, 204));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Image :");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 316, 60, -1));

        btnImage.setText("Select Image");
        btnImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageActionPerformed(evt);
            }
        });
        jPanel1.add(btnImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 316, -1, -1));

        lblImagePath.setForeground(new java.awt.Color(255, 0, 0));
        lblImagePath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagePath.setText("image: path");
        jPanel1.add(lblImagePath, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 345, 408, -1));

        lblLogin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(255, 0, 0));
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("back to login page");
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLoginMouseExited(evt);
            }
        });
        jPanel1.add(lblLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 446, 259, 35));

        btnRegister.setBackground(new java.awt.Color(0, 255, 204));
        btnRegister.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegister.setText("Register");
        btnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegisterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegisterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegisterMouseExited(evt);
            }
        });
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 379, 293, 49));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 106, 216, -1));
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 146, 216, -1));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 190, 216, -1));
        jPanel1.add(txtConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 232, 216, -1));

        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });
        jPanel1.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 272, 216, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\omaim\\Desktop\\JeuDeTqauin\\Ressources\\Images\\33vov45f7zqi6t75.jpg")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 420, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageActionPerformed

        JFileChooser chooser = new JFileChooser("./AccountImages");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpeg", "jpg", "png");
        chooser.addChoosableFileFilter(filter);
        chooser.setDialogTitle("Choose your image");
        int fileState = chooser.showDialog(null, "OK");
        if (fileState == JFileChooser.APPROVE_OPTION) {
            File selectedImage = chooser.getSelectedFile();
            String path = selectedImage.getAbsolutePath();
            lblImagePath.setText(path);
        }
    }//GEN-LAST:event_btnImageActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed

        if (checkEmptyFields()) {
            JOptionPane.showMessageDialog(null, "Please enter all the  infotmations", "Missing Informations", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!txtMail.getText().matches(".+@.+\\..+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Invalid Email", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String name = txtName.getText();
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();
            String mail = txtMail.getText();
            String image = lblImagePath.getText();
            if (!matchingPasswords(password, confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Please enter same Password", "Passwords!!!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!image.toLowerCase().endsWith(".jpg")&&!image.toLowerCase().endsWith(".png")&&!image.toLowerCase().endsWith(".jpeg")) {
                JOptionPane.showMessageDialog(null, "Veuillez choisir une image de type JPG, PNG ou JPEG", "Image invalide", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!matchingPasswords(password, confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Please enter same Password", "Passwords!!!", JOptionPane.ERROR_MESSAGE);
            } else {

                String query = "INSERT INTO users (name, username, password ,mail ,image ,score) VALUES (?, ?, ?, ?, ?, ?)";

                try {

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, name);
                    stmt.setString(2, username);
                    stmt.setString(3, password);
                    stmt.setString(4, mail);
                    stmt.setString(5, image);
                    stmt.setFloat(6, 0);
                    if (!checkUser()) {
                        if (stmt.executeUpdate() != 0) {
                            PreparedStatement ps;
                            ResultSet rs;
                            String queryy = "SELECT id FROM users WHERE username=? AND password =?";
                            try {
                                ps = ConnectionDB.getConnection().prepareStatement(queryy);
                                ps.setString(1, username);
                                ps.setString(2, password);
                                rs = ps.executeQuery();
                                if (rs.next()) {
                                    Forme forme = new Forme();
                                    forme.setVisible(true);
                                    forme.setLocationRelativeTo(null);
                                    forme.setInfo(rs.getInt(1));
                                    this.dispose();
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
                            this.dispose();
                            JOptionPane.showMessageDialog(null, "Your Account has been created", "Success", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Your Account has not been created", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnRegisterActionPerformed
    private boolean checkEmptyFields() {
        return (txtName.getText().equals("") || txtUsername.getText().equals("") || txtPassword.getText().equals("")
                || txtConfirmPassword.getText().equals("") || txtMail.getText().equals(""));
    }

    private boolean matchingPasswords(String p, String p2) {
        return (p.equals(p2));
    }

    private boolean checkUser() {
        ResultSet rs;
        boolean userExist = false;
        String query = "SELECT username FROM users WHERE username=?";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, txtUsername.getText());
            rs = stmt.executeQuery();
            if (rs.next()) {
                userExist = true;
                JOptionPane.showMessageDialog(null, "The username already exist, try another name ", "ERREUR!!!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No Database connection  ", "ERREUR!!!", JOptionPane.ERROR_MESSAGE);
        }
        return userExist;
    }
    private void btnRegisterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterMouseEntered
        btnRegister.setBackground(new Color(0, 200, 204));
    }//GEN-LAST:event_btnRegisterMouseEntered

    private void btnRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterMouseExited
        btnRegister.setBackground(new Color(0, 255, 204));
    }//GEN-LAST:event_btnRegisterMouseExited

    private void lblLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseClicked
        login Login = new login();
        Login.setVisible(true);
        Login.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblLoginMouseClicked

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        Border borderLabel = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        lblClose.setBorder(borderLabel);
        lblClose.setForeground(Color.white);
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        Border borderLabel = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red);
        lblClose.setBorder(borderLabel);
        lblClose.setForeground(Color.red);
    }//GEN-LAST:event_lblCloseMouseExited

    private void lblMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseEntered
        Border borderLabel = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        lblMinimize.setBorder(borderLabel);
        lblMinimize.setForeground(Color.white);
    }//GEN-LAST:event_lblMinimizeMouseEntered

    private void lblMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseExited
        Border borderLabel = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red);
        lblMinimize.setBorder(borderLabel);
        lblMinimize.setForeground(Color.red);
    }//GEN-LAST:event_lblMinimizeMouseExited

    private void lblLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseEntered
        lblLogin.setForeground(new Color(204, 0, 0));
    }//GEN-LAST:event_lblLoginMouseEntered

    private void lblLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseExited
        lblLogin.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_lblLoginMouseExited

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCloseMouseClicked

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void btnRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterMouseClicked
        login Login = new login();
        Login.setVisible(true);
        Login.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnRegisterMouseClicked

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImage;
    private javax.swing.JButton btnRegister;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblImagePath;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JPanel panelRegister;
    private javax.swing.JTextField txtConfirmPassword;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
