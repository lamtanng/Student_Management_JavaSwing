/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stdManage.Views.General;

import com.stdManage.Dao.ClassDao;
import com.stdManage.Dao.CourseDao;
import com.stdManage.Models.ClassModel;
import com.stdManage.Utils.U_Common;
import com.stdManage.Utils.U_ColumnTitles;
import com.stdManage.Views.Components.Combobox;
import com.stdManage.Views.Components.InputPopup.I_PopupAction;
import com.stdManage.Views.Components.InputPopup.InputPopup;
import com.stdManage.Views.Components.TextField;
import com.stdManage.Views.Swing.JTable.ITableActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class F_Class extends javax.swing.JPanel {

    private static final int ADD_BUTTON = 1;
    private static final int UPDATE_BUTTON = 2;

    private static final U_ColumnTitles.CLASS TABLE = null;

    CourseDao courseDao = new CourseDao();
    ClassDao classDao = new ClassDao();
    ClassModel currentClass = new ClassModel();
    int currentRow = 0;

    Combobox<Object> cbb_Course = new Combobox<>();
    TextField txt_IdUpd = U_Common.createTextField(TABLE.ID, "");
    TextField txt_NameUpd = U_Common.createTextField(TABLE.TITLE, "");
    TextField txt_PeriodUpd = U_Common.createTextField(TABLE.PERIOD_TOTAL, "");
    TextField txt_Fee = U_Common.createTextField(TABLE.FEE, "");

    public F_Class() {
        initComponents();
        loadClassTable();
        initComp();
    }

    private void loadClassTable() {
        tbl_Class.initTable(U_ColumnTitles.CLASS.COLUMNS_TITLE, classDao.findAll());
        handleClassTable();
    }

    private void initPopupComponents(String id, String name, String period, String fee) {
        txt_IdUpd.setText(id);
        txt_NameUpd.setText(name);
        txt_PeriodUpd.setText(period);
        txt_Fee.setText(fee);
    }

    private void processPopup(final int TYPE_BUTTON) {
        currentClass = getSelectedClass();

        //create components
        I_PopupAction event = () -> {
            try {
                currentClass = getSelectedClass();
                currentClass.setName(txt_NameUpd.getText().trim());
                currentClass.setPeriod_total(Integer.parseInt(txt_PeriodUpd.getText().trim()));
                currentClass.setFee(Double.parseDouble(txt_Fee.getText().trim()));

                if (TYPE_BUTTON == UPDATE_BUTTON) {
                    classDao.update(currentClass);
                } else if (TYPE_BUTTON == ADD_BUTTON) {
                    currentClass.setId(txt_IdUpd.getText().trim());
                    currentClass.setCourse_id(cbb_Course.getObjectValueAt(0).toString());
                    classDao.add(currentClass);
                }

                loadClassTable();

                //focus student edited
                currentClass = getSelectedClass();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(tbl_Class, e.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
            }

        };
        InputPopup p = new InputPopup(event);
        p.setVisible(true);

        switch (TYPE_BUTTON) {
            case UPDATE_BUTTON:
                initPopupComponents(currentClass.getId(),
                        currentClass.getName(),
                        String.valueOf(currentClass.getPeriod_total()),
                        String.valueOf(currentClass.getFee()));
                //create event handler
                p.createComponents(txt_NameUpd, txt_PeriodUpd, txt_Fee);
                break;

            case ADD_BUTTON:
                initPopupComponents("", "", "", "");
                cbb_Course.init(courseDao.findAll(), 0, "Course", 1);

                //create event handler
                p.createComponents(cbb_Course, txt_IdUpd, txt_NameUpd, txt_PeriodUpd, txt_Fee);
                break;
            default:
                throw new AssertionError();
        }

    }

    private void handleClassTable() {
        ITableActionEvent event = new ITableActionEvent() {
            @Override
            public void onFirstButton(int row, int col) {
                currentRow = row;
                processPopup(UPDATE_BUTTON);
            }

            @Override
            public void onSecondButton(int row, int col) {
                //delete - confirm before delete
                currentRow = row;
                currentClass = getSelectedClass();
                int confirmDelele = JOptionPane.showConfirmDialog(tbl_Class, "Are you sure ?", "Delete", JOptionPane.YES_NO_OPTION);
                try {
                    if (confirmDelele == JOptionPane.YES_OPTION) {
                        classDao.delete(currentClass.getId());
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
        btn_Add.setIcon(U_Common.createImageIcon("add-button.png", U_Common.IMAGE_FOLDER));
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
        tbl_Class.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ClassMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Class);

        btn_Add.setText("add");
        btn_Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_AddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 366, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_ClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ClassMouseClicked
        if (tbl_Class.getRowCount() >= 1) {
            currentRow = tbl_Class.getSelectedRow();
            currentClass = getSelectedClass();
        }
    }//GEN-LAST:event_tbl_ClassMouseClicked

    private void btn_AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AddMouseClicked
        processPopup(ADD_BUTTON);
    }//GEN-LAST:event_btn_AddMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stdManage.Views.Swing.Button btn_Add;
    private javax.swing.JScrollPane jScrollPane2;
    private com.stdManage.Views.Swing.Table.Table tbl_Class;
    // End of variables declaration//GEN-END:variables

    private ClassModel getSelectedClass() {
        if (tbl_Class.getRowCount() >= 1 && currentRow >= 0) {
            currentClass.setId(tbl_Class.getValueAt(currentRow, tbl_Class.getColumnByName(U_ColumnTitles.CLASS.ID)).toString());
            currentClass.setCourse_id(tbl_Class.getValueAt(currentRow, tbl_Class.getColumnByName(U_ColumnTitles.CLASS.COURSE)).toString());
            currentClass.setName(tbl_Class.getValueAt(currentRow, tbl_Class.getColumnByName(U_ColumnTitles.CLASS.TITLE)).toString());
            currentClass.setPeriod_total(Integer.parseInt(tbl_Class.getValueAt(currentRow, tbl_Class.getColumnByName(U_ColumnTitles.CLASS.PERIOD_TOTAL)).toString()));
            currentClass.setFee(Double.parseDouble(tbl_Class.getValueAt(currentRow, tbl_Class.getColumnByName(U_ColumnTitles.CLASS.FEE)).toString()));

        }
        return currentClass;
    }

}
