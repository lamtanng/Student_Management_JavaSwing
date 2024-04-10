/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.stdManage.Models.ClassModel;
import com.stdManage.Utils.U_HelperDao;
import com.stdManage.Utils.U_ModelFields;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ClassDao implements InterfaceDao<ClassModel> {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();

    @Override
    public Object[][] findAll() {
        List<Object[]> listData = new ArrayList<>();
        String sql = "Select * from class where status = true";
        Object result[][] = new Object[][]{};

        try {
            conn = DBConnectionDao.getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            rs = ps.executeQuery();// Chuyển dữ liệu ra view
            while (rs.next()) {
                getList(listData);
            }
            conn.close();
            result = heplerDao.covertToDataTable(listData);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Get model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    public Object[][] findAllbyCourse(String id) {
        List<Object[]> listData = new ArrayList<>();
        String sql = "Select * from class where course_id = ? and status = true ";
        Object result[][] = new Object[][]{};

        try {
            conn = DBConnectionDao.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                getList(listData);
            }
            conn.close();
            result = heplerDao.covertToDataTable(listData);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Get model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    @Override
    public void delete(String id) {
        String sql = "UPDATE class SET status = 0 where _id = ?";
        try {
            conn = DBConnectionDao.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Delete model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(ClassModel model) {
        String sql = "Update class SET name = ?, period_total = ?, fee = ? WHERE _id = ?";
        try {
            conn = DBConnectionDao.getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            ps.setString(1, model.getName());
            ps.setInt(2, model.getPeriod_total());
            ps.setDouble(3, model.getFee());
            ps.setString(4, model.getId());
            ps.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null, "Success !", "Update class", JOptionPane.PLAIN_MESSAGE);

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Update model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void add(ClassModel model) {
        String sql = "insert into class values (?, ?,?, ?, ?,true)";
        try {
            conn = DBConnectionDao.getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            ps.setString(1, model.getId());
            ps.setString(2, model.getCourse_id());
            ps.setString(3, model.getName());
            ps.setInt(4, model.getPeriod_total());
            ps.setDouble(5, model.getFee());
//            ps.setBoolean(6, model.getStatus());
            ps.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null, "Success !", "Add new class", JOptionPane.PLAIN_MESSAGE);

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Add new model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void getList(List<Object[]> list) throws SQLException {
        ClassModel model = new ClassModel();
        getModel(model);
        Object[] obj = model.toModelTable();
        list.add(obj);
    }

    @Override
    public void getModel(ClassModel model) {
        try {
            model.setId(rs.getString(U_ModelFields.CLASS.ID));
            model.setName(rs.getString(U_ModelFields.CLASS.NAME));
            model.setCourse_id(rs.getString(U_ModelFields.CLASS.COURSE_ID));
            model.setPeriod_total(rs.getInt(U_ModelFields.CLASS.PERIOD_TOTAL));
            model.setFee(rs.getDouble(U_ModelFields.CLASS.FEE));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Get model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public ClassModel findOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
