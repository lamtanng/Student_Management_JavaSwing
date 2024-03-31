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
public class ClassGroup implements I_PaintRowTable{
    private String id;
    private String class_id;
    private String teacher_id;
    private String classroom_id;
    private String shift_id;
    private int students_min;
    private int students_max;
    private String start_date;
    private String end_date;
    private boolean register_status = false;
    private String day_of_week;

    public ClassGroup() {
    }

    public ClassGroup(String id, String class_id, String teacher_id, String classroom_id, String shift_id, int students_min, int students_max, String start_date, String end_date, boolean register_status, String day_of_week) {
        this.id = id;
        this.class_id = class_id;
        this.teacher_id = teacher_id;
        this.classroom_id = classroom_id;
        this.shift_id = shift_id;
        this.students_min = students_min;
        this.students_max = students_max;
        this.start_date = start_date;
        this.end_date = end_date;
        this.register_status = register_status;
        this.day_of_week = day_of_week;
    }

    @Override
    public Object[] toModelTable() {
        return new Object[]{id, class_id, teacher_id, classroom_id, 
                            shift_id, students_min, students_max, start_date, 
                            end_date, register_status, day_of_week};
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(String classroom_id) {
        this.classroom_id = classroom_id;
    }

    public String getShift_id() {
        return shift_id;
    }

    public void setShift_id(String shift_id) {
        this.shift_id = shift_id;
    }

    public int getStudents_min() {
        return students_min;
    }

    public void setStudents_min(int students_min) {
        this.students_min = students_min;
    }

    public int getStudents_max() {
        return students_max;
    }

    public void setStudents_max(int students_max) {
        this.students_max = students_max;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public boolean isRegister_status() {
        return register_status;
    }

    public void setRegister_status(boolean register_status) {
        this.register_status = register_status;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    
    
}
