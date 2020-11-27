/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entity.HocPhi;
import entity.MonHoc;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionUtil;

/**
 *
 * @author gia nguyen
 */
public class HocPhiModel extends ConnectionUtil {

    public List<HocPhi> getListHocPhi(String codeHK) throws Exception {
        List<HocPhi> lstHocPhi = new ArrayList<>();
        String strSQL = "select * from hoc_phi where code_hk = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, codeHK);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                HocPhi hocPhi = new HocPhi();
                hocPhi.setId(mRs.getLong("id"));
                hocPhi.setCodeHK(mRs.getString("code_hk"));
                hocPhi.setCodeStudent(mRs.getString("code_student"));
                hocPhi.setTotalTC(mRs.getLong("total_tc"));
                hocPhi.setTotalTien(mRs.getDouble("total_tien"));
                hocPhi.setStatus(mRs.getString("status"));
                lstHocPhi.add(hocPhi);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return lstHocPhi;
    }
    public List<HocPhi> getListHocPhiTheoKhoa(String codeHK, String codeKhoa) throws Exception {
        List<HocPhi> lstHocPhi = new ArrayList<>();
        String strSQL = "SELECT * FROM student inner join hoc_phi on student.code = hoc_phi.code_student where hoc_phi.code_hk = ? and student.code_khoa = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, codeHK);
            mStmt.setString(2, codeKhoa);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                HocPhi hocPhi = new HocPhi();
                hocPhi.setId(mRs.getLong("id"));
                hocPhi.setCodeHK(mRs.getString("code_hk"));
                hocPhi.setCodeStudent(mRs.getString("code_student"));
                hocPhi.setTotalTC(mRs.getLong("total_tc"));
                hocPhi.setTotalTien(mRs.getDouble("total_tien"));
                hocPhi.setStatus(mRs.getString("hoc_phi.status"));
                lstHocPhi.add(hocPhi);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return lstHocPhi;
    }
    public List<HocPhi> getListHocPhiTheoLop(String codeHK, String codeLop) throws Exception {
        List<HocPhi> lstHocPhi = new ArrayList<>();
        String strSQL = "SELECT * FROM student inner join hoc_phi on student.code = hoc_phi.code_student where hoc_phi.code_hk = ? and student.code_class = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, codeHK);
            mStmt.setString(2, codeLop);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                HocPhi hocPhi = new HocPhi();
                hocPhi.setId(mRs.getLong("id"));
                hocPhi.setCodeHK(mRs.getString("code_hk"));
                hocPhi.setCodeStudent(mRs.getString("code_student"));
                hocPhi.setTotalTC(mRs.getLong("total_tc"));
                hocPhi.setTotalTien(mRs.getDouble("total_tien"));
                hocPhi.setStatus(mRs.getString("hoc_phi.status"));
                lstHocPhi.add(hocPhi);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return lstHocPhi;
    }
    public Long getTC(String code) throws Exception{
        KhoaModel khoaModel = new KhoaModel();
        List<MonHoc> listMonHoc = new ArrayList<>(khoaModel.getListMonHoc());
        for (MonHoc listMonHoc1 : listMonHoc) {
            if(listMonHoc1.getMaMon().equals(code)){
                return listMonHoc1.getSoTinChi();
            }
        }
        return null;
    }
    public int getTongTC(String[] listMonHoc) throws Exception {
        int total = 0;
        if(listMonHoc.length != 0){
            for (String listMonHoc1 : listMonHoc) {
                total += getTC(listMonHoc1);
            }
        }
        return total;
    }
    public void addHocPhi(HocPhi hocPhi) throws Exception {
        String strSQL = "insert into hoc_phi(\n"
                + "code_hk,\n"
                + "code_student,\n"
                + "total_tc,\n"
                + "total_tien,\n"
                + "status) \n"
                + "values(?,?,?,?,0)";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            mStmt.setString(1, hocPhi.getCodeHK());
            mStmt.setString(2, hocPhi.getCodeStudent());
            mStmt.setLong(3, hocPhi.getTotalTC());
            mStmt.setDouble(4, hocPhi.getTotalTien());
//            mStmt.setString(5, hocPhi.getStatus());
            mStmt.executeUpdate();
            mRs = mStmt.getGeneratedKeys();
            if (mRs.next()) {
                hocPhi.setId(mRs.getLong(1));
            }
        } finally {
            close();
        }
    }

//    public void updateHocPhi(HocPhi hocPhi) throws Exception {
//        String strSQL = "update hoc_phi set code_hk = ?,\n"
//                + "code_student = ?,\n"
//                + "total_tc = ?,\n"
//                + "total_tien = ?\n"
//                + "where id = ? and status = 1";
//        try {
//            open();
//            mStmt = mConnection.prepareStatement(strSQL);
//            mStmt.setString(1, hocPhi.getCodeHK());
//            mStmt.setString(2, hocPhi.getCodeStudent());
//            mStmt.setLong(3, hocPhi.getTotalTC());
//            mStmt.setDouble(4, hocPhi.getTotalTien());
//            mStmt.setLong(5, hocPhi.getId());
//            mStmt.executeUpdate();
//        } finally {
//            close();
//        }
//    }

    public void dongHocPhi(HocPhi hocPhi) throws Exception {
        String strSQL = "UPDATE hoc_phi SET status = '1', date = sysdate() WHERE (id = ? and status = 0);";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setLong(1, hocPhi.getId());
            mStmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
            finally {
            close();
        }
    }
}
