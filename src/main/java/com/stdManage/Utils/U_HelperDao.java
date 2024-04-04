/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Utils;

import com.stdManage.Views.Student.F_Student;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class U_HelperDao {

    public static final String GEN_ID(String tableName) {
        return "uf_AutoGenerateID('" + tableName + "')";
    }

    public Object[] convertToArrObj(Object... values) {
        return values;
    }

    public Object[][] covertToDataTable(List<Object[]> listData) {
        Object[][] listModel = new Object[listData.size()][];
        for (int i = 0; i < listData.size(); i++) {
            listModel[i] = listData.get(i);
        }
        return listModel;
    }

    public List<Object[]> covertToListObject1D(Object[][] list2D) {
        List<Object[]> list1D = new ArrayList<Object[]>();
        for (Object[] row : list2D) {
            list1D.add(row);
        }
        return list1D;
    }
}
