/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.LoginController;
import entity.SinhVien;
import java.io.IOException;
import static java.nio.channels.AsynchronousServerSocketChannel.open;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import util.ConnectionUtil;
/**
 *
 * @author ADMIN
 */
public class DiemDanhModel extends ConnectionUtil {
    public void addDiemDanh(List<SinhVien> listSinhVien, String codeClass, String codeSubject) throws IOException, SQLException, Exception {
        try {
            for (SinhVien listSinhVien1 : listSinhVien) {
                String strSQL = "insert into diem_danh\n"
                    + "(code_subjects,\n"
                    + "code_class,\n"
                    + "code_student,\n"
                    + "date,\n"
                    + "decscription, chg_who, chg_date, status) \n"
                    + "values\n"
                    + "(?,?,?,sysdate(),?,?,sysdate(),?)";
                open();
                mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
                mStmt.setString(1, codeSubject);
                mStmt.setString(2, codeClass);
                mStmt.setString(3, listSinhVien1.getCode());
                mStmt.setString(4, listSinhVien1.getDecscriptionDiemDanh());
                mStmt.setString(5, LoginController.getUserLogin());
                mStmt.setInt(6, listSinhVien1.getDiemDanh());
                mStmt.executeUpdate();
                mRs = mStmt.getGeneratedKeys();
            }
        } finally {
            close();
        }
    }
}
