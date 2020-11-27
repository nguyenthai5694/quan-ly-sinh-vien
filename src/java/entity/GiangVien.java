/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class GiangVien {
    private long id;
    private String maGv;
    private String tenGv;
    private Date ngaySinhGv;
    private String dienThoai;
    private String diaChi;
    private String CMND;
    private Date ngayCapCMND;
    private String noiCapCMND;
    private String nguyenQuan;
    private String maKhoa;
    private String listMonHoc;
    private String chgWho;
    private Date chgDate;
    private int status;
    private int gender;

    public GiangVien() {
    }

    /**
     *
     * @param obj
     */
    public GiangVien(GiangVien obj) {
        this.id = obj.getId();
        this.maGv = obj.getMaGv();
        this.tenGv = obj.getTenGv();
        this.ngaySinhGv = obj.getNgaySinhGv();
        this.dienThoai = obj.getDienThoai();
        this.diaChi = obj.getDiaChi();
        this.CMND = obj.getCMND();
        this.ngayCapCMND = obj.getNgayCapCMND();
        this.noiCapCMND = obj.getNoiCapCMND();
        this.nguyenQuan = obj.getNguyenQuan();
        this.maKhoa = obj.getMaKhoa();
        this.listMonHoc = obj.getListMonHoc();
        this.chgWho = obj.getChgWho();
        this.chgDate = obj.getChgDate();
        this.status = obj.getStatus();
    }

    public Date getNgayCapCMND() {
        return ngayCapCMND;
    }

    public void setNgayCapCMND(Date ngayCapCMND) {
        this.ngayCapCMND = ngayCapCMND;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaGv() {
        return maGv;
    }

    public void setMaGv(String maGv) {
        this.maGv = maGv;
    }

    public String getTenGv() {
        return tenGv;
    }

    public void setTenGv(String tenGv) {
        this.tenGv = tenGv;
    }

    public Date getNgaySinhGv() {
        return ngaySinhGv;
    }

    public void setNgaySinhGv(Date ngaySinhGv) {
        this.ngaySinhGv = ngaySinhGv;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


    public String getNoiCapCMND() {
        return noiCapCMND;
    }

    public void setNoiCapCMND(String noiCapCMND) {
        this.noiCapCMND = noiCapCMND;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getListMonHoc() {
        return listMonHoc;
    }

    public void setListMonHoc(String listMonHoc) {
        this.listMonHoc = listMonHoc;
    }

    public String getChgWho() {
        return chgWho;
    }

    public void setChgWho(String chgWho) {
        this.chgWho = chgWho;
    }

    public Date getChgDate() {
        return chgDate;
    }

    public void setChgDate(Date chgDate) {
        this.chgDate = chgDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

    
}
