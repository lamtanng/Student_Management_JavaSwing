/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stdManage.Views.General;

import com.stdManage.Dao.ClassDao;
import com.stdManage.Dao.ClassGroupDao;
import com.stdManage.Dao.ClassRoomDao;
import com.stdManage.Dao.CourseDao;
import com.stdManage.Dao.ShiftDao;
import com.stdManage.Dao.TeacherDao;
import com.stdManage.Models.ClassGroup;
import com.stdManage.Models.ClassModel;
import com.stdManage.Utils.U_Common;
import com.stdManage.Utils.U_ColumnTitles;
import com.stdManage.Utils.U_HelperDao;
import com.stdManage.Utils.U_Styles;
import com.stdManage.Views.Components.Combobox;
import com.stdManage.Views.Components.InputPopup.I_PopupAction;
import com.stdManage.Views.Components.InputPopup.InputPopup;
import com.stdManage.Views.Components.TextField;
import com.stdManage.Views.Swing.JTable.ITableActionEvent;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.ComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdatepicker.impl.JDatePickerImpl;

/**
 *
 * @author ADMIN
 */
public class F_ClassGroup extends javax.swing.JPanel {

    private static final Object[] DAY_TOTAL_OPTIONS = {2, 3};
    private int countNumOfWeek = 0;
    private String daysOfWeek = "";

    private static final U_ColumnTitles.CLASS_GROUP TABLE = null;

    TeacherDao teacherDao = new TeacherDao();
    ClassRoomDao roomDao = new ClassRoomDao();
    ShiftDao shiftDao = new ShiftDao();
    CourseDao courseDao = new CourseDao();
    ClassDao classDao = new ClassDao();
    ClassGroupDao groupDao = new ClassGroupDao();

    ClassGroup currentGroup = new ClassGroup();
    int currentRow = 0;

    Combobox<Object> cbb_Course = new Combobox<>();
    Combobox<Object> cbb_Class = new Combobox<>();
    Combobox<Object> cbb_Teacher = new Combobox<>();
    Combobox<Object> cbb_Room = new Combobox<>();
    Combobox<Object> cbb_Shift = new Combobox<>();
    Combobox<Object> cbb_DaysTotal = new Combobox<>();//2-3 buoi/tuan

    TextField txt_PeriodTotal = U_Common.createTextField(U_ColumnTitles.CLASS.PERIOD_TOTAL, "");
    TextField txt_IdUpd = U_Common.createTextField(TABLE.ID, "");
    TextField txt_StuMin = U_Common.createTextField(TABLE.STUDENT_MIN, "0");
    TextField txt_StuMax = U_Common.createTextField(TABLE.STUDENT_MAX, "0"); //==room seat
    JDatePickerImpl startDatePicker = null;

    JPanel pnlCheck = new JPanel();

    public F_ClassGroup() {
        initComponents();
        loadGroupTable();
        initComp();
    }

    private void loadGroupTable() {
        tbl_Group.initTable(U_ColumnTitles.CLASS_GROUP.COLUMNS_TITLE, groupDao.findAll());
        tbl_Group.hideColumnOf(new String[]{TABLE.IS_OPEN, TABLE.REGISTER_STATUS});
        handleClassTable();
    }

    private void initCourseCbb() {
        cbb_Course.addActionListener((ActionEvent e) -> {
            String currentCourse = (String) cbb_Course.getObjectValueAt(0);
            initClassCbb(currentCourse);
        });
        cbb_Course.init(courseDao.findAll(), 0, "Course", 1);
    }

    private void initClassCbb(String courseId) {
        //get period total
        cbb_Class.addActionListener((ActionEvent e) -> {
            txt_PeriodTotal.setText(cbb_Class.getObjectValueAt(3).toString());
        });
        cbb_Class.init(classDao.findAllbyCourse(courseId), 0, "Class", 1);
    }

    private void initDaysTotalCbb() {
        cbb_DaysTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //enable all checkbox
                for (Component component : pnlCheck.getComponents()) {
                    if (component instanceof JCheckBox checkBox) {
                        ((JCheckBox) component).setSelected(false);
                        component.setEnabled(true);
                    }
                }
                countNumOfWeek = 0;//reset count num of week
            }
        });
        cbb_DaysTotal.init(DAY_TOTAL_OPTIONS, 0, "Days of week", 0);
    }

    //
    private void initTeacherCbb(String shiftId, String daysOfWeek) {
        cbb_Teacher.setEnabled(false);
        cbb_Teacher.init(groupDao.findTeachersFreeTime(shiftId, daysOfWeek), 0, "Teacher", 1);
    }

    //
    private void initRoomCbb(String shiftId, String daysOfWeek) {
        //get student max = room's seats
        cbb_Room.setEnabled(false);
        cbb_Room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_StuMax.setText(cbb_Room.getObjectValueAt(1).toString());
            }
        });
        cbb_Room.init(groupDao.findRoomsFreeTime(shiftId, daysOfWeek), 0, "Room", 0);

    }

    private void initShiftCbb() {
        cbb_Shift.init(shiftDao.findAll(), 0, "Shift", 0);
    }

    private void initPopupComponents(Date startDate) {
        initCourseCbb();
        initShiftCbb();
        initDaysTotalCbb();
        startDatePicker = U_Common.createDatePicker(startDate);
        txt_PeriodTotal.setEditable(false);
        createMutilSelection();
    }

    private JCheckBox createCheckbox(String value, ItemListener l) {
        JCheckBox cb = new JCheckBox(value);
        cb.setBorderPaintedFlat(true);
        cb.setBackground(U_Styles.COLOR_WHITE);
        cb.addItemListener(l);
        return cb;
    }

    private void addCheckBoxToPanel(JCheckBox... cbs) {
        for (JCheckBox c : cbs) {
            pnlCheck.add(c);
        }
    }

    //set state for unchecked checkbox
    private void setStateUnCheckedCheckBoxes(JPanel panel, boolean enable) {
        //reset value
        daysOfWeek = "";
        for (Component component : panel.getComponents()) {
            if (component instanceof JCheckBox checkBox) {
                if (!checkBox.isSelected()) {
                    checkBox.setEnabled(enable);
                } else {
                    //format days_of_week to String
                    daysOfWeek += checkBox.getText().trim();
                }
            }
        }
    }

    private void createMutilSelection() {

        ItemListener listener = (ItemEvent e) -> {
            JCheckBox source = (JCheckBox) e.getSource();

            if (source.isSelected()) {
                countNumOfWeek += 1;
                System.out.println("Count: " + countNumOfWeek);
                if (countNumOfWeek == Integer.parseInt(cbb_DaysTotal.getSelectedItem().toString().trim())) {                    //disable all unchecked checkbox
                    setStateUnCheckedCheckBoxes(pnlCheck, false);
                    //init teacherCbb + roomCbb
                    String shiftSelected = cbb_Shift.getObjectValueAt(0).toString().trim();
                    String daysSelected = daysOfWeek;

                    initTeacherCbb(shiftSelected, daysSelected);
                    initRoomCbb(shiftSelected, daysOfWeek);
                    cbb_Room.setEnabled(true);
                    cbb_Teacher.setEnabled(true);
                }
            } else {
                countNumOfWeek -= 1;
                setStateUnCheckedCheckBoxes(pnlCheck, true);
                cbb_Room.setEnabled(false);
                cbb_Teacher.setEnabled(false);
            }

        };
        // JCheckBoxes
        JCheckBox check_2 = createCheckbox("2", listener);
        JCheckBox check_3 = createCheckbox("3", listener);
        JCheckBox check_4 = createCheckbox("4", listener);
        JCheckBox check_5 = createCheckbox("5", listener);
        JCheckBox check_6 = createCheckbox("6", listener);
        JCheckBox check_7 = createCheckbox("7", listener);
        // Add checkboxes to the panel
        pnlCheck = new JPanel();
        addCheckBoxToPanel(check_2, check_3, check_4, check_5, check_6, check_7);
        pnlCheck.setBackground(U_Styles.COLOR_WHITE);
    }

    private void processPopup(final int TYPE_BUTTON) {
        currentGroup = getSelectedGroup();

        InputPopup p = new InputPopup();
        //create components
        I_PopupAction event = () -> {
            try {
                currentGroup = getSelectedGroup();

//                currentGroup.setId(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.ID)).toString());
                currentGroup.setClass_id(cbb_Class.getObjectValueAt(0).toString());
                currentGroup.setTeacher_id(cbb_Teacher.getObjectValueAt(0).toString());
                currentGroup.setClassroom_id(cbb_Room.getObjectValueAt(0).toString());
                currentGroup.setShift_id(cbb_Shift.getObjectValueAt(0).toString());
                currentGroup.setStudents_min(Integer.parseInt(txt_StuMin.getText().trim()));
                currentGroup.setStudents_max(Integer.parseInt(txt_StuMax.getText().trim()));
                currentGroup.setStart_date(U_Common.getLocalDate(startDatePicker));
//                currentGroup.setRegister_status(Boolean.parseBoolean(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.REGISTER_STATUS)).toString()));
                currentGroup.setDay_of_week(daysOfWeek);
//                currentGroup.setIs_open(Boolean.parseBoolean(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.IS_OPEN)).toString()));

                if (TYPE_BUTTON == U_Common.UPDATE_BUTTON) {
                    groupDao.update(currentGroup);
                } else if (TYPE_BUTTON == U_Common.ADD_BUTTON) {
                    if (checkAddNew()) {
//                        groupDao.add(currentGroup);
                        List<String> testList = U_HelperDao.schedule(groupDao.findAll(), daysOfWeek, daysOfWeek);
                        p.dispose();
                    }
                }

                loadGroupTable();

                //focus student edited
                currentGroup = getSelectedGroup();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(tbl_Group, e.getMessage(), "Not a number", JOptionPane.PLAIN_MESSAGE);
            } catch (Throwable e) {
                JOptionPane.showMessageDialog(tbl_Group, e.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);

            }

        };

        switch (TYPE_BUTTON) {
            case U_Common.UPDATE_BUTTON:
                initPopupComponents(Date.valueOf(LocalDate.now()));
                startDatePicker = U_Common.createDatePicker(Date.valueOf(currentGroup.getStart_date()));
                setComboboxItem(cbb_DaysTotal, String.valueOf(currentGroup.getDay_of_week().length()), 0);
                createMutilSelection();
                getCheckbox(currentGroup.getDay_of_week(), pnlCheck);
                setComboboxItem(cbb_Shift, currentGroup.getShift_id(), 0);

                String shiftSelected = cbb_Shift.getObjectValueAt(0).toString().trim();
                String daysSelected = daysOfWeek;
                initRoomCbb(shiftSelected, daysOfWeek);
                initTeacherCbb(shiftSelected, daysOfWeek);
                
                setComboboxItem(cbb_Room, currentGroup.getClassroom_id(), 0);
                setComboboxItem(cbb_Teacher, currentGroup.getTeacher_id(), 0);
                txt_StuMin.setText(String.valueOf(currentGroup.getStudents_min()));
                txt_StuMax.setText(String.valueOf(currentGroup.getStudents_max()));

                p.createComponents(startDatePicker, cbb_DaysTotal, pnlCheck, cbb_Shift, cbb_Room, cbb_Teacher, txt_StuMin, txt_StuMax);
                break;
            case U_Common.ADD_BUTTON:
                initPopupComponents(Date.valueOf(LocalDate.now()));
                p.createComponents(cbb_Course, cbb_Class, txt_PeriodTotal, startDatePicker,
                        cbb_DaysTotal, pnlCheck, cbb_Shift, cbb_Room, cbb_Teacher, txt_StuMin, txt_StuMax);
                break;
            default:
                throw new AssertionError();
        }
        
        p.handleEvent(event);
        p.setVisible(true);

    }

    //set item for checkbox
    public void setComboboxItem(JComboBox cbb, String item, int colCheck) {
        ComboBoxModel<String> model = cbb.getModel();
        for (int i = 0; i < model.getSize(); i++) {

            Object modelItem = model.getElementAt(i);

            if (modelItem instanceof Object[]) {
                Object[] itemObj = (Object[]) modelItem;

                if (itemObj[colCheck].toString().equals(item)) {
                    cbb.setSelectedIndex(i);
                    return;
                }
            } else if (modelItem.toString().equals(item)) {
                cbb.setSelectedIndex(i);
                return;
            }

        }
    }

    //
    public void getCheckbox(String days, JPanel checkboxPanel) {
        for (int i = 0; i < checkboxPanel.getComponentCount(); i++) {
            JCheckBox checkbox = (JCheckBox) checkboxPanel.getComponent(i);
            System.out.println(days + "-Checkbox: " + checkbox.getText());
            String checkboxValue = checkbox.getText();
            
            if(countNumOfWeek == days.length()){
                return;
            }
            if (days.contains(checkboxValue)) {
                checkbox.setSelected(true);
            } else {
                checkbox.setSelected(false);
            }
        }
    }

    //check insert condition
    private boolean checkAddNew() {
        //check total day
        int dayTotalSelected = Integer.parseInt(cbb_DaysTotal.getSelectedItem().toString().trim());
        if (countNumOfWeek != dayTotalSelected) {
            JOptionPane.showMessageDialog(null, "Please select the correct number of days", "Error", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
        //check min/max student
        int stuMin = Integer.parseInt(txt_StuMin.getText().trim());
        int stuMax = Integer.parseInt(txt_StuMax.getText().trim());

        if (stuMin < 0 || stuMax < 0) {
            txt_StuMin.setText("0");
            txt_StuMax.setText(cbb_Room.getObjectValueAt(1).toString());
            JOptionPane.showMessageDialog(null, "Number of student is a positive integer !", "Error", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else {
            if (stuMin > stuMax) {
                JOptionPane.showMessageDialog(null, "Students min <= Student max !", "Error", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        }

        return true;
    }

    private void handleClassTable() {
        ITableActionEvent event = new ITableActionEvent() {
            @Override
            public void onFirstButton(int row, int col) {
                currentRow = row;
                processPopup(U_Common.UPDATE_BUTTON);
            }

            @Override
            public void onSecondButton(int row, int col) {
                //delete - confirm before delete
                currentRow = row;
                currentGroup = getSelectedGroup();
                int confirmDelele = JOptionPane.showConfirmDialog(tbl_Group, "Are you sure ?", "Delete", JOptionPane.YES_NO_OPTION);
                try {
                    if (confirmDelele == JOptionPane.YES_OPTION) {
                        groupDao.delete(currentGroup.getId());
                        loadGroupTable();

                    } else if (confirmDelele == JOptionPane.NO_OPTION) {
                        System.err.println("Cancel delete");
                    }
                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(tbl_Group, e.getMessage(), "Delete error", JOptionPane.ERROR_MESSAGE);
                }

            }

        };
        tbl_Group.createActionColumn(event, U_Common.ActionTable.UPDATE_DELETE);
    }

    private ClassGroup getSelectedGroup() {
        if (tbl_Group.getRowCount() >= 1 && currentRow >= 0) {
            currentGroup.setId(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.ID)).toString());
            currentGroup.setClass_id(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.CLASS)).toString());
            currentGroup.setTeacher_id(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.TEACHER)).toString());
            currentGroup.setClassroom_id(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.CLASSROOM)).toString());
            currentGroup.setShift_id(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.SHIFT)).toString());
            currentGroup.setStudents_min(Integer.parseInt(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.STUDENT_MIN)).toString()));
            currentGroup.setStudents_max(Integer.parseInt(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.STUDENT_MAX)).toString()));
            currentGroup.setStart_date(LocalDate.parse(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.START_DATE)).toString()));
            currentGroup.setPeriod_Checked(Integer.parseInt(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.PERIOD_CHECKED)).toString()));
            currentGroup.setRegister_status(Boolean.parseBoolean(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.REGISTER_STATUS)).toString()));
            currentGroup.setDay_of_week(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.DAY_OF_WEEK)).toString());
            currentGroup.setIs_open(Boolean.parseBoolean(tbl_Group.getValueAt(currentRow, tbl_Group.getColumnByName(TABLE.IS_OPEN)).toString()));

        }
        return currentGroup;
    }

    private void initComp() {
        btn_Add.setText("");
        btn_Add.setIcon(U_Common.createImageIcon("add-button.png", U_Common.IMAGE_FOLDER));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Group = new com.stdManage.Views.Swing.Table.Table();
        btn_Add = new com.stdManage.Views.Swing.Button();
        jCheckBox1 = new javax.swing.JCheckBox();

        setOpaque(false);

        tbl_Group.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Group.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_GroupMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Group);

        btn_Add.setText("add");
        btn_Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_AddMouseClicked(evt);
            }
        });

        jCheckBox1.setText("jCheckBox1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1125, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(jCheckBox1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(jCheckBox1)
                .addContainerGap(209, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_GroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_GroupMouseClicked
        if (tbl_Group.getRowCount() >= 1) {
            currentRow = tbl_Group.getSelectedRow();
            currentGroup = getSelectedGroup();
        }
    }//GEN-LAST:event_tbl_GroupMouseClicked

    private void btn_AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AddMouseClicked
        processPopup(U_Common.ADD_BUTTON);
    }//GEN-LAST:event_btn_AddMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stdManage.Views.Swing.Button btn_Add;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.stdManage.Views.Swing.Table.Table tbl_Group;
    // End of variables declaration//GEN-END:variables

}
