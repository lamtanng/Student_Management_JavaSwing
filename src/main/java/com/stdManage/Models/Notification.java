/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Models;

/**
 *
 * @author ADMIN
 */
public class Notification{
    private String id;
    private String teacher_id;
    private String title;
    private String class_group_id;

    public Notification() {
    }

    public Notification(String id, String teacher_id, String title, String class_group_id) {
        this.id = id;
        this.teacher_id = teacher_id;
        this.title = title;
        this.class_group_id = class_group_id;
    }

    public String getId() {
        return id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClass_group_id() {
        return class_group_id;
    }

    public void setClass_group_id(String class_group_id) {
        this.class_group_id = class_group_id;
    }

     
}
