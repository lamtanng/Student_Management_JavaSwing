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
public class Account implements I_PaintRowTable{
    private String username;
    private String role;
    private String password;

    public Account() {
    }

    public Account(String username, int status, String role, String password, String code) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public Object[] toModelTable(){
        return new Object[]{username, password, role};
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String  username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
