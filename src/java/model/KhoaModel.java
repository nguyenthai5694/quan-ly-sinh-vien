/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.ChiTietHocPhi;
import entity.Khoa;
import entity.Lop;
import entity.MonHoc;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionUtil;
import util.HocPhiUtils;

/**
 *
 * @author gia nguyen
 */
public class KhoaModel extends ConnectionUtil {

    public List<Khoa> getListKhoa() throws Exception {
        List<Khoa> lstKhoa = new ArrayList<>();
        String strSQL = "select * from khoa";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                Khoa khoa = new Khoa();
                khoa.setId(mRs.getLong("id"));
                khoa.setMaKhoa(mRs.getString("code_khoa"));
                khoa.setTenKhoa(mRs.getString("name"));
                khoa.setChg_Who(mRs.getString("chg_who"));
                khoa.setChg_Date(mRs.getTimestamp("chg_date"));
                khoa.setStatus(mRs.getString("status"));
                lstKhoa.add(khoa);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return lstKhoa;
    }

    public List<Lop> getListLopByMaKhoa(String maKhoa) throws Exception {
        List<Lop> lstLop = new ArrayList<>();
        String strSQL = "select * from class where code_khoa = ? order by name";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, maKhoa);
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

    public List<MonHoc> getListMonHoc() throws Exception {
        List<MonHoc> lstMonHoc = new ArrayList<>();
        String strSQL = "select * from subjects";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                MonHoc monHoc = new MonHoc();
                monHoc.setId(mRs.getLong("id"));
                monHoc.setMaMon(mRs.getString("code"));
                monHoc.setTenMon(mRs.getString("name"));
                monHoc.setSoTinChi(mRs.getLong("tin_chi"));
                monHoc.setChg_Who(mRs.getString("chg_who"));
                monHoc.setChg_Date(mRs.getTimestamp("chg_date"));
                monHoc.setStatus(mRs.getString("status"));
                lstMonHoc.add(monHoc);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return lstMonHoc;
    }
    public ChiTietHocPhi getChiTietHocPhi(String codeSubject, String codeSV) throws Exception{
        ChiTietHocPhi chiTietHocPhi = new ChiTietHocPhi();
        String strSQL = "select * from subjects where code = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, codeSubject);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                
                chiTietHocPhi.setCodeSubject(mRs.getString("code"));
                chiTietHocPhi.setCodeSV(codeSV);
                chiTietHocPhi.setNameSubject(mRs.getString("name"));
                chiTietHocPhi.setTinChi(mRs.getInt("tin_chi"));
                chiTietHocPhi.setHocPhi(mRs.getInt("tin_chi")*HocPhiUtils.HOC_PHI);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return chiTietHocPhi;
    }

    public void addKhoa(Khoa khoa) throws Exception {
        String strSQL = "insert into khoa\n"
                + "(code_khoa,\n"
                + "name,\n"
                + "chg_who,\n"
                + "chg_date,\n"
                + "status) \n"
                + "values\n"
                + "(?,?,?,sysdate(),?);";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            mStmt.setString(1, khoa.getMaKhoa());
            mStmt.setString(2, khoa.getTenKhoa());
            mStmt.setString(3, khoa.getChg_Who());
//            mStmt.setObject(4, khoa.getChg_Date());
            mStmt.setString(4, khoa.getStatus());
            mStmt.executeUpdate();
            mRs = mStmt.getGeneratedKeys();
            if (mRs.next()) {
                khoa.setId(mRs.getLong(1));
                khoa.setStatus("1");
            }
        } finally {
            close();
        }
    }

    public void updateKhoa(Khoa khoa) throws Exception {
        String strSQL = "update\n"
                + "khoa\n"
                + "SET code_khoa = ?,\n"
                + "name = ?,\n"
                + "chg_who = ?,\n"
                + "chg_date = ?\n"
                + "where id = ? and status = '1'";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, khoa.getMaKhoa());
            mStmt.setString(2, khoa.getTenKhoa());
            mStmt.setString(3, khoa.getChg_Who());
            mStmt.setObject(4, khoa.getChg_Date());
            mStmt.setLong(5, khoa.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }

    public void deleteKhoa(Khoa khoa) throws Exception {
        String strSQL = "update khoa\n"
                + "set status = '0'\n"
                + "where id = ?;";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setLong(1, khoa.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }

        public String getTenLopTheoMaLop(String code) throws Exception {
        String strSQL = "select * from class where code = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, code);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                return mRs.getString("name");
            }
        } finally {
            close();
        }
        return "";
    }
    public void addLop(Lop lop) throws Exception {
        String strSQL = "insert into class\n"
                + "(code,\n"
                + "name,\n"
                + "code_khoa,\n"
                + "chg_who,\n"
                + "chg_date,\n"
                + "status) \n"
                + "values(?,?,?,?,sysdate(),?);";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            mStmt.setString(1, lop.getMaLop());
            mStmt.setString(2, lop.getTenLop());
            mStmt.setString(3, lop.getMaKhoa());
            mStmt.setString(4, lop.getChg_Who());
//            mStmt.setObject(5, lop.getChg_Date());
            mStmt.setString(5, lop.getStatus());
            mStmt.executeUpdate();
            mRs = mStmt.getGeneratedKeys();
            if (mRs.next()) {
                lop.setId(mRs.getLong(1));
                lop.setStatus("1");
            }
        } finally {
            close();
        }
    }

    public void updateLop(Lop lop) throws Exception {
        String strSQL = "update\n"
                + "class\n"
                + "SET code = ?,\n"
                + "name = ?,\n"
                + "code_khoa = ?,\n"
                + "chg_who = ?,\n"
                + "chg_date = ?\n"
                + "where id = ? and status = '1'";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, lop.getMaLop());
            mStmt.setString(2, lop.getTenLop());
            mStmt.setString(3, lop.getMaKhoa());
            mStmt.setString(4, lop.getChg_Who());
            mStmt.setObject(5, lop.getChg_Date());
            mStmt.setLong(6, lop.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }

    public void deleteLop(Lop lop) throws Exception {
        String strSQL = "update class\n"
                + "set status = '0'\n"
                + "where id = ?;";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setLong(1, lop.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }

    public void addMonHoc(MonHoc monHoc) throws Exception {
        String strSQL = "insert into subjects\n"
                + "(code,\n"
                + "name,\n"
                + "tin_chi,\n"
                + "chg_who,\n"
                + "chg_date,\n"
                + "status) \n"
                + "values(?,?,?,?,sysdate(),?);";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            mStmt.setString(1, monHoc.getMaMon());
            mStmt.setString(2, monHoc.getTenMon());
            mStmt.setLong(3, monHoc.getSoTinChi());
            mStmt.setString(4, monHoc.getChg_Who());
//            mStmt.setObject(6, monHoc.getChg_Date());
            mStmt.setString(5, monHoc.getStatus());
            mStmt.executeUpdate();
            mRs = mStmt.getGeneratedKeys();
            if (mRs.next()) {
                monHoc.setId(mRs.getLong(1));
                monHoc.setStatus("1");
            }
        } finally {
            close();
        }
    }

    public void updateMonHoc(MonHoc monHoc) throws Exception {
        String strSQL = "update\n"
                + "subjects\n"
                + "SET code = ?,\n"
                + "name = ?,\n"
                + "tin_chi = ?,\n"
                + "chg_who = ?,\n"
                + "chg_date = ?\n"
                + "where id = ? and status = '1'";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, monHoc.getMaMon());
            mStmt.setString(2, monHoc.getTenMon());
            mStmt.setLong(3, monHoc.getSoTinChi());
            mStmt.setString(4, monHoc.getChg_Who());
            mStmt.setObject(5, monHoc.getChg_Date());
            mStmt.setLong(6, monHoc.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }

    public void deleteMonHoc(MonHoc monHoc) throws Exception {
        String strSQL = "update mon_hoc\n"
                + "set status = '0'\n"
                + "where id = ?;";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setLong(1, monHoc.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }
}
