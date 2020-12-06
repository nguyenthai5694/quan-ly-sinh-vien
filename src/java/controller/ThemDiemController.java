/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.HocKy;
import entity.Khoa;
import entity.Lop;
import entity.MonHoc;
import entity.SinhVien;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.DiemDanhModel;
import model.HocKyModel;
import model.HocPhiModel;
import model.KhoaModel;
import model.LoginModel;
import model.LopModel;
import model.SinhVienModel;
import model.ThemDiemModel;
import util.ActionUtil;
import util.DateUtils;
import util.MessagesUtils;

/**
 *
 * @author gia nguyen
 */
@ManagedBean
@ViewScoped
public class ThemDiemController extends ActionUtil {

    private SinhVienModel svmodel;
    private LopModel lopmodel;
    private SinhVien sv;
    private Lop lop;
    private Lop lopSelected;
    private List<SinhVien> lstSinhVien;
    private List<SinhVien> lstSinhVien2;
    private List<MonHoc> lstMonHoc;
    private List<Lop> lstLop;
    private List<Lop> lstLopTheoKhoa;
    private KhoaModel khoaModel;
    private LoginModel loginModel;
    private DiemDanhModel diemDanhModel;
    private String hocKySelect;
    private List<HocKy> mlstHocKy;
    private HocKyModel hocKyModel;
    private ThemDiemModel themDiemModel;
    private String khoaSelect;
    private String lopSelect;
    private String monHocSelect;
    private List<Khoa> lstKhoa;
    private HocPhiModel hocPhiModel;
    /**
     * Creates a new instance of KhoaController
     */
    public ThemDiemController() {
        try {
            diemDanhModel = new DiemDanhModel();
            loginModel = new LoginModel();
            khoaModel = new KhoaModel();
            svmodel = new SinhVienModel();
            lopmodel = new LopModel();
            themDiemModel = new ThemDiemModel();
            sv = new SinhVien();
            lop = new Lop();
            lstKhoa = new ArrayList<>();
            lstKhoa = khoaModel.getListKhoa();
            lstMonHoc = new ArrayList<>();
            lstSinhVien = new ArrayList<>();
            lstSinhVien2 = new ArrayList<>();
            lstLop = new ArrayList<>();
            lstLop = lopmodel.getListLop();
            hocKyModel = new HocKyModel();
            mlstHocKy = new ArrayList<>();
            mlstHocKy = hocKyModel.getListHocKy();
            hocPhiModel = new HocPhiModel();
            
            for (HocKy mlstHocKy1 : mlstHocKy) {
                hocKySelect = mlstHocKy1.getCode();
            }
            if(loginModel.getDec(LoginController.getUserLogin()) == 2){
                lstMonHoc = khoaModel.getListMonHocTheoGiangVien(LoginController.getUserLogin());
            }else {
               lstMonHoc = khoaModel.getListMonHoc(); 
            }
            lstSinhVien2 = themDiemModel.getListSinhVienTheoHK(hocKySelect);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void handOK() {
        try {
            if(monHocSelect != null && !monHocSelect.equals("")){
                themDiemModel.updateDiem(lstSinhVien, lopSelect, monHocSelect, hocKySelect);
                MessagesUtils.info("", "Thêm điểm thành công");
                handCancel();
            }else {
                MessagesUtils.error("", "Chưa chọn môn học");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void getListSVofCodeClass() throws Exception {
        if(lopSelect !=null && !lopSelect.equals("")){
            if(monHocSelect != null && !monHocSelect.equals("")){
                lstSinhVien = themDiemModel.getListSinhVienBangDiem(lopSelect,hocKySelect,monHocSelect);
            }
            else {
                MessagesUtils.error("", "Chưa chọn môn học");
            }
        }
        else {
            lstSinhVien.clear();
        }
    }
    public void onCountryChange() throws Exception {
        if(khoaSelect !=null && !khoaSelect.equals("")){
            lstLop = lopmodel.getListLopTheoKhoa(khoaSelect);
        }
        else {
            lstLop = lopmodel.getListLop();
        }
    }
    public int getTotalTC(String codeSV) throws Exception{
        return hocPhiModel.getTongTCTheoHK(codeSV, hocKySelect);
    }
    public double getDiemTBMH(String maSV, String maMH) throws Exception{
        return themDiemModel.getDiemTBMH(maSV, maMH, hocKySelect);
    }
    public double getTongDiemTBMH(String maSV) throws Exception{
        double total = 0;
        for (String maMH : themDiemModel.getListMH(maSV, hocKySelect)) {
            total += themDiemModel.getDiemTBMH(maSV, maMH, hocKySelect)*hocPhiModel.getTC(maMH);
        }
        return total/getTotalTC(maSV);
    }
   

    public HocPhiModel getHocPhiModel() {
        return hocPhiModel;
    }
    

    public void setHocPhiModel(HocPhiModel hocPhiModel) {
        this.hocPhiModel = hocPhiModel;
    }

    
    public KhoaModel getKhoaModel() {
        return khoaModel;
    }

    public void setKhoaModel(KhoaModel khoaModel) {
        this.khoaModel = khoaModel;
    }

    public List<Khoa> getLstKhoa() {
        return lstKhoa;
    }

    public void setLstKhoa(List<Khoa> lstKhoa) {
        this.lstKhoa = lstKhoa;
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

    public DiemDanhModel getDiemDanhModel() {
        return diemDanhModel;
    }

    public void setDiemDanhModel(DiemDanhModel diemDanhModel) {
        this.diemDanhModel = diemDanhModel;
    }

    public String getHocKySelect() {
        return hocKySelect;
    }

    public void setHocKySelect(String hocKySelect) {
        this.hocKySelect = hocKySelect;
    }

    public List<HocKy> getMlstHocKy() {
        return mlstHocKy;
    }

    public void setMlstHocKy(List<HocKy> mlstHocKy) {
        this.mlstHocKy = mlstHocKy;
    }

    public HocKyModel getHocKyModel() {
        return hocKyModel;
    }

    public void setHocKyModel(HocKyModel hocKyModel) {
        this.hocKyModel = hocKyModel;
    }

    public ThemDiemModel getThemDiemModel() {
        return themDiemModel;
    }

    public void setThemDiemModel(ThemDiemModel themDiemModel) {
        this.themDiemModel = themDiemModel;
    }

    public String getKhoaSelect() {
        return khoaSelect;
    }

    public void setKhoaSelect(String khoaSelect) {
        this.khoaSelect = khoaSelect;
    }

    public List<SinhVien> getLstSinhVien2() {
        return lstSinhVien2;
    }

    public void setLstSinhVien2(List<SinhVien> lstSinhVien2) {
        this.lstSinhVien2 = lstSinhVien2;
    }

   
}
