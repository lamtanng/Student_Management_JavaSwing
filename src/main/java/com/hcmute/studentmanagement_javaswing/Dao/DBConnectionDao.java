/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmute.studentmanagement_javaswing.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class DBConnectionDao {
    public static Connection conn(){
        try {
            String url = "jdbc:mysql://localhost:3307/testdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "admin";
            String pass = "123456";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
}
