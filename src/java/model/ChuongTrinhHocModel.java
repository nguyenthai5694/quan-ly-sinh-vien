/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.LoginController;
import entity.ChiTietHocPhi;
import entity.ChuongTrinhHoc;
import entity.HocPhi;
import entity.MonHoc;
import entity.SinhVien;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionUtil;

/**
 *
 * @author gia nguyen
 */
public class ChuongTrinhHocModel extends ConnectionUtil {

    public List<ChuongTrinhHoc> getListChuongTrinhHoc() throws Exception {
        List<ChuongTrinhHoc> listChuongTrinhHoc = new ArrayList<>(getListMaChuongTrinhHoc());
        
       List<ChuongTrinhHoc> lstChuongTrinhHoc = new ArrayList<>();
       try {
       for (ChuongTrinhHoc listChuongTrinhHoc1 : listChuongTrinhHoc) {
           List<String> listString = new ArrayList<>(getListMonHoc(listChuongTrinhHoc1.getCodeHK(),listChuongTrinhHoc1.getCodeKhoa()));
           String[] Strings = new String[listString.size()];
            Strings = listString.toArray(Strings);
           listChuongTrinhHoc1.setCodeSubjects(Strings);
           lstChuongTrinhHoc.add(listChuongTrinhHoc1);
        }
        
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return lstChuongTrinhHoc;
    }
    public List<ChuongTrinhHoc> getListMaChuongTrinhHoc() throws Exception{
        List<ChuongTrinhHoc> listChuongTrinhHoc = new ArrayList<>();
         String strSQL = "select * from chuong_trinh_hoc";
         boolean check = false;
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                ChuongTrinhHoc chuongTrinhHoc = new ChuongTrinhHoc();
                chuongTrinhHoc.setId(mRs.getLong("id"));
                chuongTrinhHoc.setCodeHK(mRs.getString("code_hk"));
                chuongTrinhHoc.setCodeKhoa(mRs.getString("code_khoa"));
                if(listChuongTrinhHoc.size() != 0){
                    for (ChuongTrinhHoc cth : listChuongTrinhHoc) {
                        if(cth.getCodeHK().equals(chuongTrinhHoc.getCodeHK()) && cth.getCodeKhoa().equals(chuongTrinhHoc.getCodeKhoa())){
                            check = false;
                            break;
                        }
                        check = true;
                    }
                }else{
                    listChuongTrinhHoc.add(chuongTrinhHoc);
//                    check = false;
                }
                if(check == true){
                    listChuongTrinhHoc.add(chuongTrinhHoc);
                    check = false;
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return listChuongTrinhHoc;
    }
    public List<String> getListMonHoc(String codeHK, String codeKhoa) throws Exception{
            List<String> lstStrings = new ArrayList<>();
            String strSQL = "select * from chuong_trinh_hoc where code_khoa = ? and code_hk = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, codeKhoa);
            mStmt.setString(2, codeHK);
            mRs = mStmt.executeQuery();
            String maMonHoc = "";
            while (mRs.next()) {
                maMonHoc = mRs.getString("code_subjects");
                lstStrings.add(maMonHoc);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            close();
        }
        return lstStrings;
    }
    

    public void addChuongTrinhHoc(ChuongTrinhHoc chuongTrinhHoc) throws Exception {

        try {
            for (String codeSubjects : chuongTrinhHoc.getCodeSubjects()) {
                String strSQL = "insert into chuong_trinh_hoc\n"
                        + "(code_khoa,\n"
                        + "code_hk,\n"
                        + "code_subjects) \n"
                        + "values(?,?,?)";
                open();
                mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
                mStmt.setString(1, chuongTrinhHoc.getCodeKhoa());
                mStmt.setString(2, chuongTrinhHoc.getCodeHK());
                mStmt.setString(3, codeSubjects);
                mStmt.executeUpdate();
                mRs = mStmt.getGeneratedKeys();
                if (mRs.next()) {
                    chuongTrinhHoc.setId(mRs.getLong(1));
                }
                addHocPhi(chuongTrinhHoc);
                addDiem(chuongTrinhHoc,codeSubjects);
            }

        } finally {
            close();
        }
    }
    public void addDiem(ChuongTrinhHoc cth, String codeSubject) throws Exception {
        SinhVienModel sinhVienModel = new SinhVienModel();
        List<SinhVien> listSV = new ArrayList<>(sinhVienModel.getListSinhVienTheoKhoa(cth.getCodeKhoa()));
        try {
            for (SinhVien listSinhVien1 : listSV) {
                    String strSQL = "INSERT INTO bang_diem (code_hk, code_student, code_subject, chg_who, chg_date) VALUES (?, ?, ?, ?, sysdate());";
                    open();
                    mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
                    mStmt.setString(3, codeSubject);
                    mStmt.setString(1, cth.getCodeHK());
                    mStmt.setString(2, listSinhVien1.getCode());
                    mStmt.setString(4, LoginController.getUserLogin());
                    mStmt.executeUpdate();
                    mRs = mStmt.getGeneratedKeys();
            }
        } finally {
            close();
        }
    }
    public void addHocPhi(ChuongTrinhHoc cth) throws Exception{
        HocPhiModel hocPhiModel = new HocPhiModel();
        SinhVienModel sinhVienModel = new SinhVienModel();
        List<SinhVien> listSV = new ArrayList<>(sinhVienModel.getListSinhVienTheoKhoa(cth.getCodeKhoa()));
        HocPhi hocPhi = new HocPhi();
        for (SinhVien listSV1 : listSV) {
            hocPhi.setCodeHK(cth.getCodeHK());
            hocPhi.setCodeStudent(listSV1.getCode());
            hocPhi.setTotalTC(hocPhiModel.getTongTC(cth.getCodeSubjects()));
            hocPhi.setTotalTien(hocPhiModel.getTongTC(cth.getCodeSubjects())*300000);
            hocPhiModel.addHocPhi(hocPhi);
        }
    }

    public void updateChuongTrinhHoc(ChuongTrinhHoc chuongTrinhHoc) throws Exception {
        String strSQL = "update chuong_trinh_hoc\n"
                + "set code_khoa = ?,\n"
                + "code_hk = ?,\n"
                + "code_subjects = ?\n"
                + "where id = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, chuongTrinhHoc.getCodeKhoa());
            mStmt.setString(2, chuongTrinhHoc.getCodeHK());
//            mStmt.setString(3, chuongTrinhHoc.getCodeSubjects());
            mStmt.setLong(4, chuongTrinhHoc.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }

    public void deleteChuongTrinhHoc(ChuongTrinhHoc chuongTrinhHoc) throws Exception {
        try {
            for (String codeMonHoc : chuongTrinhHoc.getCodeSubjects()) {
                String strSQL = "delete from chuong_trinh_hoc where code_subjects = ? and code_hk = ? and code_khoa = ?";
                open();
                mStmt = mConnection.prepareStatement(strSQL);
                mStmt.setString(1, codeMonHoc);
                mStmt.setString(2, chuongTrinhHoc.getCodeHK());
                mStmt.setString(3, chuongTrinhHoc.getCodeKhoa());
                mStmt.executeUpdate();
            }
        } finally {
            close();
        }
    }
}
