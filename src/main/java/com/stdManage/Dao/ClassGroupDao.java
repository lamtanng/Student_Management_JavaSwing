/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.ClassGroup;
import com.stdManage.Utils.U_HelperDao;
import com.stdManage.Utils.U_ModelFields;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return model;
    }

    @Override
    public void add(ClassGroup model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            model.setStart_date(rs.getString(U_ModelFields.CLASS_GROUP.START_DATE));
            model.setEnd_date(rs.getString(U_ModelFields.CLASS_GROUP.END_DATE));
            model.setRegister_status(rs.getBoolean(U_ModelFields.CLASS_GROUP.REGISTER_STATUS));
            model.setDay_of_week(rs.getString(U_ModelFields.CLASS_GROUP.DAY_OF_WEEK));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Get model is failure !", JOptionPane.ERROR_MESSAGE);
        }

    }

}
