/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Models;

/**
 *
 * @author ADMIN
 */
public class Teacher{
    private String id;
    private String name;
    private String birth_date;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String username;

    public Teacher() {
    }

    public Teacher(String id, String name, String birth_date, String gender, String address, String phone, String email, String username) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
    
}
