/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.Account;
import com.stdManage.Models.ClassGroup;
import com.stdManage.Models.ClassModels;
import com.stdManage.Utils.U_HelperDao;
import com.stdManage.Utils.U_ModelFields;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class AccountDao implements InterfaceDao<Account> {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();

    @Override
    public Object[][] findAll() {

        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from account";
        Object result[][] = new Object[][]{};

        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);
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

    public Account findOne(String username, String pass) {
        Account model = new Account();
        String sql = "Select * from account where username = ? and password = ?";

        try {
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);

            rs = ps.executeQuery();
            while (rs.next()) {
                getModel(model);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Username or Password incorrect !", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return model;
    }

    @Override
    public void add(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void getList(List<Object[]> list) throws SQLException {
        Account dataModel = new Account();
        getModel(dataModel);
        Object[] obj = dataModel.toModelTable();
        list.add(obj);
    }

    public void getModel(Account dataModel) {
        try {
            dataModel.setUsername(rs.getString(U_ModelFields.ACCOUNT.USERNAME));
            dataModel.setPassword(rs.getString(U_ModelFields.ACCOUNT.PASSWORD));
            dataModel.setRole(rs.getString(U_ModelFields.ACCOUNT.ROLE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Get model is failure !", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public Account findOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
