package web06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieDemo")
public class CookieDemoServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理响应中文乱码问题
        resp.setContentType("text/html;charset=utf-8");
        //首先格式化好日期
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd+hh:mm:ss");
        Cookie cookie = new Cookie("lastTime" , sf.format(new Date()));
        cookie.setMaxAge(24 * 60 * 60 * 30);//记录日期一个月
        //添加浏览器
        resp.addCookie(cookie);
        //获取浏览器携带的Cookie
        Cookie[] cookies = req.getCookies();
        String lastTime = null;
        System.out.println("cookies " + cookies);
        if(null != cookies && cookies.length > 0){
            for(Cookie c : cookies){
                if(null != c){
                    if(c.getName().equals("lastTime")){
                        lastTime = c.getValue().replace("+" , " ");
                    }
                }
            }
        }
        if(null == lastTime){
            resp.getWriter().write("您是第一次访问");
        }else{
            resp.getWriter().write("您上次访问的时间为：" + lastTime);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }
}
