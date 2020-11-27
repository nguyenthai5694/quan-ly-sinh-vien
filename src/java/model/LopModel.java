/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Lop;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionUtil;

/**
 *
 * @author ADMIN
 */
public class LopModel extends ConnectionUtil {
    public List<Lop> getListLop() throws Exception {
        List<Lop> lstLop = new ArrayList<>();
        String strSQL = "select * from class";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
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
    
    public List<Lop> getListLopTheoKhoa(String codeKhoa) throws Exception {
        List<Lop> lstLop = new ArrayList<>();
        String strSQL = "select * from class where code_khoa = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, codeKhoa);
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
