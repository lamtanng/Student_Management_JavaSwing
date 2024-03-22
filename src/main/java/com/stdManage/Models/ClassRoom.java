/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Models;

/**
 *
 * @author ADMIN
 */
public class ClassRoom{
    private String id;
    private int seat;

    public ClassRoom() {
    }

    public ClassRoom(String id, int seat) {
        this.id = id;
        this.seat = seat;
    }

    public String getId() {
        return id;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    
}
