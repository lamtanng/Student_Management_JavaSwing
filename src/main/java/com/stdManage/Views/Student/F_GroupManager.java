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
import com.stdManage.Dao.StudentDao;
import com.stdManage.Models.ClassGroup;
import com.stdManage.Utils.U_Common;
import com.stdManage.Utils.U_ColumnTitles;
import com.stdManage.Utils.U_HelperDao;
import java.util.List;
import com.stdManage.Views.Swing.JTable.ITableActionEvent;

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
    StudentDao stuDao = new StudentDao();
    U_HelperDao helpDao = new U_HelperDao();

    String currentCourse = "";
    String currentClass = "";
    String currentGroup = " ";

    String[] stuOtherCols = {"ID", "Name"};

    public F_GroupManager() {
        initComponents();
        initCourseCbb();
    }

    private void loadStudentOtherTable() {
        tbl_StudentOther.initTable(stuOtherCols, stuDao.findAll_ExceptGroup(currentGroup));
        handleStudentOtherTable();
    }

    private void loadStudentOfGroupTable() {
        tbl_StudentGroup.initTable(U_ColumnTitles.GRADE_DETAIL.COLUMNS_TITLE, gradeDao.findAllbyGroup(currentGroup));
//        tbl_StudentGroup.hideColumnOf(new int[]{6, 7});
        handleStudentOfGroupTable();
    }

    private void loadGroupInfo() {
        ClassGroup gr = groupDao.findOne(currentGroup);
        txt_Teacher.setText(gr.getTeacher_id());
        txt_Room.setText(gr.getClassroom_id());
        txt_Shift.setText(String.valueOf(gr.getShift_id()));
        txt_StuMin.setText(String.valueOf(gr.getStudents_min()));
        txt_StuMax.setText(String.valueOf(gr.getStudents_max()));
        txt_StartDate.setText(gr.getStart_date().toString());
        txt_DayOfWeek.setText(gr.getDay_of_week());
        txt_PeriodChecked.setText(String.valueOf(gr.getPeriod_Checked()));
    }

    private void initGroupCbb() {
        List<Object[]> list = helpDao.covertToListObject1D(groupDao.findAllbyCourse(currentClass));
        cbb_groupClass.init(list.toArray(), 0, "Group", 0);
    }

    private void initClassCbb() {
        List<Object[]> list = helpDao.covertToListObject1D(classDao.findAllbyCourse(currentCourse));
        cbb_class.init(list.toArray(), 0, "Class", 1);
    }

    private void initCourseCbb() {
        List<Object[]> list = helpDao.covertToListObject1D(courseDao.findAll());
        cbb_course.init(list.toArray(), 0, "Course", 1);
    }

    private void handleStudentOfGroupTable() {
        ITableActionEvent event = new ITableActionEvent() {
            @Override
            public void onFirstButton(int row, int col) {
                //remove
            }

            @Override
            public void onSecondButton(int row, int col) {

            }

        };
        tbl_StudentGroup.createActionColumn(event, U_Common.ActionTable.DELETE);
    }

    private void handleStudentOtherTable() {
        ITableActionEvent event = new ITableActionEvent() {
            @Override
            public void onFirstButton(int row, int col) {
                //add

            }

            @Override
            public void onSecondButton(int row, int col) {

            }

        };
        tbl_StudentOther.createActionColumn(event, U_Common.ActionTable.ADD);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_StudentGroup = new com.stdManage.Views.Swing.Table.Table();
        jPanel1 = new javax.swing.JPanel();
        cbb_course = new com.stdManage.Views.Components.Combobox();
        txt_Teacher = new com.stdManage.Views.Components.TextField();
        cbb_class = new com.stdManage.Views.Components.Combobox();
        cbb_groupClass = new com.stdManage.Views.Components.Combobox();
        txt_Room = new com.stdManage.Views.Components.TextField();
        txt_Shift = new com.stdManage.Views.Components.TextField();
        txt_StuMax = new com.stdManage.Views.Components.TextField();
        txt_StuMin = new com.stdManage.Views.Components.TextField();
        txt_StartDate = new com.stdManage.Views.Components.TextField();
        txt_PeriodChecked = new com.stdManage.Views.Components.TextField();
        txt_DayOfWeek = new com.stdManage.Views.Components.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_StudentOther = new com.stdManage.Views.Swing.Table.Table();

        setPreferredSize(new java.awt.Dimension(1058, 741));

        tbl_StudentGroup.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_StudentGroup);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cbb_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_courseActionPerformed(evt);
            }
        });

        txt_Teacher.setLabelText("Teacher");

        cbb_class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_classActionPerformed(evt);
            }
        });

        cbb_groupClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_groupClassActionPerformed(evt);
            }
        });

        txt_Room.setLabelText("Room");

        txt_Shift.setLabelText("Shift");

        txt_StuMax.setLabelText("Max");

        txt_StuMin.setLabelText("Min");

        txt_StartDate.setLabelText("Start");

        txt_PeriodChecked.setLabelText("Checked");

        txt_DayOfWeek.setLabelText("Day Of Week");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_Teacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbb_course, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_DayOfWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbb_class, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_Room, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_PeriodChecked, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbb_groupClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_Shift, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_StuMin, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_StuMax, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(147, 147, 147))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_groupClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Teacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Shift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_StuMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_StuMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_PeriodChecked, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_DayOfWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        tbl_StudentOther.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_StudentOther);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_courseActionPerformed
        currentCourse = (String) cbb_course.getObjectValueAt(0);
        System.err.println("course id: " + currentCourse);

        initClassCbb();
    }//GEN-LAST:event_cbb_courseActionPerformed

    private void cbb_classActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_classActionPerformed
        currentClass = (String) cbb_class.getObjectValueAt(0);
        initGroupCbb();
    }//GEN-LAST:event_cbb_classActionPerformed

    private void cbb_groupClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_groupClassActionPerformed
        currentGroup = (String) cbb_groupClass.getObjectValueAt(0);
        loadStudentOfGroupTable();
        loadStudentOtherTable();
        loadGroupInfo();
    }//GEN-LAST:event_cbb_groupClassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stdManage.Views.Components.Combobox cbb_class;
    private com.stdManage.Views.Components.Combobox cbb_course;
    private com.stdManage.Views.Components.Combobox cbb_groupClass;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.stdManage.Views.Swing.Table.Table tbl_StudentGroup;
    private com.stdManage.Views.Swing.Table.Table tbl_StudentOther;
    private com.stdManage.Views.Components.TextField txt_DayOfWeek;
    private com.stdManage.Views.Components.TextField txt_PeriodChecked;
    private com.stdManage.Views.Components.TextField txt_Room;
    private com.stdManage.Views.Components.TextField txt_Shift;
    private com.stdManage.Views.Components.TextField txt_StartDate;
    private com.stdManage.Views.Components.TextField txt_StuMax;
    private com.stdManage.Views.Components.TextField txt_StuMin;
    private com.stdManage.Views.Components.TextField txt_Teacher;
    // End of variables declaration//GEN-END:variables
}
