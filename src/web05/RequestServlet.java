package web05;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/request")
public class RequestServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //使用req对象操作请求行
        String method = req.getMethod();//获取请求方式
        System.out.println("method  = " + method);
        String uri = req.getRequestURI();//获取项目相对路径的（路由）
        StringBuffer url = req.getRequestURL();//访问全路径
        System.out.println("uri = " + uri + ", url = " + url);
        System.out.println("contextPath = " + req.getContextPath());
        //编写重定向代码时建议前面加上req.getContextPath()
        //resp.sendRedirect(req.getContextPath() + "/login");
        /*
        //获取GET方式请求的携带参数
        System.out.println("param = " + req.getQueryString());
        resp.getWriter().write(req.getQueryString());*/

        System.out.println("address = " + req.getRemoteAddr());

        //通过req操作请求头信息
        System.out.println("head = " + req.getHeader("User-Agent"));
        //获取所有的请求头名称
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            System.out.println(headerNames.nextElement());
        }

        String referer = req.getHeader("referer");
        System.out.println("referer = " + referer);
        /*if(referer != null && referer.startsWith("http://localhost:8081")){
            System.out.println("是内部请求");
            //详情页跳转
        }else{
            System.out.println("内部做防盗链了，不允许访问！");
        }*/

        //req获取请求体的内容 取任意请求方式
        System.out.println("name = " + req.getParameter("name"));
        String[] hobbies = req.getParameterValues("hobby");
        System.out.println("hobby" + Arrays.toString(hobbies));
        System.out.println("-----------获取所有参数名称---------");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()){
            System.out.println(parameterNames.nextElement());
        }

        System.out.println("--------------获取前端所有表单参数自动封装为map------------");
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for(Map.Entry<String, String[]> e : entries){
            for(String s : e.getValue()){
                System.out.println(e.getKey() + " -> " + s);
            }
        }

        System.out.println("-------------内部转发------------");
        /*RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.html");
        requestDispatcher.forward(req , resp);*/
        req.setAttribute("user" , "张三");
        req.getRequestDispatcher("/servletContextDemo").forward(req , resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
