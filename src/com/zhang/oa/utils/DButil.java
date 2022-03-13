package com.zhang.oa.utils;

import java.sql.*;
import java.util.ResourceBundle;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceBundle;

public class DButil {
    private static ResourceBundle bundle = getResourceBundle("resources.jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void close(Connection conn, Statement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
