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
public class MonHoc implements Serializable{
    private long id;
    private String maMon;
    private String tenMon;
    private String maKhoa;
    private long soTinChi;
    private String chg_Who;
    private Date chg_Date;
    private String status;
    private String tenkhoa;

    public MonHoc() {
    }

    public MonHoc(MonHoc obj) {
        this.maKhoa = obj.getMaKhoa();
        this.id = obj.getId();
        this.maMon = obj.getMaMon();
        this.tenMon = obj.getTenMon();
        this.soTinChi = obj.getSoTinChi();
        this.chg_Who = obj.getChg_Who();
        this.chg_Date = obj.getChg_Date();
        this.status = obj.getStatus();
        this.tenkhoa = obj.getTenkhoa();
        
    }

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public long getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(long soTinChi) {
        this.soTinChi = soTinChi;
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

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

}
