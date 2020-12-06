/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.GiangVien_old;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import model.GiangVienModel_old;
import model.LoginModel;
import util.ActionUtil;
import util.DateUtils;
import util.MessagesUtils;
import util.SessionUtils;

/**
 *
 * @author ADMIN
 */
@ManagedBean
@ViewScoped
public class GiangVienController_old extends ActionUtil{
    private GiangVien_old giangVien;
    private List<GiangVien_old> mlstGiangVien;
    private LoginModel loginModel;
    private GiangVienModel_old giangVienModel;

    public GiangVienModel_old getGiangVienModel() {
        return giangVienModel;
    }

    public void setGiangVienModel(GiangVienModel_old giangVienModel) {
        this.giangVienModel = giangVienModel;
    }
    
    public GiangVienController_old() {
        try {
            giangVien = new GiangVien_old();
            giangVienModel = new GiangVienModel_old();
            mlstGiangVien = new ArrayList<>();
            mlstGiangVien = giangVienModel.getListGiangVien();
        } catch (Exception e) {
            MessagesUtils.error("", e.toString());
        }
    }
    
    public void hanOK(){
        try {
            SessionUtils.getRequest();
            HttpSession session = SessionUtils.getSession();
            String giangVienLogin = (String) session.getAttribute("username");
            giangVien.setChgWho(giangVienLogin);
            giangVien.setChgDate(new Date());
            giangVien.setStatus(1);
            if(isAdd){
                giangVienModel.add(giangVien);
                mlstGiangVien.add(0, giangVien);
                MessagesUtils.info("", "Thêm thành công");
            }else if(isEdit){
                giangVienModel.updateGiangVien(giangVien);
                MessagesUtils.info("", "Sửa Thành Công");
            }else if(isCopy){
                giangVienModel.add(giangVien);
                mlstGiangVien.add(0, giangVien);
                MessagesUtils.info("", "Copy Thành Công");
            }
            
            handCancel();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    
    public String getNgaySinhView(Date ngaySinh) throws Exception{
        try {
            if(ngaySinh !=null){
            return DateUtils.convertDate(ngaySinh, "dd/MM/YYYY");
        }else{
            return "";
        }
        } catch (Exception e) {
        }
        return "";
    }

    
    @Override
    public void changeStateAdd(){
        try {
            super.changeStateAdd();
            giangVien = new GiangVien_old();
        } catch (Exception e) {
            MessagesUtils.error("", e.toString());
        }
    }
    public void changeStateEdit(GiangVien_old giangVien){
        try {
            super.changeStateEdit();
            this.giangVien = giangVien;
        } catch (Exception e) {
            MessagesUtils.error("", e.toString());
        }
    }
    public void changeStateView(GiangVien_old giangVien){
        try {
            super.changeStateView();
            this.giangVien = giangVien;
        } catch (Exception e) {
            MessagesUtils.error("", e.toString());
        }
    }
    
    
    
    public void delete(GiangVien_old giangVien){
        try {
            giangVienModel.delete(giangVien);
            mlstGiangVien.remove(giangVien);
        } catch (Exception e) {
            MessagesUtils.error("", e.toString());
        }
    }
    
       
    public GiangVien_old getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(GiangVien_old giangVien) {
        this.giangVien = giangVien;
    }

    public List<GiangVien_old> getMlstGiangVien() {
        return mlstGiangVien;
    }

    public void setMlstGiangVien(List<GiangVien_old> mlstGiangVien) {
        this.mlstGiangVien = mlstGiangVien;
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    /**
     * Creates a new instance of GiangVienController
     */

    
}
