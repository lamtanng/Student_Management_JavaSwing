/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.ClassModels;
import com.stdManage.Models.GradeDetail;
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
public class GradeDetailDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    U_ModelFields.GRADE_DETAIL grade_detail = new U_ModelFields.GRADE_DETAIL();
    
    public List<GradeDetail> findAll(){
        List<GradeDetail> list = new ArrayList<GradeDetail>();
        String sql = "Select * from grade_detail";

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
    
    public List<GradeDetail> findAllbyGroup(String id){
        List<GradeDetail> list = new ArrayList<GradeDetail>();
        String sql = "Select * from grade_detail where group_id = ?";

        try {
                conn = new DBConnectionDao().getConn();
                ps = conn.prepareStatement(sql);
                ps.setString(1, id);
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
    
    
    
    private void getList(List<GradeDetail> list) throws SQLException{
        GradeDetail model = new GradeDetail();
        model.setId(rs.getString(U_ModelFields.GRADE_DETAIL.ID));
        model.setGroup_id(rs.getString(U_ModelFields.GRADE_DETAIL.GROUP_ID));
        model.setStudent_id(rs.getString(U_ModelFields.GRADE_DETAIL.STUDENT_ID));
        model.setTheory_mark(rs.getInt(U_ModelFields.GRADE_DETAIL.THEORY_MARK));
        model.setPractice_mark(rs.getDouble(U_ModelFields.GRADE_DETAIL.PRACTICE_MARK));
        model.setPay_status(rs.getBoolean(U_ModelFields.GRADE_DETAIL.PAY_STATUS));
        model.setCertificate_status(rs.getBoolean(U_ModelFields.GRADE_DETAIL.CERT_STATUS));
        list.add(model);
    }   
}
