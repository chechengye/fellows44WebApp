package web01;

import org.junit.Test;
import web02.util.JdbcUtil;

import java.sql.*;

/**
 * JDBC技术应用
 */
public class JdbcDemo {

    @Test
    public void testFn() throws ClassNotFoundException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fellows44", "root", "root");
            //3、获取/创建 Statement对象，用来执行sql语句
            st = conn.createStatement();
            //4、编写sql语句
            String sql = "select * from t_user";
            //5、调用Statement的api来执行编译sql语句，若查询则获取到结果集对象
            rs = st.executeQuery(sql);
            //6、遍历结果集
            while (rs.next()){//索引依次指向下一个，若没有值则返回false否则返回一行记录 。列索引从1开始 与 自增的id一样
                System.out.println("用户名 :" + rs.getString("username") + ",密码:" + rs.getString(3) + ",性别: " + rs.getInt("sex"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    /**
     * 根据一个id主键修改一个用户名
     */
    @Test
    public void testFn1(){
        //1、加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fellows44?useUnicode=true&characterEncoding=utf-8", "root", "root");
            //3、获取/创建 Statement对象，用来执行sql语句
            Statement st = conn.createStatement();
            //4、编写sql语句，做修改操作
            String sql = "update t_user set username = 'zhaoliu' where id = 1";
            int rows = st.executeUpdate(sql);//rows:受影响的行数
            if(rows > 0){
                System.out.println("更新成功！");
            }else{
                System.out.println("更新失败！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
