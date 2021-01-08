package web06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取/创建一个session对象
        HttpSession session = req.getSession();
        session.setAttribute("name","lucy");

        Cookie cookie = new Cookie("JSESSIONID" , session.getId());
        cookie.setMaxAge(30*60);
        resp.addCookie(cookie);

        //session的生命周期  默认时长30分钟可以自行设定
        /*销毁：
        1、服务器异常关闭。若是正常关闭的话，检查是否有session存在，若存在主动的写入磁盘
        2、session过期/失效（默认30分钟）
        3、手动调用invalidate()
        */
        session.invalidate();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req , resp);
    }
}
