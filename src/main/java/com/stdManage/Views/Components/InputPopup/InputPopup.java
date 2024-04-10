/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.stdManage.Views.Components.InputPopup;

import com.stdManage.Utils.U_Styles;
import com.stdManage.Views.Components.TextField;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author ADMIN
 */
public class InputPopup extends javax.swing.JFrame {

    private I_PopupAction event;

    JPanel panel = new JPanel();
    GroupLayout panelLayout = new GroupLayout(panel);
    SequentialGroup verticalGroup = panelLayout.createSequentialGroup();
    ParallelGroup parallelGroup = panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING);

    public InputPopup(I_PopupAction event) {
        initComponents();
        initLayout();
        
        this.event = event;
        handleEvent();
    }

    public void addComponentVertical(JComponent... components) {
        for (JComponent c : components) {
            verticalGroup
                    .addGap(18, 18, 18)
                    .addComponent(c, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        }

    }

    public void addComponentHorizontal(JComponent... component) {
        for (JComponent c : component) {
            parallelGroup
                    .addComponent(c, 294, 294, 294);
        }
    }

    public void createComponents(JComponent... components) {
        panelLayout.setVerticalGroup(verticalGroup);
        panelLayout.setHorizontalGroup(parallelGroup);
        //add component
        addComponentHorizontal(components);
        addComponentVertical(components);

        JScrollPane scrollContainer = new JScrollPane(panel);
        scrollContainer.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));
        scrollContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollContainer)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollContainer)
        );
    }

    public void handleEvent() {
        btn_Cancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btn_Finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.handleFinish();
                dispose();
            }
        });
    }

    private void initLayout(){
        btn_Cancle.setBackground(U_Styles.COLOR_GRAY2);
        btn_Finish.setBackground(U_Styles.COLOR_PRIMARY);
        btn_Finish.setForeground(U_Styles.COLOR_WHITE);
        panel.setBackground(U_Styles.COLOR_WHITE);
        
        panel.setLayout(panelLayout);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_Cancle = new com.stdManage.Views.Swing.Button();
        btn_Finish = new com.stdManage.Views.Swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_Cancle.setText("Cancle");

        btn_Finish.setText("Finish");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btn_Cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btn_Finish, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Cancle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Finish, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(482, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stdManage.Views.Swing.Button btn_Cancle;
    private com.stdManage.Views.Swing.Button btn_Finish;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
