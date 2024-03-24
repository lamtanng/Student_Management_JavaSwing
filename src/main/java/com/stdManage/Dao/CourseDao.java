/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.Account;
import com.stdManage.Models.Course;
import com.stdManage.Utils.U_HelperDao;
import com.stdManage.Utils.U_ModelFields;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CourseDao{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();
    
    public Object[][] findAll(){
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from course";
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
    
    public List<Object[]> convertToListObject1D(Object[][] list2D){
        return heplerDao.covertToListObject1D(list2D);
    }
    
    private void getList(List<Object[]> list) throws SQLException{
        Course model = new Course();
        model.setId(rs.getString(U_ModelFields.COURSE.ID));
        model.setName(rs.getString(U_ModelFields.COURSE.NAME));
        Object[] obj = model.toModelTable();
        list.add(obj);
    }
}
