/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stdManage.Views.Student;

import com.stdManage.Dao.StudentDao;
import com.stdManage.Models.Student;
import com.stdManage.Utils.U_Common;
import com.stdManage.Utils.U_ColumnTitles;
import com.stdManage.Views.Components.Combobox;
import com.stdManage.Views.Components.InputPopup.I_PopupAction;
import com.stdManage.Views.Components.InputPopup.InputPopup;
import com.stdManage.Views.Components.TextField;
import com.stdManage.Views.Swing.JTable.ITableActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Properties;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.DateFormatter;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class F_Student extends javax.swing.JPanel {

    StudentDao stuDao = new StudentDao();

    Student currentStu = new Student();
    private static final U_ColumnTitles.STUDENT STUDENT_TABLE = new U_ColumnTitles.STUDENT();
    String path = null;
    String fileName = null;

    public F_Student() {
        initComponents();
        loadStudentTable();
    }

    private void loadStudentTable() {
        tbl_Student.initTable(U_ColumnTitles.STUDENT.COLUMNS_TITLE, stuDao.findAll());
        createActionColumn();
        currentStu = getSelectedStudent(0);
        showInfo();
    }

    public TextField createTextField(String lableText, String text) {
        TextField txt = new TextField();
        txt.setLabelText(lableText);
        txt.setText(text);
        return txt;
    }

    private void showPopup() {
        TextField txt_NameUpd = createTextField(U_ColumnTitles.STUDENT.NAME, currentStu.getName());
        TextField txt_AddressUpd = createTextField(U_ColumnTitles.STUDENT.ADDRESS, currentStu.getAddress());
        TextField txt_PhoneUpd = createTextField(U_ColumnTitles.STUDENT.PHONE, currentStu.getPhone());
        Combobox<String> cbb_Gender = new Combobox<String>();
        cbb_Gender.setModel(new DefaultComboBoxModel(new String[]{"Nam", "Nữ"}));
        cbb_Gender.setSelectedItem(currentStu.getGender());

        UtilDateModel model = new UtilDateModel();
        model.setValue(Date.valueOf(currentStu.getBirth_date()));
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new JFormattedTextField.AbstractFormatter() {
            private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    return dateFormatter.format((Date) value);
                }
                return "";
            }
        });

        I_PopupAction event = new I_PopupAction() {
            @Override
            public void handleFinish() {
                currentStu.setName(txt_NameUpd.getText().toString().trim());
                currentStu.setAddress(txt_AddressUpd.getText().toString().trim());
                currentStu.setPhone(txt_PhoneUpd.getText().toString().trim());
                currentStu.setGender(cbb_Gender.getSelectedItem().toString().trim());
//                currentStu.setBirth_date(LocalDate.parse(model.getValue().toString()));

                Date getDate = Date.valueOf(model.getValue().toString());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(getDate);
                System.out.println("~~~~~~>currenrStu" + getDate);
            }
        };

        InputPopup p = new InputPopup(event);
        p.createComponents(txt_NameUpd, txt_AddressUpd, txt_PhoneUpd, cbb_Gender, datePicker);
        p.setVisible(true);
    }

    private void createActionColumn() {

        ITableActionEvent e = new ITableActionEvent() {
            //update
            @Override
            public void onFirstButton(int row, int col) {

                currentStu = getSelectedStudent(row);
                showPopup();
//                path = profile_Stu.getPath();
//                try {
//                    currentStu = getSelectedStudent(row);
//                    Student oldStu = stuDao.findOne(currentStu.getId());
//
//                    if (path != null) {
//                        if (!oldStu.getImage().isBlank()) {
//                            U_Image.deleteImage(oldStu.getImage(), U_Common.PROFILE_FOLDER);
//                        }
//                        fileName = U_Image.uploadImage(path, U_Common.PROFILE_FOLDER);
//                        currentStu.setImage(fileName);
//
//                    } else if (path.isBlank()) { //clear avt
//                        currentStu.setImage("");
//
//                    } else {//reset old avt
//                        currentStu.setImage(oldStu.getImage());
//                    }
//
//                    stuDao.update(currentStu);
//                    JOptionPane.showMessageDialog(tbl_Student, "Success !", "Update", JOptionPane.PLAIN_MESSAGE);
//                    loadStudentTable();
//
//                    //focus student edited
//                    currentStu = getSelectedStudent(row);
//                    showInfo();
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(tbl_Student, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
            }

            //delete
            @Override
            public void onSecondButton(int row, int col) {
                int confirmDelele = JOptionPane.showConfirmDialog(tbl_Student, "Are you sure ?", "Delete", JOptionPane.YES_NO_OPTION);
                currentStu = getSelectedStudent(row);

                try {
                    if (confirmDelele == JOptionPane.YES_OPTION) {
                        stuDao.delete(currentStu.getId());
                        loadStudentTable();

                    } else if (confirmDelele == JOptionPane.NO_OPTION) {
                        System.err.println("Cancel delete");
                    }
                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(tbl_Student, e.getMessage(), "Delete error", JOptionPane.ERROR_MESSAGE);

                }

            }
        };

        tbl_Student.createActionColumn(e, U_Common.ActionTable.EDIT_DELETE);
    }

    private void init() {
        btn_Add.setText("");
        btn_Add.setIcon(U_Common.createImageIcon("add-button.png", U_Common.IMAGE_FOLDER));
    }

    private void showInfo() {

        ImageIcon avt = new ImageIcon(U_Common.RESOURCE.concat(U_Common.PROFILE_FOLDER).concat(currentStu.getImage()));
        profile_Stu.showInfo(currentStu.getId(), currentStu.getName(),
                currentStu.getBirth_date().toString(), currentStu.getGender(),
                currentStu.getPhone(), avt);
    }

    private Student getSelectedStudent(int row) {
        if (tbl_Student.getRowCount() >= 1 && row >= 0) {
            currentStu.setId(tbl_Student.getValueAt(row, tbl_Student.getColumnByName(STUDENT_TABLE.ID)).toString());
            currentStu.setName(tbl_Student.getValueAt(row, tbl_Student.getColumnByName(STUDENT_TABLE.NAME)).toString());
            currentStu.setBirth_date(LocalDate.parse((tbl_Student.getValueAt(row, tbl_Student.getColumnByName(STUDENT_TABLE.BIRTH_DAY)).toString())));
            currentStu.setAddress(tbl_Student.getValueAt(row, tbl_Student.getColumnByName(STUDENT_TABLE.ADDRESS)).toString());
            currentStu.setPhone(tbl_Student.getValueAt(row, tbl_Student.getColumnByName(STUDENT_TABLE.PHONE)).toString());
            currentStu.setGender(tbl_Student.getValueAt(row, tbl_Student.getColumnByName(STUDENT_TABLE.GENDER)).toString());
            currentStu.setUsername(tbl_Student.getValueAt(row, tbl_Student.getColumnByName(STUDENT_TABLE.USERNAME)).toString());

            //check img null
            String avt = tbl_Student.getValueAt(row, tbl_Student.getColumnByName(STUDENT_TABLE.IMAGE)).toString();
            if (avt.isEmpty()) {
                currentStu.setImage("user.png");
            } else {
                currentStu.setImage(avt.toString());
            }

            return currentStu;
        }
        return new Student("", "", LocalDate.now(), "", "", "", "", "", "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Student = new com.stdManage.Views.Swing.Table.Table();
        btn_Add = new com.stdManage.Views.Swing.Button();
        profile_Stu = new com.stdManage.Views.Components.UserProfile();

        setPreferredSize(new java.awt.Dimension(1058, 741));

        tbl_Student.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Student.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_StudentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Student);

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profile_Stu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profile_Stu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                .addContainerGap(319, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
//        create account before, then trigger auto create student
        int confCreateAcc = JOptionPane.showConfirmDialog(tbl_Student, "You must have an account before.", "Create account", JOptionPane.YES_NO_OPTION);
        if (confCreateAcc == JOptionPane.YES_OPTION) {
            //navigate to create acc
        }
    }//GEN-LAST:event_btn_AddActionPerformed

    private void tbl_StudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_StudentMouseClicked
        if (tbl_Student.getRowCount() >= 1) {
            int row = tbl_Student.getSelectedRow();
            currentStu = getSelectedStudent(row);
            showInfo();
        }
    }//GEN-LAST:event_tbl_StudentMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stdManage.Views.Swing.Button btn_Add;
    private javax.swing.JScrollPane jScrollPane2;
    private com.stdManage.Views.Components.UserProfile profile_Stu;
    private com.stdManage.Views.Swing.Table.Table tbl_Student;
    // End of variables declaration//GEN-END:variables
}
