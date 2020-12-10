package web02.util;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC工具类抽取
 */
public class JdbcUtil {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        driver = rb.getString("jdbc.driver");
        url = rb.getString("jdbc.url");
        username = rb.getString("jdbc.username");
        password = rb.getString("jdbc.password");
    }

    /**
     * 获取数据库连接对象
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url , username , password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     * @param rs
     * @param st
     * @param conn
     */
    public static void releaseRes(ResultSet rs , Statement st , Connection conn){
        //数据流关闭处理
        if(null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(null != st){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
