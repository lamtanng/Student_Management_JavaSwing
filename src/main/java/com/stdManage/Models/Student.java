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
public class Student implements I_PaintRowTable {

    private String id;
    private String name;
    private String birth_date;
    private String gender;
    private String address;
    private String phone;
    private String username;
    private String image = "";

    @Override
    public Object[] toModelTable() {
        return new Object[]{id, name, birth_date, gender,
            address, phone, username, image};
    }

    public Student() {
    }

    public Student(String id, String name, String birth_date, String gender, String address, String phone, String email, String username, String image) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.image = image;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
