/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.Student;
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
public class StudentDao implements InterfaceDao<Student> {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao helperDao = new U_HelperDao();

    private static final U_ModelFields.STUDENT table = new U_ModelFields.STUDENT();
    private static final String FUNC_GEN_ID = U_HelperDao.GEN_ID("student");

    @Override
    public Object[][] findAll() {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from student";
        Object result[][] = new Object[][]{};

        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                getList(listData);
            }
            conn.close();
            result = helperDao.covertToDataTable(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void add(Student model) {
        String sql = "insert into student values (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);

            ps.setString(2, FUNC_GEN_ID);
            ps.setString(2, model.getName());
            ps.setDate(3, Date.valueOf(model.getBirth_date()));
            ps.setString(4, model.getGender());
            ps.setString(5, model.getAddress());
            ps.setString(6, model.getPhone());
            ps.setString(7, model.getUsername());

            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "Delete from student where _id = ?";
        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            ps.setString(1, id);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Delete model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Student model) {
        String sql = "UPDATE student "
                + "SET "
                + "full_name = ?, birth_date = ?, gender = ?, address = ?, phone = ?"
                + "WHERE _id = ?;";
        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            ps.setString(1, model.getName());
            ps.setDate(2, Date.valueOf(model.getBirth_date()));
            ps.setString(3, model.getGender());
            ps.setString(4, model.getAddress());
            ps.setString(5, model.getPhone());
            ps.setString(6, model.getId());
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Update model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void update_avatar(Student model) {
        String sql = "UPDATE student "
                + "SET "
                + "image =? "
                + "WHERE _id = ?;";
        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, model.getImage());
            ps.setString(2, model.getId());
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Update model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Object[][] findAll_ExceptGroup(String id) {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "SELECT student._id, student.full_name "
                + "FROM student JOIN grade_detail "
                + "ON student._id = grade_detail.student_id "
                + "WHERE grade_detail.group_id <> ? "
                + "GROUP BY student._id, student.full_name;";

        Object result[][] = new Object[][]{};

        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getString(table.ID));
                stu.setName(rs.getString(table.NAME));

                Object[] obj = helperDao.convertToArrObj(stu.getId(), stu.getName());
                listData.add(obj);
            }
            conn.close();
            result = helperDao.covertToDataTable(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Student findOne(String id) {
        Student stu = new Student();
        String sql = "SELECT * from student WHERE _id = ?";

        Object result[][] = new Object[][]{};

        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                getModel(stu);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION);

        }
        return stu;
    }

    @Override
    public void getList(List<Object[]> list) throws SQLException {
        Student model = new Student();
        getModel(model);
        Object[] obj = model.toModelTable();
        list.add(obj);
    }

    @Override
    public void getModel(Student model) {
        try {
            model.setId(rs.getString(table.ID));
            model.setName(rs.getString(table.NAME));
            model.setBirth_date(LocalDate.parse(rs.getDate(table.BIRTH_DAY).toString()));
            model.setGender(rs.getString(table.GENDER));
            model.setAddress(rs.getString(table.ADDRESS));
            model.setPhone(rs.getString(table.PHONE));
            model.setUsername(rs.getString(table.USERNAME));
            model.setImage(rs.getString(table.IMAGE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Get model fail !", JOptionPane.DEFAULT_OPTION);
        }

    }

}
