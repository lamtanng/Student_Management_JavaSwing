/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.Account;
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
/**
 *
 * @author ADMIN
 */
public class AccountDao{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();
    public Object[][] findAll(){
        
        List<Object[]> listData= new ArrayList<Object[]>();
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
    
     private void getList(List<Object[]> list) throws SQLException{
        Account dataModel = new Account();
        dataModel.setUsername(rs.getString(U_ModelFields.ACCOUNT.USERNAME));
        dataModel.setPassword(rs.getString(U_ModelFields.ACCOUNT.PASSWORD));
        dataModel.setRole(rs.getString(U_ModelFields.ACCOUNT.ROLE));
        Object[] obj = dataModel.toModelTable();
        list.add(obj);
    }


}


