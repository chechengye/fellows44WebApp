package web01;

import org.junit.Test;

import java.sql.*;

/**
 * JDBC技术应用
 */
public class JdbcDemo {

    @Test
    public void testFn() throws ClassNotFoundException {
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fellows44", "root", "root");
            //3、获取/创建 Statement对象，用来执行sql语句
            Statement st = conn.createStatement();
            //4、编写sql语句
            String sql = "select * from t_user";
            //5、调用Statement的api来执行编译sql语句，若查询则获取到结果集对象
            ResultSet rs = st.executeQuery(sql);
            //6、遍历结果集
            while (rs.next()){//索引依次指向下一个，若没有值则返回false否则返回一行记录 。列索引从1开始 与 自增的id一样
                System.out.println("用户名 :" + rs.getString("username") + ",密码:" + rs.getString(3) + ",性别: " + rs.getInt("sex"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
