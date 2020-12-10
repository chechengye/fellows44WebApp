package web02.util;

import org.junit.Test;

import java.sql.*;

public class JdbcDemo {

    /**
     * 根据一个id删除一条记录
     */
    @Test
    public void testFn(){
        Connection conn = null;
        Statement st = null;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            String sql = "delete from t_user where id = 1";
            int rows = st.executeUpdate(sql);
            if(rows > 0){
                System.out.println("删除成功！");
            }else{
                System.out.println("删除失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.releaseRes(null , st , conn);
        }

    }

    @Test
    public void testFn1(){
        //or ： 关键字，表示或者 。可能会造成SQL片段的攻击
        login("lisi" , "1234");
    }
    /**
     * 登录功能
     */
    public void login(String username , String password){
        Connection conn = null;
        PreparedStatement psmt = null;//多态的自动向上转型
        //PreparedStatement : 可以防止SQL攻击 。Mybatis的底层使用这个对象 #{}
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from t_user where username = ? and password = ? ";
            psmt = conn.prepareStatement(sql);//sql语句预编译。更快、高效 .使用 ？：站位符的方式，做到防止SQL攻击
//st = conn.createStatement();
            //rs = psmt.executeQuery(sql);
            //Statement 对象，需要使用字符串拼接的方式，完成变量的输入
            psmt.setString(1 , username);//参数的索引从1开始
            psmt.setString(2 , password);
            rs = psmt.executeQuery();
            if(rs.next()){
                System.out.println("登录成功！");
                System.out.println("欢迎," + rs.getString("username"));
            }else{
                System.out.println("登录失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.releaseRes(rs , psmt , conn);
        }
    }
}
