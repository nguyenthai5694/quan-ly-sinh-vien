/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.HocKy;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionUtil;
import util.DateUtils;

/**
 *
 * @author gia nguyen
 */
public class HocKyModel extends ConnectionUtil {

    public List<HocKy> getListHocKy() throws Exception {
        List<HocKy> lstHocKy = new ArrayList<>();
        String strSQL = "select * from hoc_ky";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mRs = mStmt.executeQuery();
            while (mRs.next()) {
                HocKy hocKy = new HocKy();
                hocKy.setId(mRs.getLong("id"));
                hocKy.setCode(mRs.getString("code"));
                hocKy.setStart_Date(mRs.getTimestamp("start_date"));
                hocKy.setEnd_Date(mRs.getTimestamp("end_date"));
                lstHocKy.add(hocKy);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return lstHocKy;
    }

    public void addHocKy(HocKy hocKy) throws Exception {
        String strSQL = "insert into hoc_ky\n"
                + "(code,\n"
                + "start_date,\n"
                + "end_date) \n"
                + "values(?,?,?)";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            mStmt.setString(1, hocKy.getCode());
            mStmt.setObject(2, DateUtils.getSQLDate(hocKy.getStart_Date()));
            mStmt.setObject(3, DateUtils.getSQLDate(hocKy.getEnd_Date()));
            mStmt.executeUpdate();
            mRs = mStmt.getGeneratedKeys();
            if (mRs.next()) {
                hocKy.setId(mRs.getLong(1));
            }
        } finally {
            close();
        }
    }

    public void updateHocKy(HocKy hocKy) throws Exception {
        String strSQL = "update hoc_ky set code = ?, start_date = ?, end_date = ? where id = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setString(1, hocKy.getCode());
            mStmt.setObject(2, DateUtils.getSQLDate(hocKy.getStart_Date()));
            mStmt.setObject(3, DateUtils.getSQLDate(hocKy.getEnd_Date()));
            mStmt.setLong(4, hocKy.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }

    public void deleteHocKy(HocKy hocKy) throws Exception {
        String strSQL = "delete from hoc_ky where id = ?";
        try {
            open();
            mStmt = mConnection.prepareStatement(strSQL);
            mStmt.setLong(1, hocKy.getId());
            mStmt.executeUpdate();
        } finally {
            close();
        }
    }
}
