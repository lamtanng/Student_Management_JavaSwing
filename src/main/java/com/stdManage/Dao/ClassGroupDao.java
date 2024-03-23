/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.ClassGroup;
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
public class ClassGroupDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<ClassGroup> findAll(){
        List<ClassGroup> list = new ArrayList<ClassGroup>();
        String sql = "Select * from class_group";

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
    
    public List<ClassGroup> findAllbyCourse(String id){
        List<ClassGroup> list = new ArrayList<ClassGroup>();
        String sql = "Select * from class_group where class_id = ?";
        
        try {
            
            conn = new DBConnectionDao().getConn();
            ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
            ps.setString(1,id );
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
    
    private void getList(List<ClassGroup> list) throws SQLException{
        ClassGroup model = new ClassGroup();
        model.setId(rs.getString(U_ModelFields.CLASS_GROUP.ID));
        model.setClass_id(rs.getString(U_ModelFields.CLASS_GROUP.CLASS_ID));
        model.setTeacher_id(rs.getString(U_ModelFields.CLASS_GROUP.TEACHER_ID));
        model.setClassroom_id(rs.getString(U_ModelFields.CLASS_GROUP.CLASSROOM_ID));
        model.setShift_id(rs.getInt(U_ModelFields.CLASS_GROUP.SHIFT_ID));
        model.setStudents_min(rs.getInt(U_ModelFields.CLASS_GROUP.STUDENT_MIN));
        model.setStudents_max(rs.getInt(U_ModelFields.CLASS_GROUP.STUDENT_MAX));
        model.setStart_date(rs.getString(U_ModelFields.CLASS_GROUP.START_DATE));
        model.setEnd_date(rs.getString(U_ModelFields.CLASS_GROUP.END_DATE));
        model.setRegister_status(rs.getBoolean(U_ModelFields.CLASS_GROUP.REGISTER_STATUS));
        model.setDay_of_week(rs.getString(U_ModelFields.CLASS_GROUP.DAY_OF_WEEK));
        
        list.add(model);
    }
}
