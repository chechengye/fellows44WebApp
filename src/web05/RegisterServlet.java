package web05;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import web03.C3p0Demo;
import web05.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            //处理中文乱码问题
            req.setCharacterEncoding("utf-8");
            //进行参数接收
            Map<String, String[]> parameterMap = req.getParameterMap();
            User user = new User();
            BeanUtils.populate(user , parameterMap);
            String[] hobbies = req.getParameterValues("hobby");
            /*String hh = "";
            for(String h : hobbies){
                hh += h + ",";
            }*/
            String hobby = Arrays.toString(hobbies);//[篮球,足球,乒乓球]
            //处理完毕复选结果之后进行重新赋值
            user.setHobby(hobby.substring(1 , hobby.length()-1));
            System.out.println("user = " + user);
            //存入数据库中
            QueryRunner qr = new QueryRunner(C3p0Demo.getDataSource());
            String sql = "insert into t_user values(?,?,?,?,?,?,?,?,?)";
            try {
                int rows = qr.update(sql, null,
                        user.getUsername(),
                        user.getPassword(),
                        user.getSex(),
                        user.getHobby(),
                        user.getEmail(),
                        user.getName(),
                        user.getBirthday(), 0);
                if(rows > 0){
                    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                }else{
                    req.getRequestDispatcher("/register.jsp").forward(req,resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
