/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.ChuongTrinhHoc;
import entity.HocKy;
import entity.Khoa;
import entity.Lop;
import entity.MonHoc;
import entity.SinhVien;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.ChuongTrinhHocModel;
import model.DiemDanhModel;
import model.HocKyModel;
import model.KhoaModel;
import model.LoginModel;
import model.LopModel;
import model.SinhVienModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
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
public class DiemDanhController extends ActionUtil {

    private SinhVienModel svmodel;
    private LopModel lopmodel;
    private SinhVien sv;
    private Lop lop;
    private Lop lopSelected;
    private List<SinhVien> lstSinhVien;
    private List<MonHoc> lstMonHoc;
    private List<Lop> lstLop;
    private List<Lop> lstLopTheoKhoa;
    private KhoaModel khoaModel;
    private LoginModel loginModel;
    private DiemDanhModel diemDanhModel;

    private String lopSelect;
    private String monHocSelect;
    /**
     * Creates a new instance of KhoaController
     */
    public DiemDanhController() {
        try {
            diemDanhModel = new DiemDanhModel();
            loginModel = new LoginModel();
            khoaModel = new KhoaModel();
            svmodel = new SinhVienModel();
            lopmodel = new LopModel();
            sv = new SinhVien();
            lop = new Lop();
            lstMonHoc = new ArrayList<>();
            lstSinhVien = new ArrayList<>();
            lstLop = new ArrayList<>();
            lstLop = lopmodel.getListLop();
            if(loginModel.getDec(LoginController.getUserLogin()) == 2){
                lstMonHoc = khoaModel.getListMonHocTheoGiangVien(LoginController.getUserLogin());
            }else {
               lstMonHoc = khoaModel.getListMonHoc(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void handOK() {
        try {
            diemDanhModel.addDiemDanh(lstSinhVien, lopSelect, monHocSelect);
            MessagesUtils.error("", "Điểm danh thành công");
            handCancel();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void onLopChange() throws Exception {
        if(sv.getCode_khoa() !=null && !sv.getCode_khoa().equals("")){
            lstLop = lopmodel.getListLopTheoKhoa(sv.getCode_khoa());
        }
        else {
            lstLop = lopmodel.getListLop();
        }
    }

    public void getListSVofCodeClass() throws Exception {
        if(lopSelect !=null && !lopSelect.equals("")){
            lstSinhVien = svmodel.getListSinhVienTheoLop(lopSelect);
        }
        else {
            lstSinhVien.clear();
        }
    }
    
    
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

    
    public KhoaModel getKhoaModel() {
        return khoaModel;
    }

    public void setKhoaModel(KhoaModel khoaModel) {
        this.khoaModel = khoaModel;
    }


    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public SinhVienModel getSvmodel() {
        return svmodel;
    }

    public void setSvmodel(SinhVienModel svmodel) {
        this.svmodel = svmodel;
    }


    public LopModel getLopmodel() {
        return lopmodel;
    }

    public void setLopmodel(LopModel lopmodel) {
        this.lopmodel = lopmodel;
    }

    public SinhVien getSv() {
        return sv;
    }

    public void setSv(SinhVien sv) {
        this.sv = sv;
    }

    public Lop getLopSelected() {
        return lopSelected;
    }

    public void setLopSelected(Lop lopSelected) {
        this.lopSelected = lopSelected;
    }

    public List<SinhVien> getLstSinhVien() {
        return lstSinhVien;
    }

    public void setLstSinhVien(List<SinhVien> lstSinhVien) {
        this.lstSinhVien = lstSinhVien;
    }

    public List<MonHoc> getLstMonHoc() {
        return lstMonHoc;
    }

    public void setLstMonHoc(List<MonHoc> lstMonHoc) {
        this.lstMonHoc = lstMonHoc;
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

    public String getLopSelect() {
        return lopSelect;
    }

    public void setLopSelect(String lopSelect) {
        this.lopSelect = lopSelect;
    }

    public String getMonHocSelect() {
        return monHocSelect;
    }

    public void setMonHocSelect(String monHocSelect) {
        this.monHocSelect = monHocSelect;
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

   
}
