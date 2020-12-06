/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.LoginController;
import entity.SinhVien;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionUtil;
import util.DateUtils;
/**
 *
 * @author ADMIN
 */
public class ThemDiemModel extends ConnectionUtil {
    public void updateDiem(List<SinhVien> listSinhVien, String codeClass, String codeSubject, String codeHK) throws IOException, SQLException, Exception {
        try {
            for (SinhVien listSinhVien1 : listSinhVien) {
                String strSQL = "UPDATE bang_diem SET code_class = ?, diem_a = ?, diem_b = ?, diem_c = ?, chg_who = ?, chg_date = sysdate(), decscription = ? WHERE (code_student = ? and code_hk = ? and code_subject = ?)";
                open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, codeClass);
            mStmt.setDouble(2, listSinhVien1.getDiemA());
            mStmt.setDouble(3, listSinhVien1.getDiemB());
            mStmt.setDouble(4, listSinhVien1.getDiemC());
            mStmt.setString(5, LoginController.getUserLogin());
            mStmt.setString(6, listSinhVien1.getDecscriptionDiem());
            mStmt.setString(7, listSinhVien1.getCode());
            mStmt.setString(8, codeHK);
            mStmt.setString(9, codeSubject);
            mStmt.executeUpdate();
            }
        } finally {
            close();
        }
    }
    public List<SinhVien> getListSinhVienBangDiem(String maLop, String maHK, String maMH) throws Exception {
        List<SinhVien> lstReturn = new ArrayList<SinhVien>();
        String strSQL="select * from student inner join bang_diem on student.code = bang_diem.code_student where student.code_class = ? and bang_diem.code_subject = ? and bang_diem.code_hk = ? and student.status = 1 order by name";
        int i = 1;
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, maLop);
            mStmt.setString(2, maMH);
            mStmt.setString(3, maHK);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                SinhVien user = new SinhVien();
                user.setId(mRs.getInt("id"));
                user.setName(mRs.getString("name"));
                user.setCode(mRs.getString("code"));
                user.setBirthday(mRs.getDate("birthday"));
                user.setPhone_number(mRs.getString("phone_number"));
                user.setAddress(mRs.getString("address"));
                user.setGender(mRs.getInt("gender"));
                user.setId_no(mRs.getString("id_no"));
                user.setId_issue_date(mRs.getDate("id_issue_date"));
                user.setId_issue_place(mRs.getString("id_issue_place"));
                user.setPlace(mRs.getString("place"));
                user.setCode_khoa(mRs.getString("code_khoa"));
                user.setCode_class(mRs.getString("code_class"));
                user.setChg_who(mRs.getString("chg_who"));
                user.setChg_date(mRs.getDate("chg_date"));
                user.setStatus(mRs.getInt("status"));
                user.setDiemA(mRs.getDouble("bang_diem.diem_a"));
                user.setDiemB(mRs.getDouble("bang_diem.diem_b"));
                user.setDiemC(mRs.getDouble("bang_diem.diem_c"));
                user.setDecscriptionDiem(mRs.getString("bang_diem.decscription"));
                user.setStt(i);
                lstReturn.add(user);
                i++;
            }
        } finally {
            close();
        }
        return lstReturn;
    }
    public List<SinhVien> getListSinhVienTheoHK(String maHK) throws Exception {
        List<SinhVien> lstReturn = new ArrayList<SinhVien>();
        String strSQL="select * from student inner join bang_diem on student.code = bang_diem.code_student where bang_diem.code_hk = ? and student.status = 1 order by name";
        int i = 1;
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, maHK);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                SinhVien user = new SinhVien();
                user.setId(mRs.getInt("id"));
                user.setName(mRs.getString("name"));
                user.setCode(mRs.getString("code"));
                user.setBirthday(mRs.getDate("birthday"));
                user.setPhone_number(mRs.getString("phone_number"));
                user.setAddress(mRs.getString("address"));
                user.setGender(mRs.getInt("gender"));
                user.setId_no(mRs.getString("id_no"));
                user.setId_issue_date(mRs.getDate("id_issue_date"));
                user.setId_issue_place(mRs.getString("id_issue_place"));
                user.setPlace(mRs.getString("place"));
                user.setCode_khoa(mRs.getString("code_khoa"));
                user.setCode_class(mRs.getString("code_class"));
                user.setChg_who(mRs.getString("chg_who"));
                user.setChg_date(mRs.getDate("chg_date"));
                user.setStatus(mRs.getInt("status"));
                user.setDiemA(mRs.getDouble("bang_diem.diem_a"));
                user.setDiemB(mRs.getDouble("bang_diem.diem_b"));
                user.setDiemC(mRs.getDouble("bang_diem.diem_c"));
                user.setDecscriptionDiem(mRs.getString("bang_diem.decscription"));
                user.setStt(i);
                lstReturn.add(user);
                i++;
            }
        } finally {
            close();
        }
        return lstReturn;
    }
    public double getDiemTBMH(String maSV, String maMH, String maHK) throws Exception{
        double dtb = 0;
        String strSQL="select * from bang_diem where code_hk = ? and code_subject = ? and code_sutdent = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, maHK);
            mStmt.setString(2, maMH);
            mStmt.setString(3, maSV);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                dtb = mRs.getDouble("diem_a")*0.6 + mRs.getDouble("diem_b")*0.3 + mRs.getDouble("diem_c")*0.1;
            }
        } finally {
            close();
        }
        return dtb;
    }
    
    public List<String> getListMH(String maSV, String maHK) throws Exception{
        List<String> listReturn = new ArrayList<>();
        String strSQL="select * from bang_diem where code_hk = ? and code_sutdent = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, maHK);
            mStmt.setString(2, maSV);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                listReturn.add(mRs.getString("code_subject"));
            }
        } finally {
            close();
        }
        return listReturn;
    }
}
