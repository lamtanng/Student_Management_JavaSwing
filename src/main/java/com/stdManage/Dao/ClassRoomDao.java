/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.ClassRoom;
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
public class ClassRoomDao implements InterfaceDao<ClassRoom> {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();

    @Override
    public Object[][] findAll() {
        List<Object[]> listData = new ArrayList<>();
        String sql = "select * from classroom";
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
    public ClassRoom findOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(ClassRoom model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ClassRoom model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void getList(List<Object[]> list) {
        ClassRoom model = new ClassRoom();
        getModel(model);
        Object[] obj = model.toModelTable();
        list.add(obj);
    }

    @Override
    public void getModel(ClassRoom model) {
        try {
            model.setId(rs.getString(U_ModelFields.CLASS_ROOM.ID));
            model.setSeat(rs.getInt(U_ModelFields.CLASS_ROOM.SEAT));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Get model failure!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
