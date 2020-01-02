package com.qbank.qbank.utils;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author Gemini
 */
public class DatabaseOperations {

    private static String driver;
    private static String url;
    private static String user;
    private static String password;


    static {
        Properties properties = new Properties();
        InputStream is = DatabaseOperations.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("spring.datasource.driverClassName");
        url = properties.getProperty("spring.datasource.url");
        user = properties.getProperty("spring.datasource.username");
        password = properties.getProperty("spring.datasource.password");
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn 数据库连接
     * @param pst  Statement
     * @param rs   结果姐
     */
    public static void closeAll(Connection conn, Statement pst, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pst != null) {
            try {
                pst.close();
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

    /**
     * 数据库查询
     *
     * @param sql  数据库语句
     * @param args 对象集
     * @return 查询结果集
     */
    public static ResultSet exQuery(String sql, Object[] args) {
        ResultSet sum = null;
        PreparedStatement pstm;
        try {
            pstm = DatabaseOperations.getConnection().prepareStatement(sql);
            if (null != args) {
                for (int i = 0; i < args.length; i++) {
                    pstm.setObject(i + 1, args[i]);
                }
            }
            sum = pstm.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    /**
     * 数据库更新
     *
     * @param sql  数据库语句
     * @param args 对象集
     * @return 更新影响条数
     */
    public static int exUpdate(String sql, Object[] args) {
        int sum = 0;
        PreparedStatement pstm = null;
        try {
            pstm = DatabaseOperations.getConnection().prepareStatement(sql);
            if (null != args) {
                for (int i = 0; i < args.length; i++) {
                    pstm.setObject(i + 1, args[i]);
                }
            }
            sum = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseOperations.closeAll(null, pstm, null);
        }
        return sum;
    }


}