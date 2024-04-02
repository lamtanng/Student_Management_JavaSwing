/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.ClassModels;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface InterfaceDao<T> {
    
    public Object[][] findAll();
    public T findOne(String id);
    public void add(T model);
    public void update(T model);
    public void delete(String id);
    public void getList(List<Object[]> list) throws SQLException;
    public void getModel(T model);
}
