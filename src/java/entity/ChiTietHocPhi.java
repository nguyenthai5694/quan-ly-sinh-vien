/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class ChiTietHocPhi implements Serializable{
    private String codeSV;
    private String codeSubject;
    private String nameSubject;
    private int tinChi;
    private int hocPhi;
    public ChiTietHocPhi() {
    }

    public String getCodeSV() {
        return codeSV;
    }

    public void setCodeSV(String codeSV) {
        this.codeSV = codeSV;
    }

    public String getCodeSubject() {
        return codeSubject;
    }

    public void setCodeSubject(String codeSubject) {
        this.codeSubject = codeSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public int getTinChi() {
        return tinChi;
    }

    public void setTinChi(int tinChi) {
        this.tinChi = tinChi;
    }

    public int getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(int hocPhi) {
        this.hocPhi = hocPhi;
    }

    
}
