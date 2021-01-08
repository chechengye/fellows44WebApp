package web05;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import web03.C3p0Demo;
import web05.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req , resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            System.out.println("--------");
            //解决前端传递中文乱码问题
            req.setCharacterEncoding("utf-8");
            String username = req.getParameter("username");
            System.out.println("LoginServlet : " + username);
            String password = req.getParameter("password");
            QueryRunner qr = new QueryRunner(C3p0Demo.getDataSource());
            User user = qr.query("select * from t_user u where u.username = ? and u.password = ? and u.is_delete != 1"
                    , new BeanHandler<>(User.class), username, password);
            if(null != user){
                System.out.println("登录成功！");
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                Cookie cookie = new Cookie("JSESSIONID" , session.getId());
                cookie.setMaxAge(60*60);
                resp.addCookie(cookie);
                resp.sendRedirect("/index.html");
            }else{
                System.out.println("登录失败！");
                req.setAttribute("loginInfo" ,"用户名或密码错误！");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
