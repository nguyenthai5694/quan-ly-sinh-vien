/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.mysql.cj.xdevapi.DatabaseObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nguyễn Tiến Dũng
 */
public class ConnectionUtil {

    private static String DB_URL = "jdbc:mysql://localhost:3306/student_management";
    private static String USER_NAME = "root";
    private static String PASSWORD = "vanthai1";

    public Connection mConnection = null;
    public PreparedStatement mStmt = null;
    public ResultSet mRs = null;

    public void open() throws Exception {
        mConnection = getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    public Connection getConnection(String dbURL, String userName,
            String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    public void close(Connection cnn) throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public void close(PreparedStatement stmt) throws Exception {
        if (stmt != null) {
            stmt.close();
        }
    }

    public void close(ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
        }
    }

    public void close() throws Exception {
        if (mRs != null) {
            mRs.close();
        }
        if (mStmt != null) {
            mStmt.close();
        }
        if (mConnection != null) {
            mConnection.close();
        }
    }
}
