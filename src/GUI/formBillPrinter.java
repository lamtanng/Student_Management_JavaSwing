/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.impl.HoaDonChiTietDaoImpl;
import GUI.Controller.LoginController;
import Model.HoaDonChiTietModel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;

/**
 *
 * @author ADMIN
 */
public class formBillPrinter extends javax.swing.JFrame {

    HoaDonChiTietDaoImpl hdDao = new HoaDonChiTietDaoImpl();
    int billId = 0;

    public formBillPrinter(int billId) {
        initComponents();
        this.billId = billId;
    }

    public void bill_print() {

        List<HoaDonChiTietModel> billList = hdDao.getByIdBill(billId);
        long priceTotal =0;
        try {
            bill.setText("                  Cửa hàng điện thoại \n");
            bill.setText(bill.getText() + "\tSố 1, Võ Văn Ngân, \n");
            bill.setText(bill.getText() + "\tThủ Đức, Hồ Chí Minh, \n");
            bill.setText(bill.getText() + "\t+84 123456789, \n");
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            //
            bill.setText(bill.getText() + "\tBILL DETAILS \n");
            bill.setText(bill.getText() + "\tCode: "+billId +" \n");
            bill.setText(bill.getText() + "\tDate: "+new Date(new java.util.Date().getTime()) +" \n");
            bill.setText(bill.getText() + "\tCashier: "+ LoginController.currentAcc.getName() +" \n\n");
            bill.setText(bill.getText() + " Order \tProduct \tPrice \tQuantity\n");
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            for(int i = 0; i < billList.size(); i++){
                int product = billList.get(i).getIdSP();
                double price = billList.get(i).getPrice();
                int quantity = billList.get(i).getQuantity();
                priceTotal += price;
                bill.setText(bill.getText() + (i+1)+" \t"+product+" \t"+price+" \t"+quantity+"\n");
            }
            
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            bill.setText(bill.getText() + "Total :\t\t\t "+priceTotal+"\n");
            
            bill.setText(bill.getText() + "====================================\n");
            bill.setText(bill.getText() + "                     Thanks For Your Business...!" + "\n");
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            bill.setText(bill.getText() + "                     Software by Techinbox" + "\n");

            bill.print();

        } catch (PrinterException ex) {

            Logger.getLogger(formBillPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_ButtonGroup2 = new javax.swing.JPanel();
        btn_Cancel2 = new javax.swing.JButton();
        btn_Print = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnl_ButtonGroup2.setBackground(new java.awt.Color(204, 255, 204));

        btn_Cancel2.setText("Cancel");
        btn_Cancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancel2ActionPerformed(evt);
            }
        });

        btn_Print.setText("Print");
        btn_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintActionPerformed(evt);
            }
        });
        btn_Print.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_PrintKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnl_ButtonGroup2Layout = new javax.swing.GroupLayout(pnl_ButtonGroup2);
        pnl_ButtonGroup2.setLayout(pnl_ButtonGroup2Layout);
        pnl_ButtonGroup2Layout.setHorizontalGroup(
            pnl_ButtonGroup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ButtonGroup2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(btn_Cancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(btn_Print, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        pnl_ButtonGroup2Layout.setVerticalGroup(
            pnl_ButtonGroup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ButtonGroup2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(pnl_ButtonGroup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Cancel2)
                    .addComponent(btn_Print))
                .addContainerGap())
        );

        bill.setColumns(20);
        bill.setRows(5);
        jScrollPane1.setViewportView(bill);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(pnl_ButtonGroup2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnl_ButtonGroup2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Cancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Cancel2ActionPerformed

    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
        //        Graphics g = new Graphics
        bill_print();
    }//GEN-LAST:event_btn_PrintActionPerformed

    private void btn_PrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_PrintKeyPressed

    }//GEN-LAST:event_btn_PrintKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea bill;
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JButton btn_Cancel1;
    private javax.swing.JButton btn_Cancel2;
    private javax.swing.JButton btn_Print;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl_ButtonGroup;
    private javax.swing.JPanel pnl_ButtonGroup1;
    private javax.swing.JPanel pnl_ButtonGroup2;
    // End of variables declaration//GEN-END:variables
}
