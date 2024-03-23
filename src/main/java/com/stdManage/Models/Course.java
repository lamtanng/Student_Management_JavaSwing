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
public class Course implements I_PaintRowTable{
    private String id;
    private String name;
    

    public Course() {
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public Object[] toModelTable() {
        return new Object[]{id, name};
    }

        
}
