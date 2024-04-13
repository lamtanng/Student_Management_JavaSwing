/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Utils;

import com.stdManage.Models.ClassGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class U_HelperDao {

    public static final String GEN_ID(String tableName) {
        return "uf_AutoGenerateID('" + tableName + "')";
    }

    public Object[] toModelTable(Object... values) {
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

    public static List<String> schedule(List<ClassGroup> list, String shift, int... days) {

        List<ClassGroup> listBusy = new ArrayList<>();
        List<ClassGroup> listFree = new ArrayList<>();

        for (ClassGroup gr : list) {
            if (gr.getShift_id().equals(shift)) {
                for (int day : days) {
                    if (gr.getDay_of_week().contains(String.valueOf(day))) {
                        listBusy.add(gr);
                    } else {
                        listFree.add(gr);
                    }
                }
            } else {
                listFree.add(gr);
            }
        }
        
        return getUniqueStudentNames(listFree);
    }

    public static List<String> getUniqueStudentNames(List<ClassGroup> freeList) {
        return freeList.stream()
                .map(ClassGroup::getTeacher_id)
                .distinct()
                .collect(Collectors.toList());
    }
}
