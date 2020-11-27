/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gia nguyen
 */
public class Khoa implements Serializable{
    private long id;
    private String maKhoa;
    private String tenKhoa;
    private String chg_Who;
    private Date chg_Date;
    private String status;

    public Khoa() {
    }

    public Khoa(Khoa obj) {
        this.id = obj.getId();
        this.maKhoa = obj.getMaKhoa();
        this.tenKhoa = obj.getTenKhoa();
        this.chg_Who = obj.getChg_Who();
        this.chg_Date = obj.getChg_Date();
        this.status = obj.getStatus();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getChg_Who() {
        return chg_Who;
    }

    public void setChg_Who(String chg_Who) {
        this.chg_Who = chg_Who;
    }

    public Date getChg_Date() {
        return chg_Date;
    }

    public void setChg_Date(Date chg_Date) {
        this.chg_Date = chg_Date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
