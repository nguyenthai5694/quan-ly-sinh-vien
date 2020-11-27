/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author gia nguyen
 */
public class HocPhi implements Serializable{
    private long id;
    private String codeHK;
    private String codeStudent;
    private long totalTC;
    private double totalTien;
    private String status;

    public HocPhi() {
    }

    public HocPhi(HocPhi obj) {
        this.id = obj.getId();
        this.codeHK = obj.getCodeHK();
        this.codeStudent = obj.getCodeStudent();
        this.totalTC = obj.getTotalTC();
        this.totalTien = obj.getTotalTien();
        this.status = obj.getStatus();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodeHK() {
        return codeHK;
    }

    public void setCodeHK(String codeHK) {
        this.codeHK = codeHK;
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public void setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    public long getTotalTC() {
        return totalTC;
    }

    public void setTotalTC(long totalTC) {
        this.totalTC = totalTC;
    }

    public double getTotalTien() {
        return totalTien;
    }

    public void setTotalTien(double totalTien) {
        this.totalTien = totalTien;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
