/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package GUI;

import GUI.Controller.NhanVienController;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class formQLNhanVien extends javax.swing.JPanel {

    /** Creates new form ControllerNhanVien
     *  */
    private NhanVienController controller;
    public formQLNhanVien() {
        initComponents();
        table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
        scrollPane.setViewportView(table);
        lblAvt.setSize(80, 90);
        controller = new NhanVienController(txfID, txfName, txfPhone, txfAddress, txfEmail, txfPasswd, txfFind, txdate, table, cbRole, cbGender, lblAvt, btnUpload, btnAdd, btnEdit, btnCancel, btnSave, cbFilter, btnFind, cbStatus);
    }
    
    public NhanVienController getController() {
		return controller;
    }

    public void setController(NhanVienController controller) {
            this.controller = controller;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txfPhone = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txfPasswd = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txfAddress = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbFilter = new javax.swing.JComboBox<>();
        txfFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("EMPLOYEE");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 6, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 215, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAvt.setBackground(new java.awt.Color(137, 164, 249));
        lblAvt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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

        btnAdd.setText("Add");
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 80, -1));

        btnEdit.setText("Edit");
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 80, -1));

        btnCancel.setText("Cancel");
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 80, -1));

        btnSave.setText("Save");
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 80, -1));

        jLabel9.setText("Phone");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 181, -1, -1));
        jPanel1.add(txfPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 160, -1));

        jLabel10.setText("Password");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, -1, -1));
        jPanel1.add(txfPasswd, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 178, 200, -1));

        jLabel11.setText("Status");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enable", "Disable" }));
        jPanel1.add(cbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 218, 160, -1));

        jLabel12.setText("Address");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));
        jPanel1.add(txfAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 220, 200, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 28, 630, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("EMPLOYEE LIST");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 289, -1, -1));

        cbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name" }));
        add(cbFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, -1, -1));
        add(txfFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 289, 200, -1));

        btnFind.setText("Find");
        add(btnFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 289, 80, -1));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPane.setViewportView(table);

        add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 320, 630, 220));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpload;
    private javax.swing.JComboBox<String> cbFilter;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JComboBox<String> cbRole;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAvt;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private com.toedter.calendar.JDateChooser txdate;
    private javax.swing.JTextField txfAddress;
    private javax.swing.JTextField txfEmail;
    private javax.swing.JTextField txfFind;
    private javax.swing.JTextField txfID;
    private javax.swing.JTextField txfName;
    private javax.swing.JTextField txfPasswd;
    private javax.swing.JTextField txfPhone;
    // End of variables declaration//GEN-END:variables

}
