/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Models;

/**
 *
 * @author ADMIN
 */
public class Class{
    private String id;
    private String name;
    private String course_id;
    private int period_total;
    private int fee;

    public Class() {
    }

    public Class(String id, String name, String course_id, int period_total, int fee) {
        this.id = id;
        this.name = name;
        this.course_id = course_id;
        this.period_total = period_total;
        this.fee = fee;
    }

    public String getId() {
        return id;
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

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
    
    
    }
