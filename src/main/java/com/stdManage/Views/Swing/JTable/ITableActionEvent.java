/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.stdManage.Views.Swing.JTable;

/**
 *
 * @author ADMIN
 */
public interface ITableActionEvent {
    public void onEdit(int row, int col);

    public void onDelete(int row, int col);

    public void onView(int row, int col);
}
