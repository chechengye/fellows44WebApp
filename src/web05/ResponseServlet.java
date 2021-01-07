package web05;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用response对象操作响应行
        resp.setStatus(500);//伪造状态码
        //使用response操作响应头  add方法可以添加多个相同的key
        resp.addHeader("name","lisi");
        resp.addIntHeader("age",34);
        resp.addDateHeader("birthday",new Date().getTime());
        resp.addHeader("name","zhaoliu");
        //set方法 保证key的唯一性，也可以确定值
        resp.setHeader("name","wangwu");

        //原始设定重定向方式
        //resp.setStatus(302);
        //resp.setHeader("Location" , "/quickStartHttp");

        //推荐的使用的重定向方案
        //resp.sendRedirect("/index.html");
        //处理中文乱码问题
        resp.setContentType("text/html;charset=utf-8");
        //向前端浏览器响应内容
        resp.getWriter().write("响应测试");
    }
}
