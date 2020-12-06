/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.GiangVien;
import entity.SinhVien;
import entity.User;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionUtil;
import util.DateUtils;

/**
 *
 * @author Admin
 */
public class GiangVienModel extends ConnectionUtil{

//    public List<SinhVien> getListSinhVienTheoKhoa(String maKhoa) throws Exception {
//        List<SinhVien> lstReturn = new ArrayList<SinhVien>();
//        String strSQL="select * from teacher where code_khoa = ? and status = 1 order by name";
//       
//        try {
//            open();
//            mStmt = mConnection.prepareStatement(strSQL);
//            mStmt.setString(1, maKhoa);
//            mRs = mStmt.executeQuery();
//            while (mRs.next()) {
//                SinhVien user = new SinhVien();
//                user.setId(mRs.getInt("id"));
//                user.setName(mRs.getString("name"));
//                user.setCode(mRs.getString("code"));
//                user.setBirthday(mRs.getDate("birthday"));
//                user.setPhone_number(mRs.getString("phone_number"));
//                user.setAddress(mRs.getString("address"));
//                user.setGender(mRs.getInt("gender"));
//                user.setId_no(mRs.getString("id_no"));
//                user.setId_issue_date(mRs.getDate("id_issue_date"));
//                user.setId_issue_place(mRs.getString("id_issue_place"));
//                user.setPlace(mRs.getString("place"));
//                user.setCode_khoa(mRs.getString("code_khoa"));
//                user.setCode_class(mRs.getString("code_class"));
//                user.setChg_who(mRs.getString("chg_who"));
//                user.setChg_date(mRs.getDate("chg_date"));
//                user.setStatus(mRs.getInt("status"));
//                lstReturn.add(user);
//            }
//        } finally {
//            close();
//        }
//        return lstReturn;
//    }
//    public List<SinhVien> getListSinhVienTheoLop(String maLop) throws Exception {
//        List<SinhVien> lstReturn = new ArrayList<SinhVien>();
//        String strSQL="select * from student where code_class = ? and status = 1 order by name";
//       
//        try {
//            open();
//            mStmt = mConnection.prepareStatement(strSQL);
//            mStmt.setString(1, maLop);
//            mRs = mStmt.executeQuery();
//            while (mRs.next()) {
//                SinhVien user = new SinhVien();
//                user.setId(mRs.getInt("id"));
//                user.setName(mRs.getString("name"));
//                user.setCode(mRs.getString("code"));
//                user.setBirthday(mRs.getDate("birthday"));
//                user.setPhone_number(mRs.getString("phone_number"));
//                user.setAddress(mRs.getString("address"));
//                user.setGender(mRs.getInt("gender"));
//                user.setId_no(mRs.getString("id_no"));
//                user.setId_issue_date(mRs.getDate("id_issue_date"));
//                user.setId_issue_place(mRs.getString("id_issue_place"));
//                user.setPlace(mRs.getString("place"));
//                user.setCode_khoa(mRs.getString("code_khoa"));
//                user.setCode_class(mRs.getString("code_class"));
//                user.setChg_who(mRs.getString("chg_who"));
//                user.setChg_date(mRs.getDate("chg_date"));
//                user.setStatus(mRs.getInt("status"));
//                lstReturn.add(user);
//            }
//        } finally {
//            close();
//        }
//        return lstReturn;
//    }
    public List<GiangVien> getAllGiangVien() throws Exception {
        List<GiangVien> lstReturn = new ArrayList<>();
        String strSQL="select * from teacher where status = 1 order by name";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                GiangVien user = new GiangVien();
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
                user.setChg_who(mRs.getString("chg_who"));
                user.setChg_date(mRs.getDate("chg_date"));
                user.setStatus(mRs.getInt("status"));
                user.getListSubject();
                lstReturn.add(user);
            }
        } finally {
            close();
        }
        return lstReturn;
    }
    
    public List<String> getListMonHoc(String codeGV) throws Exception{
            List<String> lstStrings = new ArrayList<>();
            String strSQL = "select * from list_subjects where code_teacher = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, codeGV);
            mRs = mStmt.executeQuery();
            String maMonHoc = "";
            while (mRs.next()) {
                maMonHoc = mRs.getString("code_subject");
                lstStrings.add(maMonHoc);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            close();
        }
        return lstStrings;
    }

    public String getTenMonHocTheoMa(String code) throws Exception {
        String strSQL = "SELECT * FROM subjects where code = ?";
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
    public void add(GiangVien user) throws Exception {
        String strSQL = "insert into teacher(code,name,birthday,phone_number,address,gender,id_no,id_issue_date,id_issue_place,place,chg_who,chg_date,status)\n" +
"values(?,?,str_to_date(?,'%d/%m/%Y'),?,?,?,?,str_to_date(?,'%d/%m/%Y'),?,?,?,sysdate(),1)";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            mStmt.setString(1, user.getCode());
            mStmt.setString(2, user.getName());
            mStmt.setString(3, DateUtils.convertDate(user.getBirthday(), "dd/MM/yyyy"));
            mStmt.setString(4, user.getPhone_number());
            mStmt.setString(5, user.getAddress());
            mStmt.setInt(6, user.getGender());
            mStmt.setString(7, user.getId_no());
            mStmt.setString(8, DateUtils.convertDate(user.getId_issue_date(), "dd/MM/yyyy"));
            mStmt.setString(9, user.getId_issue_place());
            mStmt.setString(10, user.getPlace());
            mStmt.setString(11, user.getChg_who());
            mStmt.executeUpdate();
            mRs = mStmt.getGeneratedKeys();
            if(mRs.next()){
                user.setId(mRs.getInt(1));
            }
            addListMonGiangDay(user);
            creatUser(user);
        } finally {
            close();
        }
    }
    
    public void creatUser(GiangVien giangVien) throws Exception {
        String strSQL = "INSERT INTO user (`username`, `password`, `name`, `phone_number`, `birthday`, `gender`, `id_no`, `id_issue_date`, `id_issue_place`, `address`,"
                + " `chg_who`, `chg_date`, `status`, `dec`) VALUES (?, ?, ?, ?, str_to_date(?,'%d/%m/%Y'), ?, ?, str_to_date(?,'%d/%m/%Y'), ?, ?, ?, sysdate(), '1', '2');";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            mStmt.setString(1, giangVien.getCode());
            mStmt.setString(2, DateUtils.convertDate(giangVien.getBirthday(), "dd/MM/yyyy"));
            mStmt.setString(3, giangVien.getName());
            mStmt.setString(4, giangVien.getPhone_number());
            mStmt.setString(5, DateUtils.convertDate(giangVien.getBirthday(), "dd/MM/yyyy"));
            mStmt.setInt(6, giangVien.getGender());
            mStmt.setString(7, giangVien.getId_no());
            mStmt.setString(8, DateUtils.convertDate(giangVien.getId_issue_date(), "dd/MM/yyyy"));
            mStmt.setString(9, giangVien.getId_issue_place());
            mStmt.setString(10, giangVien.getAddress());
            mStmt.setString(11, giangVien.getChg_who());
            mStmt.executeUpdate();
            mRs = mStmt.getGeneratedKeys();
            if(mRs.next()){
                giangVien.setId(mRs.getInt(1));
            }
        } finally {
            close();
        }
    }
    
    public void addListMonGiangDay(GiangVien giangVien) throws Exception {

        try {
            for (String codeSubjects : giangVien.getListSubject()) {
                String strSQL = "INSERT INTO list_subjects (code_teacher, code_subject) VALUES (?, ?)";
                open();
                mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
                mStmt.setString(1, giangVien.getCode());
                mStmt.setString(2, codeSubjects);
                mStmt.executeUpdate();
                mRs = mStmt.getGeneratedKeys();
                if (mRs.next()) {
                    giangVien.setId(mRs.getLong(1));
                }
            }

        } finally {
            close();
        }
    }
    
    public void update(GiangVien user) throws Exception{
        String strSQL = "update  \n" +
                "	teacher\n" +
                "       SET	code = ?, name = ?, birthday = STR_TO_DATE(?, '%d/%m/%Y'), phone_number = ?, \n" +
                "	address = ?, gender = ?, id_no = ?, \n" +
                "	id_issue_date = STR_TO_DATE(?, '%d/%m/%Y'), id_issue_place = ?, place = ?, \n" +
                "	chg_who = ?, chg_date = sysdate() \n" +
                "       where id = ?";
        
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
             mStmt.setString(1, user.getCode());
            mStmt.setString(2, user.getName());
            mStmt.setString(3, DateUtils.convertDate(user.getBirthday(), "dd/MM/yyyy"));
            mStmt.setString(4, user.getPhone_number());
            mStmt.setString(5, user.getAddress());
            mStmt.setString(8, DateUtils.convertDate(user.getId_issue_date(), "dd/MM/yyyy"));
            mStmt.setInt(6, user.getGender());
            mStmt.setString(7, user.getId_no());
            mStmt.setString(9, user.getId_issue_place());
            mStmt.setString(10, user.getPlace());
            mStmt.setString(11, user.getChg_who());
            mStmt.setLong(12, user.getId());
            mStmt.executeUpdate();
            removeListMonGiangDay(user);
            addListMonGiangDay(user);
        }finally{
            close();
        }
    }
    
    public void removeListMonGiangDay(GiangVien user) throws Exception{
        String strSQL = "DELETE FROM list_subjects WHERE (code_teacher = ?);";
        try {
                open();
                mStmt = mConnection.prepareStatement(strSQL);
                 mStmt.setString(1, user.getCode());
                mStmt.executeUpdate();
        }finally{
            close();
        }
    }
    
    public void remove(GiangVien user) throws Exception{
        String strSQL = "UPDATE teacher SET status = 0 WHERE (id = ?);";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
//            mStmt.setString(1, user.getChg_who());
            mStmt.setLong(1, user.getId());
            int check = mStmt.executeUpdate();
            if(check != 1){
                throw new Exception("Trạng thái cập nhật không hợp lệ");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
}
