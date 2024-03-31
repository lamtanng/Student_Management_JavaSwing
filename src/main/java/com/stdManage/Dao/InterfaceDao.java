/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.stdManage.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface InterfaceDao {
    public Object[][] findAll();
    public void getList(List<Object[]> list) throws SQLException;
}
