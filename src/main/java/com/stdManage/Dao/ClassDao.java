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
    
    public List<ClassModels> findAll(){
        List<ClassModels> list = new ArrayList<ClassModels>();
        String sql = "Select * from class";

        try {
                conn = new DBConnectionDao().getConn();
                ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
                rs = ps.executeQuery();// Chuyển dữ liệu ra view
                while (rs.next()) {
                    getList(list);
                }
                conn.close();
        } catch (Exception e) {
                e.printStackTrace();
        }

        return list;
    }
    
    public List<ClassModels> findAllbyCourse(String course_id){
        List<ClassModels> list = new ArrayList<ClassModels>();
        String sql = "Select * from class where course_id = ?";
        
        try {
            
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            ps.setString(1,course_id );
            rs = ps.executeQuery();// Chuyển dữ liệu ra view
            while (rs.next()) {
                getList(list);
            }

            conn.close();
        } catch (Exception e) {
                e.printStackTrace();
        }

        return list;
    }
    
    private void getList(List<ClassModels> list) throws SQLException{
        ClassModels model = new ClassModels();
        model.setId(rs.getString(U_ModelFields.CLASS.ID));
        model.setName(rs.getString(U_ModelFields.CLASS.NAME));
        model.setCourse_id(rs.getString(U_ModelFields.CLASS.COURSE_ID));
        model.setPeriod_total(rs.getInt(U_ModelFields.CLASS.PERIOD_TOTAL));
        model.setFee(rs.getDouble(U_ModelFields.CLASS.FEE));
        list.add(model);
    }
}
