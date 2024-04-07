/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.stdManage.Models.ClassModels;
import com.stdManage.Utils.U_HelperDao;
import com.stdManage.Utils.U_ModelFields;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ClassDao implements InterfaceDao<ClassModels> {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();

    @Override
    public Object[][] findAll() {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from class where status = true";
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
            JOptionPane.showMessageDialog(null, "Get model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    public Object[][] findAllbyCourse(String id) {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from class where course_id = ? and status = true ";
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
            JOptionPane.showMessageDialog(null, "Get model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return result;
    }

    @Override
    public void delete(String id) {
        String sql = "Delete from class where _id = ?";
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
    public void update(ClassModels model) {
        String sql = "Update class SET name = ?, period_total = ?, fee = ? WHERE _id = ?";
        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            ps.setString(1, model.getName());
            ps.setInt(2, model.getPeriod_total());
            ps.setDouble(3, model.getFee());
            ps.setString(4, model.getId());
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Update model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void add(ClassModels model) {
        String sql = "insert into class values (?, ?,?, ?, ?)";
        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            ps.setString(1, model.getName());
            ps.setInt(2, model.getPeriod_total());
            ps.setDouble(3, model.getFee());
            ps.setString(4, model.getId());
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Update model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void getList(List<Object[]> list) throws SQLException {
        ClassModels model = new ClassModels();
        getModel(model);
        Object[] obj = model.toModelTable();
        list.add(obj);
    }

    @Override
    public void getModel(ClassModels model) {
        try {
            model.setId(rs.getString(U_ModelFields.CLASS.ID));
            model.setName(rs.getString(U_ModelFields.CLASS.NAME));
            model.setCourse_id(rs.getString(U_ModelFields.CLASS.COURSE_ID));
            model.setPeriod_total(rs.getInt(U_ModelFields.CLASS.PERIOD_TOTAL));
            model.setFee(rs.getDouble(U_ModelFields.CLASS.FEE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Get model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public ClassModels findOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
