package web06;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import web03.C3p0Demo;
import web06.pojo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 商品列表管理
 */
@WebServlet("/productList")
public class ProductListServlet  extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            QueryRunner qr = new QueryRunner(C3p0Demo.getDataSource());
            List<Product> productList = qr.query("select * from product", new BeanListHandler<>(Product.class));
            System.out.println("productList = " + productList.size());
            req.setAttribute("productList" , productList);
            req.getRequestDispatcher("/product_list.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }
}
