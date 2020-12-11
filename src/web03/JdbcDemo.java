package web03;

import org.junit.Test;
import web02.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
