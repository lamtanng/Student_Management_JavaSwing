/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import GUI.Controller.KhachHangController;
import Ultils.Constant;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author PC
 */
public class formKhachHang extends javax.swing.JPanel {

    
    
    private KhachHangController controller;
    public formKhachHang() {
        initComponents();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {}
	));
	scrollPane.setViewportView(table);
        controller = new KhachHangController(txfName, txfPhone, txfAddress, txfFind, txdate, txfID, cbGender, btnAdd, btnEdit, btnCancel, btnSave, cbFilter, btnFind, table, btnExport);
    }
    
    public KhachHangController getController() {
	return controller;
    }

    public void setController(KhachHangController controller) {
	this.controller = controller;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txfID = new javax.swing.JTextField();
        txfName = new javax.swing.JTextField();
        cbGender = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txdate = new com.toedter.calendar.JDateChooser();
        btnAdd = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txfPhone = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txfAddress = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbFilter = new javax.swing.JComboBox<>();
        txfFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnExport = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(658, 518));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("CUSTOMER");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 6, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 215, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("ID");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel4.setText("Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 20));

        jLabel5.setText("Gender");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));
        jPanel1.add(txfID, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 350, -1));
        jPanel1.add(txfName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 350, -1));

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel1.add(cbGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, -1));

        jLabel7.setText("DOB");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, -1));
        jPanel1.add(txdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 87, 130, -1));

        btnAdd.setText("Add");
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 80, -1));

        btnImport.setText("Import");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });
        jPanel1.add(btnImport, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 80, -1));

        btnEdit.setText("Edit");
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 80, -1));

        btnCancel.setText("Cancel");
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 80, -1));

        btnSave.setText("Save");
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 80, -1));

        jLabel9.setText("Phone");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));
        jPanel1.add(txfPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 340, -1));

        jLabel12.setText("Address");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));
        jPanel1.add(txfAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 340, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 28, 630, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("CUSTOMER LIST");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 289, -1, -1));

        cbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name" }));
        add(cbFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, -1, -1));
        add(txfFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 200, -1));

        btnFind.setText("Find");
        add(btnFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 80, -1));

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

        btnExport.setText("Export");
        add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed

        formImportData fImp = new formImportData(Constant.CUSTOMER_COLUMNS, Constant.CUSTOMER_IMPORT );
        fImp.setVisible(true);
    }//GEN-LAST:event_btnImportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbFilter;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private com.toedter.calendar.JDateChooser txdate;
    private javax.swing.JTextField txfAddress;
    private javax.swing.JTextField txfFind;
    private javax.swing.JTextField txfID;
    private javax.swing.JTextField txfName;
    private javax.swing.JTextField txfPhone;
    // End of variables declaration//GEN-END:variables
}
