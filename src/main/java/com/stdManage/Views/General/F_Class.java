/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stdManage.Views.General;

import com.stdManage.Dao.ClassDao;
import com.stdManage.Models.ClassModels;
import com.stdManage.Utils.U_Common;
import com.stdManage.Utils.U_ColumnTitles;
import com.stdManage.Views.Components.Combobox;
import com.stdManage.Views.Components.InputPopup;
import com.stdManage.Views.Swing.Button;
import com.stdManage.Views.Swing.JTable.ITableActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ADMIN
 */
public class F_Class extends javax.swing.JPanel {

    ClassDao classDao = new ClassDao();

    public F_Class() {
        initComponents();
        loadClassTable();
        initComp();
    }

    private void loadClassTable() {
        tbl_Class.initTable(U_ColumnTitles.CLASS, classDao.findAll());
        handleClassTable();
    }

    private void handleClassTable() {
        ITableActionEvent event = new ITableActionEvent() {
            @Override
            public void onFirstButton(int row, int col) {
                //edit - show popup
                ClassModels cl = new ClassModels();
                TableColumnModel colModel = tbl_Class.getColumnModel();
                try {
//                    {"ID", "Title", "Course", "Period total", "Fee"};
                    cl.setId(tbl_Class.getValueAt(row, colModel.getColumnIndex("ID")).toString());
                    cl.setCourse_id(tbl_Class.getValueAt(row, colModel.getColumnIndex("Course")).toString());
                    cl.setName(tbl_Class.getValueAt(row, colModel.getColumnIndex("Title")).toString());
                    cl.setPeriod_total(Integer.parseInt(tbl_Class.getValueAt(row, colModel.getColumnIndex("Period total")).toString()));
                    cl.setFee(Double.parseDouble(tbl_Class.getValueAt(row, colModel.getColumnIndex("Fee")).toString()));

                    classDao.update(cl);
                    JOptionPane.showConfirmDialog(tbl_Class, "Success !", "Update", JOptionPane.DEFAULT_OPTION);
                    
                    loadClassTable();
                    
                    //focus row updated
                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(tbl_Class, e.getMessage(), "Update error", JOptionPane.ERROR_MESSAGE);
                }
//             
            }

            @Override
            public void onSecondButton(int row, int col) {
                //delete - confirm before delete
                int confirmDelele = JOptionPane.showConfirmDialog(tbl_Class, "Are you sure ?", "Delete", JOptionPane.YES_NO_OPTION);
                try {
                    if (confirmDelele == JOptionPane.YES_OPTION) {
                        int idColIndex = tbl_Class.getColumnModel().getColumnIndex("ID");
                        String classID = tbl_Class.getValueAt(row, idColIndex).toString();
                        classDao.delete(classID);

                        loadClassTable();

                    } else if (confirmDelele == JOptionPane.NO_OPTION) {
                        System.err.println("Cancel delete");
                    }
                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(tbl_Class, e.getMessage(), "Delete error", JOptionPane.ERROR_MESSAGE);
                }

            }

        };
        tbl_Class.createActionColumn(event, U_Common.ActionTable.EDIT_DELETE);
    }

    private void initComp() {
        btn_Add.setText("");
        btn_Add.setIcon(U_Common.createImageIcon("add-button.png"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Class = new com.stdManage.Views.Swing.Table.Table();
        btn_Add = new com.stdManage.Views.Swing.Button();

        setOpaque(false);

        tbl_Class.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_Class);

        btn_Add.setText("add");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 366, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_btn_AddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stdManage.Views.Swing.Button btn_Add;
    private javax.swing.JScrollPane jScrollPane2;
    private com.stdManage.Views.Swing.Table.Table tbl_Class;
    // End of variables declaration//GEN-END:variables
}
