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

/**
 *
 * @author ADMIN
 */
public class ClassDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();
    
    public Object[][] findAll(){
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from class";
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
    
    public Object[][] findAllbyCourse(String id){
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from class where course_id = ?";
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
    
    private void getList(List<Object[]> list) throws SQLException{
        ClassModels model = new ClassModels();
        model.setId(rs.getString(U_ModelFields.CLASS.ID));
        model.setName(rs.getString(U_ModelFields.CLASS.NAME));
        model.setCourse_id(rs.getString(U_ModelFields.CLASS.COURSE_ID));
        model.setPeriod_total(rs.getInt(U_ModelFields.CLASS.PERIOD_TOTAL));
        model.setFee(rs.getDouble(U_ModelFields.CLASS.FEE));
        Object[] obj = model.toModelTable();
        list.add(obj);
    }
}
