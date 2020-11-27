/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Khoa;
import entity.Lop;
import entity.SinhVien;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.KhoaModel;
import model.LopModel;
import model.SinhVienModel;
import util.ActionControllerInterface;
import util.ActionUtil;
import util.DateUtils;
import util.MessagesUtils;
import util.SessionUtils;

/**
 *
 * @author Admin
 */
@ManagedBean(name = "sinhVienController_old")
@ViewScoped
public class SinhVienController_old extends ActionUtil implements ActionControllerInterface, Serializable {

    private SinhVienModel svmodel;
    private KhoaModel khoamodel;
    private LopModel lopmodel;
    private SinhVien sv;
    private Lop lop;
    private Khoa khoa;
    private List<SinhVien> lstSinhVien;
    private List<Khoa> lstKhoa;
    private List<Lop> lstLop;
    private List<Lop> lstLopTheoKhoa;
    private Date sysdate;
    private String focus;

    private String khoaSelect;
    private String lopSelect;
    private Map<String, String> khoaSelects;
    private Map<String, String> lopSelects;
    private Map<String, Map<String, String>> data;

    public SinhVienController_old() {
        try {

            sysdate = new Date();
            focus = "name";
            svmodel = new SinhVienModel();
            khoamodel = new KhoaModel();
            lopmodel = new LopModel();
            sv = new SinhVien();
            khoa = new Khoa();
            lop = new Lop();
            lstSinhVien = new ArrayList<>();
            lstSinhVien = svmodel.getAllSinhVien();
            lstKhoa = new ArrayList<>();
            lstLop = new ArrayList<>();
            lstKhoa = khoamodel.getListKhoa();
            lstLop = lopmodel.getListLop();
//            khoaSelects = new HashMap<>();

        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    @Override
    public void save() {
        try {
            sv.setChg_who(LoginController.getUserLogin());
            sv.setChg_date(new Date());
            sv.setStatus(1);
            if (isAdd) {
                svmodel.add(sv);
                lstSinhVien.add(0, sv);
                MessagesUtils.info("", "Chúc mừng bạn đăng ký thành công !!!");
            } else if (isEdit) {
                svmodel.update(sv);
                MessagesUtils.info("", "Chúc mừng bạn đã sửa thành công !!!");
            } else if (isCopy) {
                svmodel.add(sv);
                lstSinhVien.add(0, sv);
            }
            handCancel();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void handDelete(SinhVien sinhvien) {
        try {
            String name = LoginController.getUserLogin();
            sinhvien.setChg_who(name);
            svmodel.remove(sinhvien);
            lstSinhVien.remove(sinhvien);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void onClick() {
        if (khoaSelect != null && !khoaSelect.equals("")) {
            lopSelects = data.get(khoaSelect);
        } else {
            lopSelects = new HashMap<String, String>();
        }
    }

    public void restartListSinhVien(String maKhoa, String maLop) {
        try {
            lstSinhVien = new ArrayList<>();
            lstSinhVien = svmodel.getListSinhVienTheoLop(this.lopSelect);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateAddSinhVien() {
        try {
            super.changeStateAdd();
            sv = new SinhVien();

        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateEditSinhVien(SinhVien sv) {
        try {
            super.changeStateEdit();
            this.sv = sv;

        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateCopySinhVien(SinhVien sv) {
        try {
            super.changeStateCopy();
            SinhVien sv1 = new SinhVien(sv.getId(), sv.getCode(), sv.getName(), sv.getBirthday(), sv.getPhone_number(), sv.getAddress(), sv.getGender(), sv.getId_no(), sv.getId_issue_date(), sv.getId_issue_place(), sv.getPlace(), sv.getCode_khoa(), sv.getCode_class(), sv.getChg_who(), sv.getChg_date(), sv.getStatus());
            this.sv = sv1;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateViewSinhVien(SinhVien sv) {
        try {
            super.changeStateView();
            this.sv = new SinhVien(sv.getId(), sv.getCode(), sv.getName(), sv.getBirthday(), sv.getPhone_number(), sv.getAddress(), sv.getGender(), sv.getId_no(), sv.getId_issue_date(), sv.getId_issue_place(), sv.getPlace(), sv.getCode_khoa(), sv.getCode_class(), sv.getChg_who(), sv.getChg_date(), sv.getStatus());
//            this.sv = sv;
            String s = sv.getCode_khoa();
            String v = sv.getCode_class();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }

    }

    public String getTenKhoa(int id) {
        try {
            if (lstKhoa != null && !lstKhoa.isEmpty()) {
                for (Khoa khoa : lstKhoa) {
                    if (khoa.getId() == id) {
                        return khoa.getTenKhoa();
                    }
                }
            } else {
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
        return "";
    }

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
    public void onCountryChange() throws Exception {
        if(khoaSelect !=null && !khoaSelect.equals("")){
            lstLop = lopmodel.getListLopTheoKhoa(khoaSelect);
            lstSinhVien = svmodel.getListSinhVienTheoKhoa(khoaSelect);
        }
        else {
            lstLop = lopmodel.getListLop();
            lstSinhVien = svmodel.getAllSinhVien();
        }
    }
    
    public void getListSVofCodeClass() throws Exception {
        if(lopSelect !=null && !lopSelect.equals("")){
            lstSinhVien = svmodel.getListSinhVienTheoLop(lopSelect);
        }
        else{
           lstSinhVien = svmodel.getAllSinhVien(); 
        }
    }
    public void getListLopofCodeKhoa() throws Exception {
        if(sv.getCode_khoa() !=null && !sv.getCode_khoa().equals("")){
            lstLop = lopmodel.getListLopTheoKhoa(sv.getCode_khoa());
        }
        else{
           lstLop = lopmodel.getListLop(); 
        }
    }
    public SinhVienModel getSvmodel() {
        return svmodel;
    }

    public void setSvmodel(SinhVienModel svmodel) {
        this.svmodel = svmodel;
    }

    public KhoaModel getKhoamodel() {
        return khoamodel;
    }

    public void setKhoamodel(KhoaModel khoamodel) {
        this.khoamodel = khoamodel;
    }

    public SinhVien getSv() {
        return sv;
    }

    public void setSv(SinhVien sv) {
        this.sv = sv;
    }

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public List<SinhVien> getLstSinhVien() {
        return lstSinhVien;
    }

    public void setLstSinhVien(List<SinhVien> lstSinhVien) {
        this.lstSinhVien = lstSinhVien;
    }

    public List<Khoa> getLstKhoa() {
        return lstKhoa;
    }

    public void setLstKhoa(List<Khoa> lstKhoa) {
        this.lstKhoa = lstKhoa;
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

    public List<Lop> getLstLop() {
        return lstLop;
    }

    public void setLstLop(List<Lop> lstLop) {
        this.lstLop = lstLop;
    }

    public List<Lop> getLstLopTheoKhoa() {
        return lstLopTheoKhoa;
    }

    public void setLstLopTheoKhoa(List<Lop> lstLopTheoKhoa) {
        this.lstLopTheoKhoa = lstLopTheoKhoa;
    }

    public String getKhoaSelect() {
        return khoaSelect;
    }

    public void setKhoaSelect(String khoaSelect) {
        this.khoaSelect = khoaSelect;
    }

    public String getLopSelect() {
        return lopSelect;
    }

    public void setLopSelect(String lopSelect) {
        this.lopSelect = lopSelect;
    }

    public Map<String, String> getLopSelects() {
        return lopSelects;
    }

    public void setLopSelects(Map<String, String> lopSelects) {
        this.lopSelects = lopSelects;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public LopModel getLopmodel() {
        return lopmodel;
    }

    public void setLopmodel(LopModel lopmodel) {
        this.lopmodel = lopmodel;
    }

    public Map<String, String> getKhoaSelects() {
        return khoaSelects;
    }

    public void setKhoaSelects(Map<String, String> khoaSelects) {
        this.khoaSelects = khoaSelects;
    }

}
