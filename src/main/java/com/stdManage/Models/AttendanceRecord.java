/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Models;

import com.stdManage.Interface.I_PaintRowTable;

/**
 *
 * @author ADMIN
 */
public class AttendanceRecord implements I_PaintRowTable{
    private String id;
    private String check_date;
    private String class_group_id;
    private String student_id;
    private boolean is_present = false;

    @Override
    public Object[] toModelTable() {
        return new Object[]{id, check_date, class_group_id, student_id, is_present };
    }

    
    public AttendanceRecord() {
    }

    public AttendanceRecord(String id, String check_date, String class_group_id, String student_id) {
        this.id = id;
        this.check_date = check_date;
        this.class_group_id = class_group_id;
        this.student_id = student_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public String getClass_group_id() {
        return class_group_id;
    }

    public void setClass_group_id(String class_group_id) {
        this.class_group_id = class_group_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public boolean isIs_present() {
        return is_present;
    }

    public void setIs_present(boolean is_present) {
        this.is_present = is_present;
    }

    
}
