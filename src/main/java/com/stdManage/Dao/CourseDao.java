/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.Course;
import com.stdManage.Utils.U_ModelFields;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CourseDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Course> findAll(){
        List<Course> courses = new ArrayList<Course>();
        String sql = "Select * from course";

        try {
                conn = new DBConnectionDao().getConn();
                ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
                rs = ps.executeQuery();// Chuyển dữ liệu ra view
                while (rs.next()) {

                    Course model = new Course();
                    model.setId(rs.getString(U_ModelFields.COURSE.ID));
                    model.setName(rs.getString(U_ModelFields.COURSE.NAME));
                    courses.add(model);
                    Object[] obj = model.toModelTable();
                }

                conn.close();
        } catch (Exception e) {
                e.printStackTrace();
        }

        return courses;
    }
}
