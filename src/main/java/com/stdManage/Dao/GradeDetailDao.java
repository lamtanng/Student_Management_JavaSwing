/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.ClassModel;
import com.stdManage.Models.GradeDetail;
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
public class GradeDetailDao implements InterfaceDao<GradeDetail> {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();
    U_ModelFields.GRADE_DETAIL grade_detail = new U_ModelFields.GRADE_DETAIL();

    @Override
    public Object[][] findAll() {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from grade_detail where status = true";
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

    public Object[][] findAllbyGroup(String id) {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from grade_detail where group_id = ? and status = true";
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

    public Object[][] findAll_ExceptGroup(String id) {
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from grade_detail where group_id <> ? and status = true";
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
    public GradeDetail findOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(GradeDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(GradeDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void getList(List<Object[]> list) throws SQLException {
        GradeDetail model = new GradeDetail();
        getModel(model);
        Object[] obj = model.toModelTable();
        list.add(obj);
    }

    @Override
    public void getModel(GradeDetail model) {
        try {
            model.setId(rs.getString(U_ModelFields.GRADE_DETAIL.ID));
            model.setGroup_id(rs.getString(U_ModelFields.GRADE_DETAIL.GROUP_ID));
            model.setStudent_id(rs.getString(U_ModelFields.GRADE_DETAIL.STUDENT_ID));
            model.setTheory_mark(rs.getInt(U_ModelFields.GRADE_DETAIL.THEORY_MARK));
            model.setPractice_mark(rs.getDouble(U_ModelFields.GRADE_DETAIL.PRACTICE_MARK));
            model.setPay_status(rs.getBoolean(U_ModelFields.GRADE_DETAIL.PAY_STATUS));
            model.setCertificate_status(rs.getBoolean(U_ModelFields.GRADE_DETAIL.CERT_STATUS));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION);
        }
    }
}
