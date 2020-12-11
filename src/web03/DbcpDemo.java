package web03;

import org.apache.commons.dbcp.BasicDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * DBCP连接池
 * 在多并发下（多线程）性能有所下降 。没有类似于C3P0那样配置文件。
 * 可以设定池子大小
 * */
public class DbcpDemo {

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        //手动配置参数过程(从属性文件中读取)
        ResourceBundle rb = ResourceBundle.getBundle("db");
        dataSource.setDriverClassName(rb.getString("jdbc.driver"));
        dataSource.setUrl(rb.getString("jdbc.url"));
        dataSource.setUsername(rb.getString("jdbc.username"));
        dataSource.setPassword("root");
    }

    /**
     * 从连接池获取连接
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
