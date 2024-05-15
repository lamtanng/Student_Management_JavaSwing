/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import GUI.Controller.HoaDonController;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class formChiTietHoaDon extends javax.swing.JPanel {

    /**
     * Creates new form formChiTietHoaDon
     */
    private HoaDonController controller;
    public formChiTietHoaDon(Integer userId, boolean isAdmin) {
        initComponents();
        tableDetail.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {}
        ));
        scrollDetail.setViewportView(tableDetail);
        
        tableHistory.setModel(new DefaultTableModel(
            new Object[][] {},
		new String[] {}
	));
        scrollHistory.setViewportView(tableHistory);
        
        controller = new HoaDonController(userId, txfEmp, txfCus, txfPhone, txfIdBill, txdate, txfTotal, tableHistory, txfFind, tableDetail, txfIdEmp, cbFilter, btnFind);
    }
    public HoaDonController getController() {
        return controller;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txfIdBill = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txdate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txfTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txfCus = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txfPhone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txfIdEmp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txfEmp = new javax.swing.JTextField();
        scrollDetail = new javax.swing.JScrollPane();
        tableDetail = new javax.swing.JTable();
        scrollHistory = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();
        cbFilter = new javax.swing.JComboBox<>();
        txfFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText(" BILL HISTORY");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("BILL DETAIL");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 215, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("ID Bill");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel1.add(txfIdBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 140, -1));

        jLabel4.setText("Date");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));
        jPanel1.add(txdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 140, -1));

        jLabel5.setText("Total");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));
        jPanel1.add(txfTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 100, -1));

        jLabel6.setText("Customer");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        jPanel1.add(txfCus, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 220, -1));

        jLabel7.setText("Phone");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));
        jPanel1.add(txfPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 200, -1));

        jLabel8.setText("ID Employee");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 70, 20));

        txfIdEmp.setToolTipText("");
        jPanel1.add(txfIdEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 160, -1));

        jLabel9.setText("Employee");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));
        jPanel1.add(txfEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 280, -1));

        tableDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollDetail.setViewportView(tableDetail);

        jPanel1.add(scrollDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 600, 120));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 630, 280));

        tableHistory.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollHistory.setViewportView(tableHistory);

        add(scrollHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 630, 200));

        cbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Bill", "ID Employee" }));
        add(cbFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, -1, -1));
        add(txfFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, 170, -1));

        btnFind.setText("Find");
        add(btnFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private javax.swing.JComboBox<String> cbFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollDetail;
    private javax.swing.JScrollPane scrollHistory;
    private javax.swing.JTable tableDetail;
    private javax.swing.JTable tableHistory;
    private com.toedter.calendar.JDateChooser txdate;
    private javax.swing.JTextField txfCus;
    private javax.swing.JTextField txfEmp;
    private javax.swing.JTextField txfFind;
    private javax.swing.JTextField txfIdBill;
    private javax.swing.JTextField txfIdEmp;
    private javax.swing.JTextField txfPhone;
    private javax.swing.JTextField txfTotal;
    // End of variables declaration//GEN-END:variables
}
