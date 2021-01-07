package web05;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/quickStartHttp")
public class QuickStartHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);//统一代码处理出口
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost is called!!!");
        ServletContext servletContext = this.getServletContext();
        System.out.println(servletContext.getInitParameter("contentConfig"));
        System.out.println(servletContext.getRealPath("WEB-INF/test.html"));
        //向web域中存值
        servletContext.setAttribute("name","zhangsan");
    }


}
