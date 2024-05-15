/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author PC
 */
public class formQuanLy extends javax.swing.JFrame {
    
    /**
     * Creates new form formNhanVien
     */
//    private JPanel currentPanel;
    private formKhachHang fCus;
    private formQLNhanVien fEmpl;
    private formSanPham fProd;
    private formHoaDon fBill;
    private formChiTietHoaDon fDetail;
    private formThongKe fStatis;
    private formCaNhan fProfile;
    private formDangNhap fLogin;
    public formQuanLy(Integer userId) {
        initComponents();
        fCus = new formKhachHang();
        fEmpl = new formQLNhanVien();
        fProd = new formSanPham(userId);
        fBill = new formHoaDon(userId);
        fDetail = new formChiTietHoaDon(-1, true);
        fStatis = new formThongKe();
        fProfile = new formCaNhan(userId);
        fLogin = new formDangNhap();
                
        btnGroup.add(btnCus);
        btnGroup.add(btnBill);
        btnGroup.add(btnHistory);
        btnGroup.add(btnPro);
        btnGroup.add(btnProfile);
        btnGroup.add(btnLogOut);
        btnGroup.add(btnEmp);
        btnGroup.add(btnStatis);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
//        pnlButton.setPreferredSize(new Dimension(200, getHeight()));
        add(pnlButton, BorderLayout.WEST);
        switchPanel(fEmpl);
        fEmpl.getController().loadData();
    }
    
    private void switchPanel(JPanel panel){
       if(pnlCurrent != null) {
           remove(pnlCurrent);
       }
       pnlCurrent = panel;
//       pnlCurrent.setPreferredSize(new Dimension(getWidth() - 200, getHeight()));
       add(pnlCurrent, BorderLayout.CENTER);
       revalidate();
       repaint();
    } 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        pnlButton = new javax.swing.JPanel();
        btnEmp = new javax.swing.JToggleButton();
        btnCus = new javax.swing.JToggleButton();
        btnPro = new javax.swing.JToggleButton();
        btnBill = new javax.swing.JToggleButton();
        btnHistory = new javax.swing.JToggleButton();
        btnStatis = new javax.swing.JToggleButton();
        btnProfile = new javax.swing.JToggleButton();
        btnLogOut = new javax.swing.JToggleButton();
        pnlCurrent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEmp.setSelected(true);
        btnEmp.setText("Employee");
        btnEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpActionPerformed(evt);
            }
        });

        btnCus.setText("Customer");
        btnCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCusActionPerformed(evt);
            }
        });

        btnPro.setText("Product");
        btnPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProActionPerformed(evt);
            }
        });

        btnBill.setText("Bill");
        btnBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillActionPerformed(evt);
            }
        });

        btnHistory.setText("Bill History");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        btnStatis.setText("Statistic");
        btnStatis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisActionPerformed(evt);
            }
        });

        btnProfile.setText("Profile");
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        btnLogOut.setText("Log out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlButtonLayout.createSequentialGroup()
                        .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEmp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStatis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnEmp)
                .addGap(18, 18, 18)
                .addComponent(btnCus)
                .addGap(18, 18, 18)
                .addComponent(btnPro)
                .addGap(18, 18, 18)
                .addComponent(btnBill)
                .addGap(18, 18, 18)
                .addComponent(btnHistory)
                .addGap(18, 18, 18)
                .addComponent(btnStatis)
                .addGap(18, 18, 18)
                .addComponent(btnProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogOut)
                .addGap(19, 19, 19))
        );

        getContentPane().add(pnlButton, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout pnlCurrentLayout = new javax.swing.GroupLayout(pnlCurrent);
        pnlCurrent.setLayout(pnlCurrentLayout);
        pnlCurrentLayout.setHorizontalGroup(
            pnlCurrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );
        pnlCurrentLayout.setVerticalGroup(
            pnlCurrentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );

        getContentPane().add(pnlCurrent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBillActionPerformed
        // TODO add your handling code here:
        fBill.getController().loadData();
        switchPanel(fBill);
        
    }//GEN-LAST:event_btnBillActionPerformed

    private void btnEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpActionPerformed
        // TODO add your handling code here:
        fEmpl.getController().loadData();
        switchPanel(fEmpl);
    }//GEN-LAST:event_btnEmpActionPerformed

    private void btnCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCusActionPerformed
        // TODO add your handling code here:
        fCus.getController().loadData();
        switchPanel(fCus);
    }//GEN-LAST:event_btnCusActionPerformed

    private void btnProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProActionPerformed
        // TODO add your handling code here:
        fProd.getController().loadData();
        switchPanel(fProd);
    }//GEN-LAST:event_btnProActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        // TODO add your handling code here:
        fDetail.getController().loadData();
        switchPanel(fDetail);
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnStatisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisActionPerformed
        // TODO add your handling code here:
        fStatis.getController().clearData();
        switchPanel(fStatis);
    }//GEN-LAST:event_btnStatisActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        // TODO add your handling code here:
        switchPanel(fProfile);
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        dispose();
        fLogin.setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBill;
    private javax.swing.JToggleButton btnCus;
    private javax.swing.JToggleButton btnEmp;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JToggleButton btnHistory;
    private javax.swing.JToggleButton btnLogOut;
    private javax.swing.JToggleButton btnPro;
    private javax.swing.JToggleButton btnProfile;
    private javax.swing.JToggleButton btnStatis;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlCurrent;
    // End of variables declaration//GEN-END:variables
}
