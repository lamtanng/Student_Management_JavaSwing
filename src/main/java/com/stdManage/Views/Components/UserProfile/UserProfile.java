/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stdManage.Views.Components.UserProfile;

import com.stdManage.Utils.U_Common;
import com.stdManage.Utils.U_Styles;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ADMIN
 */
public class UserProfile<Student> extends javax.swing.JPanel {

    public static final ImageIcon avtDefault = U_Common.createImageIcon("user.png", U_Common.PROFILE_FOLDER);

    ImageIcon oldAvatar = null;
    String path = null;

    public UserProfile() {
        initComponents();
        btn_Save.setBackground(U_Styles.COLOR_PRIMARY);
        btn_Save.setForeground(U_Styles.COLOR_WHITE);
    }

    public void showInfo(String id, String name, String birthday, String gender, String phone, ImageIcon img) {
        lbl_ID.setText(id);
        lbl_Name.setText(name);
        lbl_BirthDate.setText(birthday);
        lbl_Gender.setText(gender);
        lbl_Phone.setText(phone);
        lbl_avt.setIcon(img);

        oldAvatar = img;//save old avatar
    }

    public void setAvtDefault() {
        lbl_avt.setIcon(avtDefault);
    }

    public void handelButton(I_UserProfile event) {
        btn_ResetAvt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = null;//no update image
                lbl_avt.setIcon(oldAvatar);//reset old avt
            }
        });

        btn_UploadAvt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser imgChooser = new JFileChooser();
                imgChooser.setAcceptAllFileFilterUsed(true);

                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "jpeg");
                imgChooser.setFileFilter(filter);
                imgChooser.addChoosableFileFilter(filter);
                imgChooser.showOpenDialog(null);

                try {
                    File file = imgChooser.getSelectedFile();

                    //check image file
                    if (filter.accept(file)) {
                        //get path img
                        path = file.getAbsolutePath();

                        BufferedImage buffImg = ImageIO.read(new File(path));
                        Image img = buffImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        ImageIcon imgIcon = new ImageIcon(img);
                        lbl_avt.setIcon(imgIcon);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please, choose image !", "Error upload image", JOptionPane.DEFAULT_OPTION);
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error upload image", JOptionPane.DEFAULT_OPTION);
                }
            }
        });

        btn_Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAvtDefault();
                path = "";//update empty image 
            }
        });

        btn_Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println("Path: " + path);
                event.save(path);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Info = new javax.swing.JPanel();
        lbl_IDTitle = new javax.swing.JLabel();
        lbl_NameTitle = new javax.swing.JLabel();
        lbl_BirthDateTitle = new javax.swing.JLabel();
        lbl_PhoneTitle = new javax.swing.JLabel();
        lbl_GenderTitle = new javax.swing.JLabel();
        lbl_ID = new javax.swing.JLabel();
        lbl_Name = new javax.swing.JLabel();
        lbl_BirthDate = new javax.swing.JLabel();
        lbl_Phone = new javax.swing.JLabel();
        lbl_Gender = new javax.swing.JLabel();
        btn_UploadAvt = new com.stdManage.Views.Swing.Button();
        lbl_avt = new javax.swing.JLabel();
        btn_ResetAvt = new javax.swing.JButton();
        btn_Clear = new javax.swing.JButton();
        btn_Save = new com.stdManage.Views.Swing.Button();

        pnl_Info.setBackground(new java.awt.Color(255, 255, 255));

        lbl_IDTitle.setText("ID");

        lbl_NameTitle.setText("Name");

        lbl_BirthDateTitle.setText("Birth date");

        lbl_PhoneTitle.setText("Phone");

        lbl_GenderTitle.setText("Gender");

        lbl_ID.setText("null");

        lbl_Name.setText("null");

        lbl_BirthDate.setText("null");

        lbl_Phone.setText("null");

        lbl_Gender.setText("null");

        btn_UploadAvt.setText("Upload");

        btn_ResetAvt.setText("Reset");
        btn_ResetAvt.setBorder(null);
        btn_ResetAvt.setBorderPainted(false);
        btn_ResetAvt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ResetAvtActionPerformed(evt);
            }
        });

        btn_Clear.setText("Clear");
        btn_Clear.setBorder(null);
        btn_Clear.setBorderPainted(false);
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        btn_Save.setText("Save");

        javax.swing.GroupLayout pnl_InfoLayout = new javax.swing.GroupLayout(pnl_Info);
        pnl_Info.setLayout(pnl_InfoLayout);
        pnl_InfoLayout.setHorizontalGroup(
            pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_InfoLayout.createSequentialGroup()
                .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_InfoLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(lbl_avt, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_InfoLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_GenderTitle)
                            .addComponent(lbl_PhoneTitle)
                            .addComponent(lbl_IDTitle)
                            .addComponent(lbl_NameTitle)
                            .addComponent(lbl_BirthDateTitle)
                            .addComponent(btn_UploadAvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_InfoLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_ID)
                                    .addComponent(lbl_Name)
                                    .addComponent(lbl_BirthDate)
                                    .addComponent(lbl_Phone)
                                    .addComponent(lbl_Gender)))
                            .addGroup(pnl_InfoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ResetAvt, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_InfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pnl_InfoLayout.setVerticalGroup(
            pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_InfoLayout.createSequentialGroup()
                .addComponent(lbl_avt, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_UploadAvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ResetAvt, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(btn_Clear, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_IDTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_NameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_BirthDateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_BirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_PhoneTitle)
                    .addComponent(lbl_Phone))
                .addGap(18, 18, 18)
                .addGroup(pnl_InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_GenderTitle)
                    .addComponent(lbl_Gender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl_Info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ResetAvtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ResetAvtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ResetAvtActionPerformed

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_ResetAvt;
    private com.stdManage.Views.Swing.Button btn_Save;
    private com.stdManage.Views.Swing.Button btn_UploadAvt;
    private javax.swing.JLabel lbl_BirthDate;
    private javax.swing.JLabel lbl_BirthDateTitle;
    private javax.swing.JLabel lbl_Gender;
    private javax.swing.JLabel lbl_GenderTitle;
    private javax.swing.JLabel lbl_ID;
    private javax.swing.JLabel lbl_IDTitle;
    private javax.swing.JLabel lbl_Name;
    private javax.swing.JLabel lbl_NameTitle;
    private javax.swing.JLabel lbl_Phone;
    private javax.swing.JLabel lbl_PhoneTitle;
    private javax.swing.JLabel lbl_avt;
    private javax.swing.JPanel pnl_Info;
    // End of variables declaration//GEN-END:variables
}
