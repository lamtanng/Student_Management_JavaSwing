/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stdManage.Views.Swing.JTable;

import com.stdManage.Utils.U_Common;
import com.stdManage.Utils.U_Styles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class PanelAction extends javax.swing.JPanel {

    private int typeAction;

    ActionButton btn_First = new com.stdManage.Views.Swing.JTable.ActionButton();
    ActionButton btn_Second = new com.stdManage.Views.Swing.JTable.ActionButton();

    public PanelAction(int typeAction) {
        initComponents();
        this.typeAction = typeAction;
        initActionButton();
        setImageButton();

    }

    public void initEvent(ITableActionEvent event, int row, int col) {
        if (typeAction == U_Common.ActionTable.EDIT_DELETE) {
            //handle second buton
            btn_Second.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    event.onSecondButton(row, col);
                }
            });
        }
        
        //handle first action
        btn_First.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onFirstButton(row, col);
            }
        });

    }

    private void initActionButton() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Second, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_Second, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE))
        );
    }

    private void setImageButton() {
        //2 action buttons
        if (typeAction == U_Common.ActionTable.EDIT_DELETE) {
            btn_First.setIcon(U_Common.createImageIcon("edit.png", U_Common.IMAGE_FOLDER));
            btn_Second.setIcon(U_Common.createImageIcon("trash-xmark.png", U_Common.IMAGE_FOLDER));

        } else {//1 action button
            btn_Second.setVisible(false);
            
            if (typeAction == U_Common.ActionTable.ADD) {
                btn_First.setIcon(U_Common.createImageIcon("add-button.png", U_Common.IMAGE_FOLDER));

            } else if (typeAction == U_Common.ActionTable.DELETE) {
                btn_First.setIcon(U_Common.createImageIcon("trash-xmark.png", U_Common.IMAGE_FOLDER));
            }
        }
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
