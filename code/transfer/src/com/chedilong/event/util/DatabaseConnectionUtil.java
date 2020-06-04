package com.chedilong.event.util;

import java.sql.*;
import java.util.ResourceBundle;

public class DatabaseConnectionUtil {
    /**
     * 工具类中的 构造方法 都是私有的
     * 因为工具类中的方法都是静态的，不需要new对象，直接采用类名调用
     */
    private DatabaseConnectionUtil(){}

    //绑定属性配置资源文件
    static ResourceBundle bunder = ResourceBundle.getBundle("UserData");
    static String driver = bunder.getString("driver");
    static String url = bunder.getString("url");
    static String user = bunder.getString("user");
    static String password = bunder.getString("password");

    //静态代码块在类加载时执行，并且只执行一次
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *获取数据库连接对象
     * @return 数据库连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url,user,password);
    }

    /**
     * 关闭资源
     * @param con 连接对象
     * @param pstmt 数据库操作对象
     * @param rs 结果集
     */
    public static void close(Connection con, Statement pstmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}