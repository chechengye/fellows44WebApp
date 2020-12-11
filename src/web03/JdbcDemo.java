package web03;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import web02.util.JdbcUtil;
import web03.pojo.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class JdbcDemo {

    /**
     * 根据一个id查询一个订单
     */
    @Test
    public void testFn() throws SQLException {
        MyConnectionDemo mcd = new MyConnectionDemo();
        Connection conn = mcd.getConnection();
        //编写sql语句
        PreparedStatement psmt = conn.prepareStatement("select o.oid,o.o_no,o.create_time from t_order o where oid = ?");// * ： 查询所有字段 ， 而之后做项目时通常不建议使用
        psmt.setInt(1 , 2);
        ResultSet rs = psmt.executeQuery();
        if(rs.next()){
            System.out.println("订单号: " + rs.getString("o_no") + " , 创建日期： " + rs.getString("create_time"));//数据库中一切皆串
        }
        mcd.releaseConn(conn);
        JdbcUtil.releaseRes(rs , psmt , null);
    }
    /**
     * 根据一个id删除一个订单
     */
    @Test
    public void testFn1() throws SQLException {
        Connection conn = C3p0Demo.getConnection();//连接使用完毕之后会自动收回
        PreparedStatement pstm = conn.prepareStatement("delete from t_order where oid = ?");
        pstm.setInt(1 , 1);
        int rows = pstm.executeUpdate();
        if (0 < rows) {
            System.out.println("删除订单成功!");
        }else{
            System.out.println("删除失败！");
        }
        JdbcUtil.releaseRes(null , pstm , null);
    }

    /**
     * 插入一条订单
     */
    @Test
    public void testFn2() throws SQLException {
        Connection conn = DbcpDemo.getConnection();
        PreparedStatement psmt = conn.prepareStatement("insert into t_order VALUES (null , ? , ?)");
        psmt.setString(1 , "Zdjshdjs23323232");
        long time = new java.util.Date().getTime();
        psmt.setDate(2 , new Date(time));
        int rows = psmt.executeUpdate();
        if(0 < rows){
            System.out.println("插入成功!");
        }else{
            System.out.println("插入失败!");
        }
    }

    /**
     * 获取所有用户
     */
    @Test
    public void testFn3() throws SQLException {
        QueryRunner qr = new QueryRunner(C3p0Demo.getDataSource());//使用C3P0数据库连接池
        /**
         * ResultHandler中
         * BeanListHandler<User> : 自动映射封装对象集合，要求对象的属性名称与查询的字段名称保持一致
         * BeanHandler : 获取一个对象时，通常查询一个对象（登录、查询）/修改回显时使用
         * ScalarHandler : 获取到一个long型值
         */
        //给字段起别名 列名 as 别名 （其中as可以省略不写）
        List<User> userList = qr.query("select u.id , u.username as username1 , u.password , u.sex from t_user u", new BeanListHandler<>(User.class));
        userList.forEach(System.out::println);
        System.out.println("------------------------------");
        User user = qr.query("select u.id , u.username as username1 , u.password from t_user u where id = ?", new BeanHandler<>(User.class), 4);
        System.out.println(user);
        //long : 数据库中数据条数可能很大，5000万条以上数据
        long l = (long)qr.query("select count(*) from t_user", new ScalarHandler());
        System.out.println("数量为 : " + l);
    }
}
