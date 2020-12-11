package web03;

import web02.util.JdbcUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 自定义的数据库连接池
 */
public class MyConnectionDemo implements DataSource{

    //创建了一个默认大小为20的池子
    private List<Connection> connectionList = new ArrayList<>(20);

    /**
     * 初始化连接池
     */
    public MyConnectionDemo(){
        for(int i = 0 ; i < 10 ; i ++){
            connectionList.add(JdbcUtil.getConnection());
        }
    }

    /**
     * 归还连接
     * @param conn
     */
    public void releaseConn(Connection conn){//conn.close();
        connectionList.add(conn);
    }
    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = connectionList.remove(0);
        if(0 == connectionList.size()){
            for(int i = 0 ; i < 10 ; i ++){
                connectionList.add(JdbcUtil.getConnection());
            }
        }
        return conn;
    }


    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
