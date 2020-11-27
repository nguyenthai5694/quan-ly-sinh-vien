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
public class HocKy implements Serializable{
    private long id;
    private String code;
    private Date start_Date;
    private Date end_Date;

    public HocKy() {
    }

    public HocKy(HocKy obj) {
        this.id = obj.getId();
        this.code = obj.getCode();
        this.start_Date = obj.getStart_Date();
        this.end_Date = obj.getEnd_Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStart_Date() {
        return start_Date;
    }

    public void setStart_Date(Date start_Date) {
        this.start_Date = start_Date;
    }

    public Date getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(Date end_Date) {
        this.end_Date = end_Date;
    }
    
}
