/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.Shift;
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
public class ShiftDao implements InterfaceDao<Shift> {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();

    @Override
    public Object[][] findAll() {
        List<Object[]> listData = new ArrayList<>();
        String sql = "select * from shift";
        Object result[][] = new Object[][]{};

        try {
            conn = DBConnectionDao.getConn();
            ps = conn.prepareStatement(sql);
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
    public Shift findOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(Shift model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Shift model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void getList(List<Object[]> list) throws SQLException {
        Shift model = new Shift();
        getModel(model);
        Object[] obj = model.toModelTable();
        list.add(obj);
    }

    @Override
    public void getModel(Shift model) {
        try {
            model.setId(rs.getString(U_ModelFields.SHIFT.ID));
            model.setStart_time(rs.getString(U_ModelFields.SHIFT.START_TIME));
            model.setEnd_time(rs.getString(U_ModelFields.SHIFT.END_TIME));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Get model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
