<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 2021/1/8
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<!--
    contentType : 标记上下文的内容的，和设定字符集/编码格式
    language : 语言 ，默认java ，写的话只能写java
    session : 设定整个页面是否允许使用会话技术
    import : 导入文件目录
    errorPage : 指定错误页面，若本页面出现异常，会内容转发方式跳转到该页面
    isErrorPage : 标记页面是否为处理错误的页面
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" errorPage="/web06/error.jsp" %>
<html>
<head>
    <title>JSP讲解</title>
</head>
<body>
    <%

        //局部的
        int i = 10;
        /*public int method(){ //方法中不允许再定义方法了
            System.out.println("method is called!!!");
        }*/
    %>
    <!--测试-->
    <div>这是html代码</div>
    <%
        System.out.println(i);
    %>
    <%=i%>
    <%=25 * 3%>
    <%=str%>
    <%!
        String str = "abc";//成员变量
        public void method(){
            System.out.println("method is called!!!");
        }

    %>

    <%--
        JSP注释
    --%>
    <%=str%>
    <%
        method();
        List<String> list = new ArrayList<>();
        int arr = 10 / 0;
    %>
</body>
</html>
