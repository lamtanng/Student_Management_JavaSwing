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
public class ClassModels implements I_PaintRowTable{
    private String id;
    private String name;
    private String course_id;
    private int period_total;
    private double fee;
    private boolean status = true;

    public ClassModels() {
    }

    public ClassModels(String id, String name, String course_id, int period_total, double fee) {
        this.id = id;
        this.name = name;
        this.course_id = course_id;
        this.period_total = period_total;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public int getPeriod_total() {
        return period_total;
    }

    public void setPeriod_total(int period_total) {
        this.period_total = period_total;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public Object[] toModelTable() {
        return new Object[]{id, name, course_id, period_total, fee};
    }
    
    
    }
