/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Utils;

import java.util.List;

/**
 *
 * @author PC
 */
public class U_HelperDao {
    public Object[][] covertToDataTable(List<Object[]> listData) {
        Object[][] listModel = new Object[listData.size()][];
        for(int i = 0; i < listData.size(); i++){
            listModel[i] = listData.get(i);
        }
        return listModel;
    }
}
