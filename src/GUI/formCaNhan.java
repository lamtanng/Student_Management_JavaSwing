/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import GUI.Controller.ProfileController;

/**
 *
 * @author PC
 */
public class formCaNhan extends javax.swing.JPanel {
    private ProfileController controller;
    /**
     * Creates new form formCaNhan
     */
    public formCaNhan(Integer userId) {
        initComponents();
        controller = new ProfileController(userId, txfID, txfName, txfPhone, txfAddress, txfEmail, txdate, cbRole, cbGender, btnUpload, btnEdit, btnCancel, btnSave, cbStatus, psfConfirmPw, psfNewPw, psfOldPw, lblAvt, btnChangePw, btnCancelPw, btnSavePw, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblAvt = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txfID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbRole = new javax.swing.JComboBox<>();
        txfName = new javax.swing.JTextField();
        cbGender = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txdate = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txfEmail = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txfPhone = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txfAddress = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnChangePw = new javax.swing.JButton();
        btnCancelPw = new javax.swing.JButton();
        btnSavePw = new javax.swing.JButton();
        psfOldPw = new javax.swing.JPasswordField();
        psfNewPw = new javax.swing.JPasswordField();
        psfConfirmPw = new javax.swing.JPasswordField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("PROFILE");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 215, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAvt.setText("Avatar");
        jPanel1.add(lblAvt, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 79, 93));

        btnUpload.setText("Upload");
        jPanel1.add(btnUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, -1, -1));

        jLabel3.setText("ID");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel4.setText("Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jLabel5.setText("Gender");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));
        jPanel1.add(txfID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 150, -1));

        jLabel6.setText("Role");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        cbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Employee" }));
        cbRole.setToolTipText("");
        jPanel1.add(cbRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 10, 100, -1));
        jPanel1.add(txfName, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 46, 340, -1));

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel1.add(cbGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel7.setText("DOB");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));
        jPanel1.add(txdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 87, 102, -1));

        jLabel8.setText("Email");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, -1));
        jPanel1.add(txfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 340, -1));

        btnEdit.setText("Edit");
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 80, -1));

        btnCancel.setText("Cancel");
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 80, -1));

        btnSave.setText("Save");
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 80, -1));

        jLabel9.setText("Phone");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));
        jPanel1.add(txfPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 150, -1));

        jLabel11.setText("Status");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enable", "Disable" }));
        jPanel1.add(cbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 130, -1));

        jLabel12.setText("Address");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));
        jPanel1.add(txfAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 200, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 28, 630, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("CHANGE PASSWORD");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 215, 250));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Old Password");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 48, -1, -1));

        jLabel13.setText(" New Password");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, 20));

        jLabel14.setText("Confirm Password");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, 20));

        btnChangePw.setText("Change");
        jPanel2.add(btnChangePw, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 110, -1));

        btnCancelPw.setText("Cancel");
        jPanel2.add(btnCancelPw, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 110, -1));

        btnSavePw.setText("Save");
        jPanel2.add(btnSavePw, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 110, -1));
        jPanel2.add(psfOldPw, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 250, -1));
        jPanel2.add(psfNewPw, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 250, -1));
        jPanel2.add(psfConfirmPw, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 250, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 620, 220));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancelPw;
    private javax.swing.JButton btnChangePw;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSavePw;
    private javax.swing.JButton btnUpload;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JComboBox<String> cbRole;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAvt;
    private javax.swing.JPasswordField psfConfirmPw;
    private javax.swing.JPasswordField psfNewPw;
    private javax.swing.JPasswordField psfOldPw;
    private com.toedter.calendar.JDateChooser txdate;
    private javax.swing.JTextField txfAddress;
    private javax.swing.JTextField txfEmail;
    private javax.swing.JTextField txfID;
    private javax.swing.JTextField txfName;
    private javax.swing.JTextField txfPhone;
    // End of variables declaration//GEN-END:variables
}
