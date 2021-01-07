package web05;

import javax.servlet.*;
import java.io.IOException;

/**
 * 1、实现Servlet的接口
 * 2、重写它里面的所有抽象方法
 * 3、需要在web.xml文件中进行配置
 *  生命周期：
 *  当第一次访问时会调用一次init方法 (初始化配置的)
 *  每次访问都会调用service方法 （业务逻辑处理）
 *  当服务器关闭，servlet销毁时会调用destroy方法
 */
public class QuickStartServlet implements Servlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init is called !!!");
        System.out.println(servletConfig.getServletName());
        System.out.println(servletConfig.getInitParameter("key"));
        ServletContext servletContext = servletConfig.getServletContext();

    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service is called !!!");
        servletResponse.setContentType("text/html;charset=utf-8");
        servletResponse.getWriter().write("<html lang=\"en\">");
        servletResponse.getWriter().write("<head>");
        servletResponse.getWriter().write("<meta charset=\"UTF-8\">");
        servletResponse.getWriter().write("<title>fellows44 web首页</title>");
        servletResponse.getWriter().write("</head>");
        servletResponse.getWriter().write("<body>");
        servletResponse.getWriter().write("第一次部署项目，启动服务访问");
        servletResponse.getWriter().write("</body>");
        servletResponse.getWriter().write("</html>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy is called !!!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public String getServletInfo() {
        return null;
    }


}
