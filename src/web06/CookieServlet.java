package web06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、创建Cookie
        Cookie cookie = new Cookie("age", "22");
        //3、增加cookie时效
        cookie.setMaxAge(10*60);//单位是秒
        //4、设定一个访问路径
        cookie.setPath(req.getContextPath() + "/cookie");
        //2、添加/或者发送到浏览器中
        //Set-Cookie 底层实现
        resp.addCookie(cookie);

        //清除已经存在的cookie
        /*Cookie cookie1 = new Cookie("age", "22");
        cookie1.setMaxAge(0);//单位是秒
        cookie1.setPath(req.getContextPath() + "/cookie");
        resp.addCookie(cookie1);*/
        Cookie cookie1 = new Cookie("name", "zhangsan");
        cookie1.setMaxAge(60);
        resp.addCookie(cookie1);

        //使用req对象获取客户端请求携带的cookie
        Cookie[] cookies = req.getCookies();
        for(Cookie c : cookies){
            if(null != c){
                if(c.getName().equals("name")){
                    resp.getWriter().write(c.getValue());
                }
            }
        }


    }
}
