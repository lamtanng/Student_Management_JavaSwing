/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Models;

import com.stdManage.Interface.I_PaintRowTable;

/**
 *
 * @author ADMIN
 */
public class ClassRoom implements I_PaintRowTable{
    private String id;
    private int seat;

    @Override
    public Object[] toModelTable() {
        return new Object[]{id, seat};
    }

    public ClassRoom() {
    }

    public ClassRoom(String id, int seat) {
        this.id = id;
        this.seat = seat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    
    
}
