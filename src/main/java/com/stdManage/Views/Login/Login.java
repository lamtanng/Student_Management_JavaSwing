/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.stdManage.Views.Login;

import com.stdManage.Dao.AccountDao;
import com.stdManage.Main.Main;
import com.stdManage.Models.Account;
import com.stdManage.Utils.U_Common;
import com.stdManage.Utils.U_Styles;
import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Login extends javax.swing.JFrame {

    public static String CURRENT_USER = null;

    AccountDao accDao = new AccountDao();

    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        initOveride();

    }

    private void login(){
        try {
            String userName = txt_UserName.getText();
            String password = txt_Password.getText();

            //check null input
            if (!isEmptyInput()) {
                Account acc = accDao.findOne(userName, password);

                //check acc empty
                if (acc.getUsername().trim() != null) {
                    //save CurrentUser global
                    CURRENT_USER = userName;
                    String userRole = acc.getRole().trim();
                    //check role
                    switch (userRole) {
                        case U_Common.ROLE.ADMIN:
                            openDashBoard(userRole);
                            break;
                        case U_Common.ROLE.TEACHER:
                            openDashBoard(userRole);
                            break;
                        case U_Common.ROLE.STUDENT:
                            openDashBoard(userRole);
                            break;
                        default:
                            throw new AssertionError();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Username or Password incorrect !", "Failed", JOptionPane.ERROR);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Username or Password incorrect !\n" + e.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initOveride() {
        btn_login.setBackground(U_Styles.COLOR_PRIMARY);
        btn_login.setFont(U_Styles.TEXT_BOLD_MEDIUM);
        btn_login.setForeground(U_Styles.COLOR_WHITE);
        btn_login.setBorder(null);
        jPanel1.setAlignmentY(CENTER_ALIGNMENT);

        btn_ShutDown.setIcon(U_Common.createImageIcon("power-on.png"));
    }

    private boolean isEmptyInput() {
        String userName = txt_UserName.getText().trim();
        String password = txt_Password.getText().trim();
        if (userName.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username or Password null !", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_Password = new com.stdManage.Views.Components.PasswordField();
        txt_UserName = new com.stdManage.Views.Components.TextField();
        btn_ShutDown = new com.stdManage.Views.Swing.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setName("frame_Login"); // NOI18N
        setUndecorated(true);
        setType(java.awt.Window.Type.UTILITY);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel1.setAlignmentY(Component.CENTER_ALIGNMENT);

        btn_login.setText("LOG IN");
        btn_login.setBorder(null);
        btn_login.setName(""); // NOI18N
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WELCOME");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txt_Password.setLabelText("Password");

        txt_UserName.setLabelText("Username");
        txt_UserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_UserNameKeyPressed(evt);
            }
        });

        btn_ShutDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ShutDownMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ShutDown, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txt_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btn_ShutDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void openDashBoard(String role) {
        Main mainScreen = new Main();
        mainScreen.setVisible(true);
        JOptionPane.showMessageDialog(null, "Hello "+ role + ": " + CURRENT_USER, "Welcom", JOptionPane.INFORMATION_MESSAGE);
        this.hide();
    }

    private void btn_ShutDownMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ShutDownMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_ShutDownMouseClicked

    private void txt_UserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_UserNameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
            System.err.println("ENTER");
                login();
            }
    }//GEN-LAST:event_txt_UserNameKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setLocationRelativeTo(null);

                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stdManage.Views.Swing.Button btn_ShutDown;
    private javax.swing.JButton btn_login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private com.stdManage.Views.Components.PasswordField txt_Password;
    private com.stdManage.Views.Components.TextField txt_UserName;
    // End of variables declaration//GEN-END:variables
}
