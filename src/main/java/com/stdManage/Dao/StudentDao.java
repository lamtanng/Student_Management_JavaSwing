/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.GradeDetail;
import com.stdManage.Models.Student;
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
public class StudentDao implements InterfaceDao{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_HelperDao heplerDao = new U_HelperDao();
    static final U_ModelFields.STUDENT table = new U_ModelFields.STUDENT();
    
    
    public Object[][] findAll(){
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "Select * from student";
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
    
    public Object[][] findAll_ExceptGroup(String id){
        List<Object[]> listData = new ArrayList<Object[]>();
        String sql = "SELECT student._id, student.full_name "
                + "FROM student JOIN grade_detail "
                + "ON student._id = grade_detail.student_id "
                + "WHERE grade_detail.group_id <> ? "
                + "GROUP BY student._id, student.full_name;";
        
        Object result[][] = new Object[][]{};
        
        try {
                conn = new DBConnectionDao().getConn();
                ps = conn.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student stu = new Student();
                    stu.setId(rs.getString(table.ID));
                    stu.setName(rs.getString(table.NAME));
                    
                    Object[] obj = heplerDao.convertToArrObj(stu.getId(), stu.getName());
                    listData.add(obj);
                }
                conn.close();
                result = heplerDao.covertToDataTable(listData);
        } catch (Exception e) {
                e.printStackTrace();
        }

        return result;
    }
    
    
    public void getList(List<Object[]> list) throws SQLException{
        Student model = new Student();
        model.setId(rs.getString(table.ID));
        model.setName(rs.getString(table.NAME));
        model.setBirth_date(rs.getString(table.BIRTH_DAY.toString()));
        model.setGender(rs.getString(table.GENDER));
        model.setAddress(rs.getString(table.ADDRESS));
        model.setPhone(rs.getString(table.PHONE));
        model.setUsername(rs.getString(table.USERNAME));
        Object[] obj = model.toModelTable();
        list.add(obj);
    }   
}
