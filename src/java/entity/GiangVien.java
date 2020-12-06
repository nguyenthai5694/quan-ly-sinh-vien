/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class GiangVien {
    private long id;
    private String code;
    private String name;
    private Date birthday;
    private String phone_number;
    private String address;
    private int gender;
    private String id_no;
    private Date id_issue_date;
    private String id_issue_place;
    private String place;
    private String[] listSubject;
    private String chg_who;
    private Date chg_date;
    private int status;

    public GiangVien() {
    }
    public GiangVien(GiangVien obj) {
        this.id = obj.getId();
        this.code = obj.getCode();
        this.name = obj.getName();
        this.birthday = obj.getBirthday();
        this.phone_number = obj.getPhone_number();
        this.address = obj.getAddress();
        this.gender = obj.getGender();
        this.id_no = obj.getId_no();
        this.id_issue_date = obj.getId_issue_date();
        this.id_issue_place = obj.getId_issue_place();
        this.place = obj.getPlace();
        this.listSubject = obj.getListSubject();
        this.chg_who = obj.getChg_who();
        this.chg_date = obj.getChg_date();
        this.status = obj.getStatus();
    }
    public GiangVien(long id, String code, String name, Date birthday, String phone_number, String address, int gender, String id_no, Date id_issue_date, String id_issue_place, String place, String code_khoa, String code_class, String chg_who, Date chg_date, int status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.birthday = birthday;
        this.phone_number = phone_number;
        this.address = address;
        this.gender = gender;
        this.id_no = id_no;
        this.id_issue_date = id_issue_date;
        this.id_issue_place = id_issue_place;
        this.place = place;
        this.chg_who = chg_who;
        this.chg_date = chg_date;
        this.status = status;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public Date getId_issue_date() {
        return id_issue_date;
    }

    public void setId_issue_date(Date id_issue_date) {
        this.id_issue_date = id_issue_date;
    }

    public String getId_issue_place() {
        return id_issue_place;
    }

    public void setId_issue_place(String id_issue_place) {
        this.id_issue_place = id_issue_place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getChg_who() {
        return chg_who;
    }

    public void setChg_who(String chg_who) {
        this.chg_who = chg_who;
    }

    public Date getChg_date() {
        return chg_date;
    }

    public void setChg_date(Date chg_date) {
        this.chg_date = chg_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String[] getListSubject() {
        return listSubject;
    }

    public void setListSubject(String[] listSubject) {
        this.listSubject = listSubject;
    }
    
}
