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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.ChuongTrinhHocModel;
import model.HocKyModel;
import model.KhoaModel;
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
public class KhoaController extends ActionUtil {

    private Khoa khoa;
    private Lop lop;
    private MonHoc monHoc;
    private List<Khoa> mlstKhoa;
    private List<Lop> mlstLop;
    private List<MonHoc> mlstMonHoc;
    private KhoaModel khoaModel;
    private boolean isKhoa;
    private boolean isLop;
    private boolean isMonHoc;
    private Khoa khoaSelected;
    private MonHoc monHocSelected;
    private HocKy hocKy;
    private List<HocKy> mlstHocKy;
    private HocKyModel hocKyModel;
    private boolean isHocKy;
    private boolean isChuongTrinhHoc;
    private ChuongTrinhHoc chuongTrinhHoc;
    private List<ChuongTrinhHoc> mlstChuongTrinhHoc;
    private ChuongTrinhHocModel chuongTrinhHocModel;
    private SinhVienController sinhVienController;

    /**
     * Creates a new instance of KhoaController
     */
    public KhoaController() {
        try {
            sinhVienController = new SinhVienController();
            isKhoa = false;
            isLop = false;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = false;
            khoaModel = new KhoaModel();
            khoa = new Khoa();
            lop = new Lop();
            lop.setMaKhoa("0");
            monHoc = new MonHoc();
            mlstKhoa = new ArrayList<>();
            mlstLop = new ArrayList<>();
            mlstMonHoc = new ArrayList<>();
            mlstKhoa = khoaModel.getListKhoa();
            mlstMonHoc = khoaModel.getListMonHoc();
            hocKyModel = new HocKyModel();
            hocKy = new HocKy();
            mlstHocKy = new ArrayList<>();
            mlstHocKy = hocKyModel.getListHocKy();
            chuongTrinhHocModel = new ChuongTrinhHocModel();
            chuongTrinhHoc = new ChuongTrinhHoc();
            mlstChuongTrinhHoc = new ArrayList<>();
            mlstChuongTrinhHoc = chuongTrinhHocModel.getListChuongTrinhHoc();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void handOK() {
        try {
            SessionUtils.getRequest();
            HttpSession session = SessionUtils.getSession();
            String KhoaLogin = (String) session.getAttribute("username");
            khoa.setChg_Who(KhoaLogin);
            khoa.setChg_Date(new Date());
            khoa.setStatus("1");
            String LopLogin = (String) session.getAttribute("username");
            lop.setChg_Who(LopLogin);
            lop.setChg_Date(new Date());
            lop.setStatus("1");
            String MonHocLogin = (String) session.getAttribute("username");
            monHoc.setChg_Who(MonHocLogin);
            monHoc.setChg_Date(new Date());
            monHoc.setStatus("1");
            if (isAdd) {
                if (isKhoa) {
                    khoaModel.addKhoa(khoa);
                    mlstKhoa.add(0, khoa);
                    MessagesUtils.info("", "OK ADD!");
                }
                if (isLop) {
                    khoaModel.addLop(lop);
                    mlstLop.add(0, lop);
                    MessagesUtils.info("", "OK ADD!");
                }
                if (isMonHoc) {
                    khoaModel.addMonHoc(monHoc);
                    mlstMonHoc.add(0, monHoc);
                    MessagesUtils.info("", "OK ADD!");
                }
                if (isHocKy) {
                    hocKyModel.addHocKy(hocKy);
                    mlstHocKy.add(0, hocKy);
                    MessagesUtils.info("", "OK ADD!");
                }
                if (isChuongTrinhHoc) {
                    if(checkChuongTrinhHoc(chuongTrinhHoc.getCodeKhoa(), chuongTrinhHoc.getCodeHK())) {
                        chuongTrinhHocModel.addChuongTrinhHoc(chuongTrinhHoc);
                        mlstChuongTrinhHoc.add(0, chuongTrinhHoc);
                        MessagesUtils.info("", "OK ADD!");
                    }else {
                        MessagesUtils.error("", "Chương Trình Học đã tồn tại");
                    }
                    
                }
            } else if (isEdit) {
                if (isKhoa) {
                    khoaModel.updateKhoa(khoa);
                    MessagesUtils.info("", "OK EDIT!");
                }
                if (isLop) {
                    khoaModel.updateLop(lop);
                    MessagesUtils.info("", "OK EDIT!");
                }
                if (isMonHoc) {
                    khoaModel.updateMonHoc(monHoc);
                    MessagesUtils.info("", "OK EDIT!");
                }
                if (isHocKy) {
                    hocKyModel.updateHocKy(hocKy);
                    MessagesUtils.info("", "OK EDIT!");
                }
                if (isChuongTrinhHoc) {
                    chuongTrinhHocModel.updateChuongTrinhHoc(chuongTrinhHoc);
                    MessagesUtils.info("", "OK EDIT!");
                }
            } else if (isCopy) {
                if (isKhoa) {
                    khoaModel.addKhoa(khoa);
                    mlstKhoa.add(0, khoa);
                    MessagesUtils.info("", "OK COPY!");
                }
                if (isLop) {
                    khoaModel.addLop(lop);
                    mlstLop.add(0, lop);
                    MessagesUtils.info("", "OK COPY!");
                }
                if (isMonHoc) {
                    khoaModel.addMonHoc(monHoc);
                    mlstMonHoc.add(0, monHoc);
                    MessagesUtils.info("", "OK COPY!");
                }
                if (isHocKy) {
                    hocKyModel.addHocKy(hocKy);
                    mlstHocKy.add(0, hocKy);
                    MessagesUtils.info("", "OK COPY!");
                }
                if (isChuongTrinhHoc) {
                    chuongTrinhHocModel.addChuongTrinhHoc(chuongTrinhHoc);
                    mlstChuongTrinhHoc.add(0, chuongTrinhHoc);
                    MessagesUtils.info("", "OK COPY!");
                }
            } 
            handCancel();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    public boolean checkChuongTrinhHoc(String code_khoa, String code_hk) throws Exception{
        for (ChuongTrinhHoc chuongTrinhHoc : mlstChuongTrinhHoc) {
            if(chuongTrinhHoc.getCodeKhoa().equals(code_khoa) && chuongTrinhHoc.getCodeHK().equals(code_hk)){
                return false;
            }
        }
        return true;
    }
    public void changeStateAddKhoa() {
        try {
            super.changeStateAdd();
            isKhoa = true;
            isLop = false;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = false;
            khoa = new Khoa();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateEditKhoa(Khoa khoa) {
        try {
            super.changeStateEdit();
            isKhoa = true;
            isLop = false;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = false;
            this.khoa = khoa;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    

    public void changeStateCopyKhoa(Khoa khoa) {
        try {
            super.changeStateCopy();
            isKhoa = true;
            isLop = false;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = false;
            Khoa khoa1 = new Khoa(khoa);
            this.khoa = khoa1;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void chageStateViewKhoa(Khoa khoa) {
        try {
            super.changeStateView();
            isKhoa = true;
            isLop = false;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = false;
            this.khoa = khoa;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void deleteKhoa(Khoa khoa) {
        try {
            khoaModel.deleteKhoa(khoa);
            mlstKhoa.remove(khoa);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateAddLop() {
        try {
            super.changeStateAdd();
            isKhoa = false;
            isLop = true;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = false;
            lop = new Lop();
            lop.setMaKhoa(khoa.getMaKhoa());
            String maKhoa = khoa.getMaKhoa();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateEditLop(Lop lop) {
        try {
            super.changeStateEdit();
            isKhoa = false;
            isLop = true;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = false;
            this.lop = lop;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateCopyLop(Lop lop) {
        try {
            super.changeStateCopy();
            isKhoa = false;
            isLop = true;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = false;
            Lop lop1 = new Lop(lop);
            this.lop = lop1;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void chageStateViewLop(Lop lop) {
        try {
            super.changeStateView();
            isKhoa = false;
            isLop = true;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = false;
            this.lop = lop;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void deleteLop(Lop lop) {
        try {
            khoaModel.deleteLop(lop);
            mlstLop.remove(lop);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateAddMonHoc() {
        try {
            super.changeStateAdd();
            isKhoa = false;
            isLop = false;
            isMonHoc = true;
            isHocKy = false;
            isChuongTrinhHoc = false;
            monHoc = new MonHoc();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateEditMonHoc(MonHoc monHoc) {
        try {
            super.changeStateEdit();
            isKhoa = false;
            isLop = false;
            isMonHoc = true;
            isHocKy = false;
            isChuongTrinhHoc = false;
            this.monHoc = monHoc;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateCopyMonHoc(MonHoc monHoc) {
        try {
            super.changeStateCopy();
            isKhoa = false;
            isLop = false;
            isMonHoc = true;
            isHocKy = false;
            isChuongTrinhHoc = false;
            MonHoc monHoc1 = new MonHoc(monHoc);
            this.monHoc = monHoc1;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void chageStateViewMonHoc(MonHoc monHoc) {
        try {
            super.changeStateView();
            isKhoa = false;
            isLop = false;
            isMonHoc = true;
            isHocKy = false;
            isChuongTrinhHoc = false;
            this.monHoc = monHoc;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void deleteMonHoc(MonHoc monHoc) {
        try {
            khoaModel.deleteMonHoc(monHoc);
            mlstMonHoc.remove(monHoc);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void onRowSelect(SelectEvent<Khoa> event) throws Exception {
        try {
            String maKhoa = event.getObject().getMaKhoa();
            mlstLop = khoaModel.getListLopByMaKhoa(event.getObject().getMaKhoa());
            khoa.setId(event.getObject().getId());
            lop = new Lop();
            khoa.setMaKhoa(event.getObject().getMaKhoa());
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public String getTenKhoaById(long maKhoa) {
        try {
            if (mlstKhoa != null && !mlstKhoa.isEmpty()) {
                for (Khoa khoa1 : mlstKhoa) {
                    if (khoa1.getId() == maKhoa) {
                        return khoa1.getTenKhoa();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
        return "";
    }
    public void onRowSelectDiemDanh(SelectEvent<MonHoc> event) throws Exception{
        try {
            String maKhoa = event.getObject().getMaKhoa();
            mlstLop = khoaModel.getListLopByMaKhoa(event.getObject().getMaKhoa());
            monHoc.setId(event.getObject().getId());
            monHoc.setMaKhoa(event.getObject().getMaKhoa());
            lop = new Lop();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }
    

    public void changeStateAddHocKy() {
        try {
            super.changeStateAdd();
            isKhoa = false;
            isLop = false;
            isMonHoc = false;
            isHocKy = true;
            isChuongTrinhHoc = false;
            hocKy = new HocKy();
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateEditHocKy(HocKy hocKy) {
        try {
            super.changeStateEdit();
            isKhoa = false;
            isLop = false;
            isMonHoc = false;
            isHocKy = true;
            isChuongTrinhHoc = false;
            this.hocKy = hocKy;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateCopyHocKy(HocKy hocKy) {
        try {
            super.changeStateCopy();
            isKhoa = false;
            isLop = false;
            isMonHoc = false;
            isHocKy = true;
            isChuongTrinhHoc = false;
            HocKy hocKy1 = new HocKy(hocKy);
            this.hocKy = hocKy1;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateViewHocKy(HocKy hocKy) {
        try {
            super.changeStateView();
            isKhoa = false;
            isLop = false;
            isMonHoc = false;
            isHocKy = true;
            isChuongTrinhHoc = false;
            this.hocKy = hocKy;
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void deleteHocKy(HocKy hocKy) {
        try {
            hocKyModel.deleteHocKy(hocKy);
            mlstHocKy.remove(hocKy);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
        }
    }

    public void changeStateAddChuongTrinhHoc() {
        try {
            super.changeStateAdd();
            isKhoa = false;
            isLop = false;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = true;
            chuongTrinhHoc = new ChuongTrinhHoc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStateEditChuongTrinhHoc(ChuongTrinhHoc chuongTrinhHoc) {
        try {
            super.changeStateEdit();
            isKhoa = false;
            isLop = false;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = true;
            this.chuongTrinhHoc = chuongTrinhHoc;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStateCopyChuongTrinhHoc(ChuongTrinhHoc chuongTrinhHoc) {
        try {
            super.changeStateCopy();
            isKhoa = false;
            isLop = false;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = true;
            ChuongTrinhHoc chuongTrinhHoc1 = new ChuongTrinhHoc(chuongTrinhHoc);
            this.chuongTrinhHoc = chuongTrinhHoc1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStateViewChuongTrinhHoc(ChuongTrinhHoc chuongTrinhHoc) {
        try {
            super.changeStateView();
            isKhoa = false;
            isLop = false;
            isMonHoc = false;
            isHocKy = false;
            isChuongTrinhHoc = true;
            this.chuongTrinhHoc = chuongTrinhHoc;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteChuongTrinhHoc(ChuongTrinhHoc chuongTrinhHoc) {
        try {
            chuongTrinhHocModel.deleteChuongTrinhHoc(chuongTrinhHoc);
            mlstChuongTrinhHoc.remove(chuongTrinhHoc);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtils.error("", e.toString());
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

    public void onItemUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        context.addMessage(null, msg);
    }
    
    public String ArrayMonHoc(ChuongTrinhHoc chuongTrinhHoc){
        String s = "";
        int i = 0;
        for (String maMonHoc : chuongTrinhHoc.getCodeSubjects()) {
            for (MonHoc mlstMonHoc1 : mlstMonHoc) {
                if(mlstMonHoc1.getMaMon().equals(maMonHoc)){
                    if(i == 0){
                        s += mlstMonHoc1.getTenMon();
                    }else{
                       s += ", " + mlstMonHoc1.getTenMon(); 
                    }
                }
                i++;
            }
        }
        i = 0;
        return s;
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

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public List<Khoa> getMlstKhoa() {
        return mlstKhoa;
    }

    public void setMlstKhoa(List<Khoa> mlstKhoa) {
        this.mlstKhoa = mlstKhoa;
    }

    public List<Lop> getMlstLop() {
        return mlstLop;
    }

    public void setMlstLop(List<Lop> mlstLop) {
        this.mlstLop = mlstLop;
    }

    public List<MonHoc> getMlstMonHoc() {
        return mlstMonHoc;
    }

    public void setMlstMonHoc(List<MonHoc> mlstMonHoc) {
        this.mlstMonHoc = mlstMonHoc;
    }

    public boolean isIsKhoa() {
        return isKhoa;
    }

    public void setIsKhoa(boolean isKhoa) {
        this.isKhoa = isKhoa;
    }

    public boolean isIsLop() {
        return isLop;
    }

    public void setIsLop(boolean isLop) {
        this.isLop = isLop;
    }

    public boolean isIsMonHoc() {
        return isMonHoc;
    }

    public void setIsMonHoc(boolean isMonHoc) {
        this.isMonHoc = isMonHoc;
    }

    public Khoa getKhoaSelected() {
        return khoaSelected;
    }

    public void setKhoaSelected(Khoa khoaSelected) {
        this.khoaSelected = khoaSelected;
    }
        public MonHoc getMonHocSelected() {
        return monHocSelected;
    }

    public void setMonHocSelected(MonHoc monHocSelected) {
        this.monHocSelected = monHocSelected;
    }
         
    private int getInteger(String filterText) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public HocKy getHocKy() {
        return hocKy;
    }

    public void setHocKy(HocKy hocKy) {
        this.hocKy = hocKy;
    }

    public List<HocKy> getMlstHocKy() {
        return mlstHocKy;
    }

    public void setMlstHocKy(List<HocKy> mlstHocKy) {
        this.mlstHocKy = mlstHocKy;
    }

    public boolean isIsHocKy() {
        return isHocKy;
    }

    public void setIsHocKy(boolean isHocKy) {
        this.isHocKy = isHocKy;
    }

    public boolean isIsChuongTrinhHoc() {
        return isChuongTrinhHoc;
    }

    public void setIsChuongTrinhHoc(boolean isChuongTrinhHoc) {
        this.isChuongTrinhHoc = isChuongTrinhHoc;
    }

    public ChuongTrinhHoc getChuongTrinhHoc() {
        return chuongTrinhHoc;
    }

    public void setChuongTrinhHoc(ChuongTrinhHoc chuongTrinhHoc) {
        this.chuongTrinhHoc = chuongTrinhHoc;
    }

    public List<ChuongTrinhHoc> getMlstChuongTrinhHoc() {
        return mlstChuongTrinhHoc;
    }

    public void setMlstChuongTrinhHoc(List<ChuongTrinhHoc> mlstChuongTrinhHoc) {
        this.mlstChuongTrinhHoc = mlstChuongTrinhHoc;
    }

}
