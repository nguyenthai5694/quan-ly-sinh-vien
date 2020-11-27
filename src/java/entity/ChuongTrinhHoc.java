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
public class ChuongTrinhHoc implements Serializable{
    private long id;
    private String codeKhoa;
    private String codeHK;
    private String[] codeSubjects;

    public ChuongTrinhHoc() {
    }

    public ChuongTrinhHoc(ChuongTrinhHoc obj) {
        this.id = obj.getId();
        this.codeKhoa = obj.getCodeKhoa();
        this.codeHK = obj.getCodeHK();
        this.codeSubjects = obj.getCodeSubjects();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodeKhoa() {
        return codeKhoa;
    }

    public void setCodeKhoa(String codeKhoa) {
        this.codeKhoa = codeKhoa;
    }

    public String getCodeHK() {
        return codeHK;
    }

    public void setCodeHK(String codeHK) {
        this.codeHK = codeHK;
    }

    public String[] getCodeSubjects() {
        return codeSubjects;
    }

    public void setCodeSubjects(String[] codeSubjects) {
        this.codeSubjects = codeSubjects;
    }
    
    
    
}
