/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.ChiTietHocPhi;
import entity.ChuongTrinhHoc;
import entity.HocKy;
import entity.HocPhi;
import entity.Khoa;
import entity.Lop;
import entity.SinhVien;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import model.ChuongTrinhHocModel;
import model.HocKyModel;
import model.HocPhiModel;
import model.KhoaModel;
import model.LopModel;
import model.SinhVienModel;
import util.ActionUtil;
import util.MessagesUtils;
import util.SessionUtils;



/**
 *
 * @author gia nguyen
 */
@ManagedBean
@ViewScoped
public class HocPhiController extends ActionUtil{
    private KhoaModel khoamodel;
    private LopModel lopmodel;
    private HocPhi hocPhi;
    private List<HocPhi> mlstHocPhi;
    private HocPhiModel hocPhiModel;
//    private SinhVienController sinhVienController;
    private String hocKySelect;
    private SinhVienModel sinhVienModel;
    private LopModel lopModel;
    private ActionUtil actionUtil; 
    private List<HocKy> mlstHocKy;
    private HocKyModel hocKyModel;
    private List<Khoa> lstKhoa;
    private List<Lop> lstLop;
    private String khoaSelect;
    private String lopSelect;
    private ChuongTrinhHoc chuongTrinhHoc;
    private ChuongTrinhHocModel chuongTrinhHocModel;
    private ChiTietHocPhi chiTietHocPhi;
    private List<ChiTietHocPhi> listChiTietHocPhi;
    private SinhVien sinhVien;
    private int tongHocPhi;
    /**
     * Creates a new instance of HocPhiController
     */
    public HocPhiController() {
        try {
            khoamodel = new KhoaModel();
            lopmodel = new LopModel();
//            sinhVienController = new SinhVienController();
            sinhVienModel = new SinhVienModel();
            hocPhiModel = new HocPhiModel();
            hocPhi = new HocPhi();
            mlstHocPhi = new ArrayList<>();
            lstKhoa = new ArrayList<>();
            lstLop = new ArrayList<>();
            lstKhoa = khoamodel.getListKhoa();
            lstLop = lopmodel.getListLop();
            actionUtil = new ActionUtil();
            hocKyModel = new HocKyModel();
            mlstHocKy = new ArrayList<>();
            mlstHocKy = hocKyModel.getListHocKy();
            hocKySelect = "";
            chuongTrinhHoc = new ChuongTrinhHoc();
            chuongTrinhHocModel = new ChuongTrinhHocModel();
            sinhVien = new SinhVien();
            chiTietHocPhi = new ChiTietHocPhi();
            listChiTietHocPhi = new ArrayList<>();
            tongHocPhi = 0;
            for (HocKy mlstHocKy1 : mlstHocKy) {
                hocKySelect = mlstHocKy1.getCode();
            }
            mlstHocPhi = hocPhiModel.getListHocPhi(hocKySelect);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
            
    }
    public void handOK() {
        try {
            SessionUtils.getRequest();
            HttpSession session = SessionUtils.getSession();
            String HocPhiLogin = (String) session.getAttribute("username");
            handCancel();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateViewHocPhi(HocPhi hocPhi) {
        try {
            super.changeStateView();
            sinhVien = new SinhVien(sinhVienModel.getSVTheoMaSinhVien(hocPhi.getCodeStudent()));
            sinhVien.setName_Lop(khoamodel.getTenLopTheoMaLop(sinhVien.getCode_class()));
            List<String> listMonHoc = new ArrayList<>(chuongTrinhHocModel.getListMonHoc(hocKySelect, sinhVien.getCode_khoa()));
            for (String listMonHoc1 : listMonHoc) {
                chiTietHocPhi = khoamodel.getChiTietHocPhi(listMonHoc1, sinhVien.getCode());
                listChiTietHocPhi.add(chiTietHocPhi);
                tongHocPhi += chiTietHocPhi.getHocPhi();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void dongHocPhi(HocPhi hocPhi) {
        try {
            hocPhiModel.dongHocPhi(hocPhi);
            MessagesUtils.info("", "Đã đóng học phí thành công");
            getListSVofCodeHK();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    public void onCountryChange() throws Exception {
        if(khoaSelect !=null && !khoaSelect.equals("")){
            lstLop = lopmodel.getListLopTheoKhoa(khoaSelect);
            mlstHocPhi = hocPhiModel.getListHocPhiTheoKhoa(hocKySelect, khoaSelect);
        }
        else {
            lstLop = lopmodel.getListLop();
            mlstHocPhi = hocPhiModel.getListHocPhi(hocKySelect);
        }
    }
    public void getListSVofCodeClass() throws Exception {
        if(lopSelect !=null && !lopSelect.equals("")){
            mlstHocPhi = hocPhiModel.getListHocPhiTheoLop(hocKySelect, lopSelect);
        }
        else if(khoaSelect !=null && !khoaSelect.equals("")){
            mlstHocPhi = hocPhiModel.getListHocPhiTheoKhoa(hocKySelect, khoaSelect);
        }else{
           mlstHocPhi = hocPhiModel.getListHocPhi(hocKySelect);
        }
    }
    public void getListSVofCodeHK() throws Exception {
        if(lopSelect !=null && !lopSelect.equals("")){
            mlstHocPhi = hocPhiModel.getListHocPhiTheoLop(hocKySelect, lopSelect);
        }
        else if(khoaSelect !=null && !khoaSelect.equals("")){
            mlstHocPhi = hocPhiModel.getListHocPhiTheoKhoa(hocKySelect, khoaSelect);
        }else{
           mlstHocPhi = hocPhiModel.getListHocPhi(hocKySelect);
        }
    }

    public int getTongHocPhi() {
        return tongHocPhi;
    }

    public void setTongHocPhi(int tongHocPhi) {
        this.tongHocPhi = tongHocPhi;
    }

    public ChiTietHocPhi getChiTietHocPhi() {
        return chiTietHocPhi;
    }

    public void setChiTietHocPhi(ChiTietHocPhi chiTietHocPhi) {
        this.chiTietHocPhi = chiTietHocPhi;
    }

    public List<ChiTietHocPhi> getListChiTietHocPhi() {
        return listChiTietHocPhi;
    }

    public void setListChiTietHocPhi(List<ChiTietHocPhi> listChiTietHocPhi) {
        this.listChiTietHocPhi = listChiTietHocPhi;
    }

    public ChuongTrinhHoc getChuongTrinhHoc() {
        return chuongTrinhHoc;
    }

    public void setChuongTrinhHoc(ChuongTrinhHoc chuongTrinhHoc) {
        this.chuongTrinhHoc = chuongTrinhHoc;
    }

    public ChuongTrinhHocModel getChuongTrinhHocModel() {
        return chuongTrinhHocModel;
    }

    public void setChuongTrinhHocModel(ChuongTrinhHocModel chuongTrinhHocModel) {
        this.chuongTrinhHocModel = chuongTrinhHocModel;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }
    
    public HocPhi getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(HocPhi hocPhi) {
        this.hocPhi = hocPhi;
    }

    public List<HocPhi> getMlstHocPhi() {
        return mlstHocPhi;
    }

    public void setMlstHocPhi(List<HocPhi> mlstHocPhi) {
        this.mlstHocPhi = mlstHocPhi;
    }

    public HocPhiModel getHocPhiModel() {
        return hocPhiModel;
    }

    public void setHocPhiModel(HocPhiModel hocPhiModel) {
        this.hocPhiModel = hocPhiModel;
    }

    public KhoaModel getKhoamodel() {
        return khoamodel;
    }

    public void setKhoamodel(KhoaModel khoamodel) {
        this.khoamodel = khoamodel;
    }

    public LopModel getLopmodel() {
        return lopmodel;
    }

    public void setLopmodel(LopModel lopmodel) {
        this.lopmodel = lopmodel;
    }

    public List<Khoa> getLstKhoa() {
        return lstKhoa;
    }

    public void setLstKhoa(List<Khoa> lstKhoa) {
        this.lstKhoa = lstKhoa;
    }

    public List<Lop> getLstLop() {
        return lstLop;
    }

    public void setLstLop(List<Lop> lstLop) {
        this.lstLop = lstLop;
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

    

    public String getHocKySelect() {
        return hocKySelect;
    }

    public void setHocKySelect(String hocKySelect) {
        this.hocKySelect = hocKySelect;
    }

    public SinhVienModel getSinhVienModel() {
        return sinhVienModel;
    }

    public void setSinhVienModel(SinhVienModel sinhVienModel) {
        this.sinhVienModel = sinhVienModel;
    }

    public LopModel getLopModel() {
        return lopModel;
    }

    public void setLopModel(LopModel lopModel) {
        this.lopModel = lopModel;
    }

    public ActionUtil getActionUtil() {
        return actionUtil;
    }

    public void setActionUtil(ActionUtil actionUtil) {
        this.actionUtil = actionUtil;
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

}
