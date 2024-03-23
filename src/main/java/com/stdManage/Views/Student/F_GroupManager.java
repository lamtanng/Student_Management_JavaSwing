/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stdManage.Views.Student;

import com.stdManage.Dao.AccountDao;
import com.stdManage.Dao.ClassDao;
import com.stdManage.Dao.ClassGroupDao;
import com.stdManage.Dao.CourseDao;
import com.stdManage.Dao.GradeDetailDao;
import com.stdManage.Main.Main;
import com.stdManage.Models.Account;
import com.stdManage.Models.ClassModels;
import com.stdManage.Models.ClassGroup;
import com.stdManage.Models.Course;
import com.stdManage.Models.GradeDetail;
import com.stdManage.Utils.U_ColumnTitles;
import com.stdManage.Utils.U_Styles;
import com.stdManage.Views.Components.Dialog.Message;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.stdManage.Views.Swing.JTable.ITableActionEvent;
import com.stdManage.Views.Swing.Table.Table;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ADMIN
 */
public class F_GroupManager extends javax.swing.JPanel {

    AccountDao accDao = new AccountDao();
    CourseDao courseDao = new CourseDao();
    ClassDao classDao = new ClassDao();
    ClassGroupDao groupDao = new ClassGroupDao();
    GradeDetailDao gradeDao = new GradeDetailDao();
    
    String currentCourse = "-1";
    String currentClass = "-1";
    String currentGroup = "-1";
    /**
     * Creates new form F_GroupManager
     */
    public F_GroupManager() {
        initComponents();
        Object[][] dataTable = accDao.findAll();
        tbl_GroupDetails1.initTable(U_ColumnTitles.ACCOUNT, dataTable);
        handleTableAction();
//        fillTableData();
        
        //init cbb
        initComboboxs();

    }

    private void loadStudentTable(String groupId){
        tbl_GroupDetails1.deleteRows();
        fillTableData();
        tbl_GroupDetails1.hideColumnAt(new int[]{6,7});
    }
    
    private void initComboboxs() {
        initCourseCbb();
    }
    
    private void initGroupCbb(String class_id){
        cbb_groupClass.init(groupDao.findAllbyCourse(class_id).toArray(), -1, "Group");
        cbb_groupClass.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (value instanceof ClassGroup) {
                    ClassGroup item = (ClassGroup) value;
                    setText(item.getId());
                }
                setBorder(new EmptyBorder(5, 5, 5, 5));
                if (isSelected) {
                    //set hover color
                    this.setBackground(U_Styles.COLOR_GRAY1);
                }
                return this;
            }
        });
    }
    
    private void initClassCbb(String course_id){
        cbb_class.init(classDao.findAllbyCourse(course_id).toArray(), -1, "Class");
        cbb_class.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (value instanceof ClassModels) {
                    ClassModels item = (ClassModels) value;
                    setText(item.getName());
                }
                setBorder(new EmptyBorder(5, 5, 5, 5));
                if (isSelected) {
                    //set hover color
                    this.setBackground(U_Styles.COLOR_GRAY1);
                }
                return this;
            }
        });
    }

    private void initCourseCbb(){
        cbb_course.init(courseDao.findAll().toArray(), -1, "Course");
        cbb_course.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (value instanceof Course) {
                    Course item = (Course) value;
                    setText(item.getName());
                }
                setBorder(new EmptyBorder(0, 10, 0, 0));
                if (isSelected) {
                    //set hover color
                    this.setBackground(U_Styles.COLOR_GRAY1);
                }
                return this;
            }
        });

//        String[] data = {"a", "b", "c", "d", "e", "b", "c", "d", "e"};
//        cbb_course.init(data, -1, "Course");
    }
    
    private void handleTableAction() {
        ITableActionEvent event = new ITableActionEvent() {
            @Override
            public void onEdit(int row, int col) {
                System.out.println("Edit row : " + row);
            }

            @Override
            public void onDelete(int row, int col) {
                if (tbl_GroupDetails1.isEditing()) {
                    tbl_GroupDetails1.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tbl_GroupDetails1.getModel();
                model.removeRow(row);
            }

            @Override
            public void onView(int row, int col) {
                System.out.println("View row : " + row + "+ col : " + col);
            }
        };
        tbl_GroupDetails1.createActionColumn(event);
    }
    

    private void fillTableData() {
//        Object[][] list = accDao.findAll();
//        list.forEach((a) -> {
//            tbl_GroupDetails1.addRow(a.toModelTable());
//        });
    }

    private void paintTable() {
        tbl_GroupDetails1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl_GroupDetails1.rowAtPoint(e.getPoint());
                int col = tbl_GroupDetails1.columnAtPoint(e.getPoint());

                System.out.println(".mouseClicked>> " + row + col + tbl_GroupDetails1.getValueAt(row, col));
            }
        });
    }

    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_GroupDetails1 = new com.stdManage.Views.Swing.Table.Table();
        jPanel1 = new javax.swing.JPanel();
        cbb_course = new com.stdManage.Views.Components.Combobox();
        textField1 = new com.stdManage.Views.Components.TextField();
        cbb_class = new com.stdManage.Views.Components.Combobox();
        cbb_groupClass = new com.stdManage.Views.Components.Combobox();

        setPreferredSize(new java.awt.Dimension(1058, 741));

        tbl_GroupDetails1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_GroupDetails1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cbb_course.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_courseItemStateChanged(evt);
            }
        });

        textField1.setLabelText("Course");

        cbb_class.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_classItemStateChanged(evt);
            }
        });

        cbb_groupClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_groupClassItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(cbb_course, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(cbb_class, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(cbb_groupClass, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(297, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_groupClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_courseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_courseItemStateChanged
        // TODO add your handling code here:
//        int idx = cbb_course.getSelectedIndex();
//        if (idx >= 0) {
//            Course value = (Course) cbb_course.getSelectedItem();
//            String currentCourse = value.getId();
//            //System.out.println("Course id : " + currentCourse);
//            initClassCbb(currentCourse);
//        }
    }//GEN-LAST:event_cbb_courseItemStateChanged

    private void cbb_classItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_classItemStateChanged
        int idx = cbb_class.getSelectedIndex();
        if (idx >= 0) {
            ClassModels value = (ClassModels) cbb_class.getSelectedItem();
            String currentClass = value.getId();
            //System.out.println("Course id : " + currentCourse);
            initGroupCbb(currentClass);
        }
    }//GEN-LAST:event_cbb_classItemStateChanged

    private void cbb_groupClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_groupClassItemStateChanged
//        int idx = cbb_groupClass.getSelectedIndex();
//        if (idx >= 0) {
//            ClassGroup value = (ClassGroup) cbb_groupClass.getSelectedItem();
//            currentGroup = value.getId();
//            loadStudentTable(currentGroup);
//        }
    }//GEN-LAST:event_cbb_groupClassItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stdManage.Views.Components.Combobox cbb_class;
    private com.stdManage.Views.Components.Combobox cbb_course;
    private com.stdManage.Views.Components.Combobox cbb_groupClass;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.stdManage.Views.Swing.Table.Table tbl_GroupDetails1;
    private com.stdManage.Views.Components.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
