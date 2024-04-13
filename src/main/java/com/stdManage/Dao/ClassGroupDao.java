/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.ClassGroup;
import com.stdManage.Models.ClassRoom;
import com.stdManage.Models.Teacher;
import com.stdManage.Utils.U_HelperDao;
import com.stdManage.Utils.U_ModelFields;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ClassGroupDao implements InterfaceDao<ClassGroup> {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();

    private static final String FUNC_GEN_ID = "TG_AutoIncrID()";

    @Override
    public Object[][] findAll() {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from class_group where status = true";
        Object result[][] = new Object[][]{};

        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            rs = ps.executeQuery();// Chuyển dữ liệu ra view
            while (rs.next()) {
                getList(listData);
            }
            conn.close();
            result = heplerDao.covertToDataTable(listData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Object[][] findAllbyCourse(String id) {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from class_group where class_id = ? and status = true";
        Object result[][] = new Object[][]{};

        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                getList(listData);
            }
            conn.close();
            result = heplerDao.covertToDataTable(listData);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    public Object[][] findTeachersFreeTime(String shiftID, String daysOfWeek) {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "CALL getTeacherFree(?, ?); ";
        Object result[][] = new Object[][]{};

        try {
            conn = DBConnectionDao.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, shiftID);
            ps.setString(2, daysOfWeek);
            rs = ps.executeQuery();
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setId(rs.getString(U_ModelFields.TEACHER.ID));
                t.setName(rs.getString(U_ModelFields.TEACHER.NAME));
                Object[] obj = heplerDao.toModelTable(t.getId(), t.getName());
                listData.add(obj);
            }
            conn.close();
            result = heplerDao.covertToDataTable(listData);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    public Object[][] findRoomsFreeTime(String shiftID, String daysOfWeek) {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "CALL getRoomFree(?, ?); ";
        Object result[][] = new Object[][]{};

        try {
            conn = DBConnectionDao.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, shiftID);
            ps.setString(2, daysOfWeek);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClassRoom r = new ClassRoom();
                r.setId(rs.getString(U_ModelFields.CLASS_ROOM.ID));
                r.setSeat(rs.getInt(U_ModelFields.CLASS_ROOM.SEAT));
                Object[] obj = r.toModelTable();
                listData.add(obj);
            }
            conn.close();
            result = heplerDao.covertToDataTable(listData);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    @Override
    public ClassGroup findOne(String id) {
        ClassGroup model = new ClassGroup();
        String sql = "Select * from class_group where _id = ? and status = true";

        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                getModel(model);
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return model;
    }

    @Override
    public void add(ClassGroup model) {
        String sql = "INSERT INTO class_group("
                + "_id, class_id, teacher_id, classroom_id, shift_id, "
                + "students_min, students_max, start_date, period_checked, "
                + "register_status, day_of_week, status, is_open) VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, false, ?, true, false)";
        try {
            conn = DBConnectionDao.getConn();
            ps = conn.prepareStatement(sql);

            ps.setString(1, FUNC_GEN_ID);
            ps.setString(2, model.getClass_id());
            ps.setString(3, model.getTeacher_id());
            ps.setString(4, model.getClassroom_id());
            ps.setString(5, model.getShift_id());
            ps.setInt(6, model.getStudents_min());
            ps.setInt(7, model.getStudents_max());
            ps.setDate(8, Date.valueOf(model.getStart_date()));
            ps.setInt(9, 0);//period_checked default 0
            ps.setString(10, model.getDay_of_week());

            ps.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null, "Success !", "Add new group", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(ClassGroup model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void getList(List<Object[]> list) throws SQLException {
        ClassGroup model = new ClassGroup();
        getModel(model);
        Object[] obj = model.toModelTable();
        list.add(obj);
    }

    @Override
    public void getModel(ClassGroup model) {
        try {
            model.setId(rs.getString(U_ModelFields.CLASS_GROUP.ID));
            model.setClass_id(rs.getString(U_ModelFields.CLASS_GROUP.CLASS_ID));
            model.setTeacher_id(rs.getString(U_ModelFields.CLASS_GROUP.TEACHER_ID));
            model.setClassroom_id(rs.getString(U_ModelFields.CLASS_GROUP.CLASSROOM_ID));
            model.setShift_id(rs.getString(U_ModelFields.CLASS_GROUP.SHIFT_ID));
            model.setStudents_min(rs.getInt(U_ModelFields.CLASS_GROUP.STUDENT_MIN));
            model.setStudents_max(rs.getInt(U_ModelFields.CLASS_GROUP.STUDENT_MAX));
            model.setStart_date(LocalDate.parse(rs.getString(U_ModelFields.CLASS_GROUP.START_DATE)));
            model.setPeriod_Checked(rs.getInt(U_ModelFields.CLASS_GROUP.PERIOD_CHECKED));
            model.setRegister_status(rs.getBoolean(U_ModelFields.CLASS_GROUP.REGISTER_STATUS));
            model.setDay_of_week(rs.getString(U_ModelFields.CLASS_GROUP.DAY_OF_WEEK));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Get model is failure !", JOptionPane.ERROR_MESSAGE);
        }

    }

}
