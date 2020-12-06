/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.GiangVien;
import entity.MonHoc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.GiangVienModel;
import util.ActionUtil;
import util.DateUtils;
import util.MessagesUtils;
import util.SessionUtils;

/**
 *
 * @author gia nguyen
 */
@ManagedBean
@ViewScoped
public class GiangVienController extends ActionUtil {
    private GiangVien giangVien;
    private GiangVienModel giangVienModel;
    private List<GiangVien> listGiangVien;
    private List<MonHoc> listMonHoc;
    private Date sysdate;
    private String focus;
    private String monHocSelect;
    /**
     * Creates a new instance of KhoaController
     */
    public GiangVienController() {
        try {
            sysdate = new Date();
            focus = "name";
            giangVienModel = new GiangVienModel();
            listGiangVien = new ArrayList<>();
            listMonHoc = new ArrayList<>();
            listGiangVien = giangVienModel.getAllGiangVien();
            for (GiangVien listGiangVien1 : listGiangVien) {
                List<String> listMH = new ArrayList<>();
                listMH = giangVienModel.getListMonHoc(listGiangVien1.getCode());
                String[] stringArray = new String[listMH.size()];
                stringArray = listMH.toArray(stringArray);
                listGiangVien1.setListSubject(stringArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    public String getStringLisstMonHoc(String[] stringArray) throws Exception{
        String returnString = "";
        if(stringArray.length != 0) {
            returnString = giangVienModel.getTenMonHocTheoMa(stringArray[0]);
            for (int i = 1; i < stringArray.length; i++) {
                returnString += ", " + giangVienModel.getTenMonHocTheoMa(stringArray[i]);
            }
        }
        return returnString;
    }

    public void handOK() {
        try {
           giangVien.setChg_who(LoginController.getUserLogin());
            giangVien.setChg_date(new Date());
            giangVien.setStatus(1);
            if (isAdd) {
                giangVienModel.add(giangVien);
                listGiangVien.add(0, giangVien);
                MessagesUtils.info("", "Chúc mừng bạn đăng ký thành công !!!");
            } else if (isEdit) {
                giangVienModel.update(giangVien);
                MessagesUtils.info("", "Chúc mừng bạn đã sửa thành công !!!");
            } else if (isCopy) {
                giangVienModel.add(giangVien);
                listGiangVien.add(0, giangVien);
                MessagesUtils.info("", "Chúc mừng bạn đăng ký thành công !!!");
            }
            handCancel();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void handDelete(GiangVien giangVien) {
        try {
            String name = LoginController.getUserLogin();
            giangVien.setChg_who(name);
            giangVienModel.remove(giangVien);
            listGiangVien.remove(giangVien);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateAddGiangVien() {
        try {
            super.changeStateAdd();
            giangVien = new GiangVien();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    public void changeStateEditGiangVien(GiangVien giangVien) {
        try {
            super.changeStateEdit();
            this.giangVien = giangVien;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    public void changeStateViewGiangVien(GiangVien giangVien) {
        try {
            super.changeStateView();
            this.giangVien = giangVien;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    
    public void changeStateCopySinhVien(GiangVien giangVien) {
        try {
            super.changeStateCopy();
            this.giangVien = new GiangVien(giangVien);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    
//    public String getTenKhoa(String code) {
//        try {
//            if (lstKhoa != null && !lstKhoa.isEmpty()) {
//                for (Khoa khoa : lstKhoa) {
//                    if (khoa.getMaKhoa().equals(code)) {
//                        return khoa.getTenKhoa();
//                    }
//                }
//            } else {
//                return "";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            MessagesUtils.error("", e.toString());
//        }
//        return "";
//    }
//    public String getTenLop(String code) {
//        try {
//            if (lstLop != null && !lstLop.isEmpty()) {
//                for (Lop lop : lstLop) {
//                    if (lop.getMaLop().equals(code)) {
//                        return lop.getTenLop();
//                    }
//                }
//            } else {
//                return "";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            MessagesUtils.error("", e.toString());
//        }
//        return "";
//    }

    public String getNgaySinhView(Date dtNgaySinh) {
        try {
            if (dtNgaySinh != null) {
                return DateUtils.convertDate(dtNgaySinh, "dd/MM/yyyy");
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
        return "";
    }
//    public void onCountryChange() throws Exception {
//        if(khoaSelect !=null && !khoaSelect.equals("")){
//            lstLop = lopmodel.getListLopTheoKhoa(khoaSelect);
//            lstSinhVien = svmodel.getListSinhVienTheoKhoa(khoaSelect);
//        }
//        else {
//            lstLop = lopmodel.getListLop();
//            lstSinhVien = svmodel.getAllSinhVien();
//        }
//    }
//    public void onLopChange() throws Exception {
//        if(sv.getCode_khoa() !=null && !sv.getCode_khoa().equals("")){
//            lstLop = lopmodel.getListLopTheoKhoa(sv.getCode_khoa());
//        }
//        else {
//            lstLop = lopmodel.getListLop();
//        }
//    }
//    public void onMonHoc() throws Exception{
//        if (monHocSelect !=null && monHocSelect.equals("")) {
//            lstLop = lopmodel.getListLopTheoKhoa(monHocSelect);
//            lstSinhVien = svmodel.getListSinhVienTheoKhoa(monHocSelect);
//            
//        } else {
//            lstLop = lopmodel.getListLop();
//            lstSinhVien = svmodel.getAllSinhVien();
//        }
//    }
//    
//    public void getListSVofCodeClass() throws Exception {
//        if(lopSelect !=null && !lopSelect.equals("")){
//            lstSinhVien = svmodel.getListSinhVienTheoLop(lopSelect);
//        }
//        else {
//            onCountryChange();
//        }
//    }
//    public void getListLopofCodeKhoa() throws Exception {
//        if(sv.getCode_khoa() !=null && !sv.getCode_khoa().equals("")){
//            lstLop = lopmodel.getListLopTheoKhoa(sv.getCode_khoa());
//        }
//        else{
//           lstLop = lopmodel.getListLop(); 
//        }
//    }
    
    
    public String getNgayView(Date dtNgay) {
        try {
            if (dtNgay != null) {
                return DateUtils.convertDate(dtNgay, "dd/MM/yyyy");
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
        return "";
    }

    public GiangVienModel getGiangVienModel() {
        return giangVienModel;
    }

    public void setGiangVienModel(GiangVienModel giangVienModel) {
        this.giangVienModel = giangVienModel;
    }

    public List<GiangVien> getListGiangVien() {
        return listGiangVien;
    }

    public void setListGiangVien(List<GiangVien> listGiangVien) {
        this.listGiangVien = listGiangVien;
    }

    public List<MonHoc> getListMonHoc() {
        return listMonHoc;
    }

    public void setListMonHoc(List<MonHoc> listMonHoc) {
        this.listMonHoc = listMonHoc;
    }

    public Date getSysdate() {
        return sysdate;
    }

    public void setSysdate(Date sysdate) {
        this.sysdate = sysdate;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getMonHocSelect() {
        return monHocSelect;
    }

    public void setMonHocSelect(String monHocSelect) {
        this.monHocSelect = monHocSelect;
    }

    public GiangVien getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }

   

   
}
