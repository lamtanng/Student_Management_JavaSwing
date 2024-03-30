/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Views.Swing.JTable;

import com.stdManage.Utils.U_Styles;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author ADMIN
 */
public class TableActionCellEditor extends DefaultCellEditor{
    private ITableActionEvent event;
    private int typeaction;
    public TableActionCellEditor(ITableActionEvent event, int typeAction) {
        super(new JCheckBox());
        this.event = event;
        this.typeaction = typeAction;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelAction action = new PanelAction(typeaction);
        action.initEvent(event, row, column);
        return action;
    }
}
