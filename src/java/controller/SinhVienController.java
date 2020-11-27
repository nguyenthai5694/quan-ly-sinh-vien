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
import model.HocKyModel;
import model.KhoaModel;
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
public class SinhVienController extends ActionUtil {

    private SinhVienModel svmodel;
    private KhoaModel khoamodel;
    private LopModel lopmodel;
    private SinhVien sv;
    private Lop lop;
    private Khoa khoa;
    private Lop lopSelected;
    private List<SinhVien> lstSinhVien;
    private List<MonHoc> lstMonHoc;
    private List<Khoa> lstKhoa;
    private List<Lop> lstLop;
    private List<Lop> lstLopTheoKhoa;
    private Date sysdate;
    private String focus;

    private String khoaSelect;
    private String lopSelect;
    private String monHocSelect;
    /**
     * Creates a new instance of KhoaController
     */
    public SinhVienController() {
        try {
            sysdate = new Date();
            focus = "name";
            svmodel = new SinhVienModel();
            khoamodel = new KhoaModel();
            lopmodel = new LopModel();
            sv = new SinhVien();
            khoa = new Khoa();
            lop = new Lop();
            lstMonHoc = new ArrayList<>();
            lstMonHoc = khoamodel.getListMonHoc();
            lstSinhVien = new ArrayList<>();
            lstSinhVien = svmodel.getAllSinhVien();
            lstKhoa = new ArrayList<>();
            lstLop = new ArrayList<>();
            lstKhoa = khoamodel.getListKhoa();
            lstLop = lopmodel.getListLop();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void handOK() {
        try {
           sv.setChg_who(LoginController.getUserLogin());
            sv.setChg_date(new Date());
            sv.setStatus(1);
            if (isAdd) {
                sv.setCode_khoa(khoaSelect);
                sv.setCode_class(lopSelect);
                svmodel.add(sv);
                lstSinhVien.add(0, sv);
                MessagesUtils.info("", "Chúc mừng bạn đăng ký thành công !!!");
            } else if (isEdit) {
                svmodel.update(sv);
                MessagesUtils.info("", "Chúc mừng bạn đã sửa thành công !!!");
            } else if (isCopy) {
                svmodel.add(sv);
                lstSinhVien.add(0, sv);
                MessagesUtils.info("", "Chúc mừng bạn đăng ký thành công !!!");
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

    public void restartListSinhVien(String maKhoa, String maLop) {
        try {
            lstSinhVien = new ArrayList<>();
            lstSinhVien = svmodel.getListSinhVienTheoLop(this.lopSelect);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    public void onRowSelectSinhVienByLop(SelectEvent<Lop> event) throws Exception{
        try {
            String maLop = event.getObject().getMaLop();
            lstSinhVien = svmodel.getListSinhVienTheoLop(event.getObject().getMaLop());
            sv.setId(event.getObject().getId());
            sv.setCode_class(event.getObject().getMaLop());
            sv = new SinhVien();
            ;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    public void changeStateAddSinhVien() {
        try {
            super.changeStateAdd();
            sv = new SinhVien();
            sv.setCode_khoa(khoaSelect);
            sv.setCode_class(lopSelect);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    public void changeStateEditSinhVien(SinhVien sinhVien) {
        try {
            super.changeStateEdit();
            this.sv = sinhVien;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    public void changeStateViewSinhVien(SinhVien sinhVien) {
        try {
            super.changeStateView();
            this.sv = sinhVien;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    
    public void changeStateCopySinhVien(SinhVien sinhVien) {
        try {
            super.changeStateCopy();
            this.sv = new SinhVien(sinhVien);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    
    public String getTenKhoa(String code) {
        try {
            if (lstKhoa != null && !lstKhoa.isEmpty()) {
                for (Khoa khoa : lstKhoa) {
                    if (khoa.getMaKhoa().equals(code)) {
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
    public String getTenLop(String code) {
        try {
            if (lstLop != null && !lstLop.isEmpty()) {
                for (Lop lop : lstLop) {
                    if (lop.getMaLop().equals(code)) {
                        return lop.getTenLop();
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
    public void onLopChange() throws Exception {
        if(sv.getCode_khoa() !=null && !sv.getCode_khoa().equals("")){
            lstLop = lopmodel.getListLopTheoKhoa(sv.getCode_khoa());
        }
        else {
            lstLop = lopmodel.getListLop();
        }
    }
    public void onMonHoc() throws Exception{
        if (monHocSelect !=null && monHocSelect.equals("")) {
            lstLop = lopmodel.getListLopTheoKhoa(monHocSelect);
            lstSinhVien = svmodel.getListSinhVienTheoKhoa(monHocSelect);
            
        } else {
            lstLop = lopmodel.getListLop();
            lstSinhVien = svmodel.getAllSinhVien();
        }
    }
    
    public void getListSVofCodeClass() throws Exception {
        if(lopSelect !=null && !lopSelect.equals("")){
            lstSinhVien = svmodel.getListSinhVienTheoLop(lopSelect);
        }
        else {
            onCountryChange();
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

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
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

    public List<Lop> getLstLopTheoKhoa() {
        return lstLopTheoKhoa;
    }

    public void setLstLopTheoKhoa(List<Lop> lstLopTheoKhoa) {
        this.lstLopTheoKhoa = lstLopTheoKhoa;
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

    public String getMonHocSelect() {
        return monHocSelect;
    }

    public void setMonHocSelect(String monHocSelect) {
        this.monHocSelect = monHocSelect;
    }


   
}
