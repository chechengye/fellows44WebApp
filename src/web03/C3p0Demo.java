package web03;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * C3p0数据库连接池 -- 市场占有率极高  60%
 * 可以设计最大/最小的链接数量 、连接等待时间（超时时间）
 * 此连接池持续运行稳定性很好、在大量并发时(多线程下) 稳定性也有一定的保障。
 */
public class C3p0Demo {

    private static ComboPooledDataSource dataSource;//声明数据源

    static {
        //推荐使用配置文件方式
        dataSource = new ComboPooledDataSource();//默认去src下查找一个名字叫c3p0-config.xml
        /*try {

            //手动配置参数过程 -- 不推荐

            ResourceBundle rb = ResourceBundle.getBundle("db");
           dataSource.setDriverClass(rb.getString("jdbc.driver"));
            dataSource.setJdbcUrl(rb.getString("jdbc.url"));
            dataSource.setUser(rb.getString("jdbc.username"));
            dataSource.setPassword("root");

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }*/
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
