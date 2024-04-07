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
public class GradeDetail implements I_PaintRowTable{
    private String id;
    private String group_id;
    private String student_id;
    private double theory_mark;
    private double practice_mark;
    private boolean pay_status = false;
    private boolean certificate_status = false;
    private boolean status = true;

    @Override
    public Object[] toModelTable() {
        return new Object[]{id, group_id, student_id, theory_mark,
                            practice_mark, pay_status, certificate_status};
    }
    
    public GradeDetail() {
    }

    public GradeDetail(String id, String group_id, String student_id, double theory_mark, double practice_mark, boolean pay_status, boolean certificate_status) {
        this.id = id;
        this.group_id = group_id;
        this.student_id = student_id;
        this.theory_mark = theory_mark;
        this.practice_mark = practice_mark;
        this.pay_status = pay_status;
        this.certificate_status = certificate_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public double getTheory_mark() {
        return theory_mark;
    }

    public void setTheory_mark(double theory_mark) {
        this.theory_mark = theory_mark;
    }

    public double getPractice_mark() {
        return practice_mark;
    }

    public void setPractice_mark(double practice_mark) {
        this.practice_mark = practice_mark;
    }

    public boolean isPay_status() {
        return pay_status;
    }

    public void setPay_status(boolean pay_status) {
        this.pay_status = pay_status;
    }

    public boolean isCertificate_status() {
        return certificate_status;
    }

    public void setCertificate_status(boolean certificate_status) {
        this.certificate_status = certificate_status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
