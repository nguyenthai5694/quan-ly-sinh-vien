/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.GiangVien_old;
import entity.Lop;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ActionUtil;
import util.ConnectionUtil;
import util.DateUtils;

/**
 *
 * @author ADMIN
 */
public class GiangVienModel_old extends ConnectionUtil{
    public List<GiangVien_old> getListGiangVien() throws Exception{
        List<GiangVien_old> lstGiangVien = new ArrayList<>();
        String strSQL = "select * from teacher order by code_khoa";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            while(mRs.next()){
                GiangVien_old gv = new GiangVien_old();
                gv.setId(mRs.getLong("id"));
                gv.setMaGv(mRs.getString("code"));
                gv.setTenGv(mRs.getString("name"));
                gv.setNgaySinhGv(mRs.getTimestamp("birthday"));
                gv.setDienThoai(mRs.getString("phone_number"));
                gv.setDiaChi(mRs.getString("address"));
                gv.setCMND(mRs.getString("id_no"));
//                gv.setNgayCapCMND(mRs.getInt("id_issue_date"));
                gv.setNoiCapCMND(mRs.getString("id_issue_place"));
                gv.setNguyenQuan(mRs.getString("gender"));
                gv.setListMonHoc(mRs.getString("list_subjects"));
                gv.setMaKhoa(mRs.getString("code_khoa"));
                gv.setChgWho(mRs.getString("chg_who"));
                gv.setChgDate(mRs.getTimestamp("chg_date"));
//                gv.setStatus(mRs.getString("status"));
                lstGiangVien.add(gv);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            close();
        }
        return lstGiangVien;
    }
    
    public void add(GiangVien_old giangVien) throws Exception{
        String strSQL = "insert into teacher\n" +
                        "(code,\n" +
                        "name,\n" +
                        "birthday,\n" +
                        "phone_number,\n" +
                        "address,\n" +
                        "gender,\n" +
                        "id_no,\n" +
                        "id_issue_date,\n" +
                        "id_issue_place,\n" +
                        "chg_who,\n" +
                        "status)values(?,?,?,?,?,?,?,?,?,?,sysdate(),?);";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            mStmt.setString(1, giangVien.getMaGv());
            mStmt.setString(2, giangVien.getTenGv());
            mStmt.setObject(3, DateUtils.getSQLDate(giangVien.getNgaySinhGv()));
            mStmt.setString(4, giangVien.getDienThoai());
            mStmt.setString(5, giangVien.getDiaChi());
            mStmt.setString(6, giangVien.getNguyenQuan());
            mStmt.setString(7, giangVien.getCMND());
            mStmt.setObject(8, DateUtils.getSQLDate(giangVien.getNgayCapCMND()));
            mStmt.setString(9, giangVien.getNoiCapCMND());
            mStmt.setString(10, giangVien.getChgWho());
            mStmt.setInt(11, giangVien.getStatus());
            mStmt.executeUpdate();
            mRs = mStmt.getGeneratedKeys();
            if(mRs.next()){
                giangVien.setId(mRs.getLong(1));
            }
        } finally {
            close();
        }
    }
    public void updateGiangVien(GiangVien_old giangVien)throws Exception{
        String strSQL = "update teacher \n" +
                        "set code = ?,\n" +
                        "name = ?,\n" +
                        "birthday = ?,\n" +
                        "phone_number = ?,\n" +
                        "address = ?,\n" +
                        "gender = ?,\n" +
                        "id_no = ?,\n" +
                        "id_issue_date = ?,\n" +
                        "id_issue_place = ?,\n" +
                        "code_khoa = ?,\n" +
                        "list_subjects = ?,\n" +
                        "chg_who = ?,\n" +
                        "chg_date = ?,\n" +
                        "status = ?\n" +
                        "where id = ?;";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, giangVien.getMaGv());
            mStmt.setString(2, giangVien.getTenGv());
            mStmt.setObject(3, DateUtils.getSQLDate(giangVien.getNgaySinhGv()));
            mStmt.setString(4, giangVien.getDienThoai());
            mStmt.setString(5, giangVien.getDiaChi());
            mStmt.setString(6, giangVien.getCMND());
            mStmt.setObject(7, giangVien.getNgayCapCMND());
            mStmt.setString(8, giangVien.getNoiCapCMND());
            mStmt.setString(9, giangVien.getNguyenQuan());
            mStmt.setString(10, giangVien.getMaKhoa());
            mStmt.setString(11, giangVien.getListMonHoc());
            mStmt.setString(12, giangVien.getChgWho());
            mStmt.setObject(13, giangVien.getChgDate());
//            mStmt.setString(14, giangVien.getStatus());
            mStmt.executeUpdate();
        } finally{
            close();
        }
    }
    public void delete(GiangVien_old giangVien) throws Exception{
        String strSQL = "update teacher\n" +
                        "set status = 0\n" +
                        "where id= ?;";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setLong(1, giangVien.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }
    public List<Lop> getListLopByMonHoc(String maMon) throws Exception {
        List<Lop> lstLop = new ArrayList<>();
        String strSQL = "select * from class where code_khoa = ? order by name";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, maMon);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                Lop lop = new Lop();
                lop.setId(mRs.getLong("id"));
                lop.setMaLop(mRs.getString("code"));
                lop.setTenLop(mRs.getString("name"));
                lop.setMaKhoa(mRs.getString("code_khoa"));
                lop.setChg_Who(mRs.getString("chg_who"));
                lop.setChg_Date(mRs.getTimestamp("chg_date"));
                lop.setStatus(mRs.getString("status"));
                lstLop.add(lop);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return lstLop;
    }
    
}

