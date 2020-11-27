/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class SinhVienModel extends ConnectionUtil{

    public List<SinhVien> getListSinhVienTheoKhoa(String maKhoa) throws Exception {
        List<SinhVien> lstReturn = new ArrayList<SinhVien>();
        String strSQL="select * from student where code_khoa = ? and status = 1 order by name";
       
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, maKhoa);
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
                lstReturn.add(user);
            }
        } finally {
            close();
        }
        return lstReturn;
    }
    public List<SinhVien> getListSinhVienTheoLop(String maLop) throws Exception {
        List<SinhVien> lstReturn = new ArrayList<SinhVien>();
        String strSQL="select * from student where code_class = ? and status = 1 order by name";
       
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, maLop);
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
                lstReturn.add(user);
            }
        } finally {
            close();
        }
        return lstReturn;
    }
    public List<SinhVien> getAllSinhVien() throws Exception {
        List<SinhVien> lstReturn = new ArrayList<SinhVien>();
        String strSQL="select * from student where status = 1 order by name";
       
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
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
                lstReturn.add(user);
            }
        } finally {
            close();
        }
        return lstReturn;
    }

    public SinhVien getSVTheoMaSinhVien(String code) throws Exception {
        SinhVien user = new SinhVien();
        String strSQL = "SELECT * FROM student inner join khoa on student.code_khoa = khoa.code_khoa where code = ?;";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, code);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                user.setName(mRs.getString("name"));
                user.setCode_khoa(mRs.getString("code_khoa"));
                user.setCode_class(mRs.getString("code_class"));
                user.setName_Khoa(mRs.getString("khoa.name"));
            }
        } finally {
            close();
        }
        return user;
    }
    public void add(SinhVien user) throws Exception {
        String strSQL = "insert into student(code,name,birthday,phone_number,address,gender,id_no,id_issue_date,id_issue_place,place,code_khoa,code_class,chg_who,chg_date,status)\n" +
"values(?,?,str_to_date(?,'%d/%m/%Y'),?,?,?,?,str_to_date(?,'%d/%m/%Y'),?,?,?,?,?,sysdate(),?)";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
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
            mStmt.setString(11, user.getCode_khoa());
            mStmt.setString(12, user.getCode_class());
            mStmt.setString(13, user.getChg_who());
//            mStmt.setString(14, DateUtils.convertDate(user.getChg_date(), "dd/MM/yyyy"));
            mStmt.setInt(14, user.getStatus());
            mStmt.executeUpdate();
            mRs = mStmt.getGeneratedKeys();
            if(mRs.next()){
                user.setId(mRs.getInt(1));
            }
        } finally {
            close();
        }
    }
    
    public void update(SinhVien user) throws Exception{
        String strSQL = "update  \n" +
                "	student\n" +
                "   SET	code = ?, name = ?, birthday = STR_TO_DATE(?, '%d/%m/%Y'), phone_number = ?, \n" +
                "	address = ?, gender = ?, id_no = ?, \n" +
                "	id_issue_date = STR_TO_DATE(?, '%d/%m/%Y'), id_issue_place = ?, place = ?, \n" +
                "	code_khoa = ?, code_class = ?, chg_who = ?, chg_date = sysdate() \n" +
                "   where id = ?";
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
            mStmt.setString(11, user.getCode_khoa());
            mStmt.setString(12, user.getCode_class());
            mStmt.setString(13, user.getChg_who());
//            mStmt.setString(14, DateUtils.convertDate(user.getChg_date(), "dd/MM/yyyy"));
            mStmt.setLong(14, user.getId());
            mStmt.executeUpdate();
        }finally{
            close();
        }
    }
    
    public void remove(SinhVien user) throws Exception{
        String strSQL = "UPDATE student SET status = 0 WHERE (id = ?);";
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
