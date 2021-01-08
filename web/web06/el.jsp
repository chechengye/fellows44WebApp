<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="web05.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 2021/1/8
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL学习</title>
</head>
<body>
    <%
        pageContext.setAttribute("name","tom");
        request.setAttribute("name","lily");
        User user = new User();
        user.setName("lucy");
        user.setSex(1);
        session.setAttribute("user",user);
        List<User> userList = new ArrayList<>();
        User user2 = new User();
        user2.setName("jack");
        user2.setSex(0);
        userList.add(user);
        userList.add(user2);
        application.setAttribute("userList", userList);
    %>

    <!--el语法从域中取值-->
    ${requestScope.name}<br/> <!--相当于request.getAttribute("name")-->
    ${sessionScope.user.name} <!--对象的属性决定key值  .可以不限制层数-->
    ${applicationScope.userList[1].name} <!--针对于集合进行取值操作-->
    <!--page域 < request域 < session域  < application域 -->
    ${name}<br/> <!--pageContext.findAttribute("name")-->

    <!--获取head信息-->
    ${header["User-Agent"]}
    <hr>
    <!--获取web应用上下文路径-->
    ${pageContext.request.contextPath} <!--相当于 request.getContextPath()-->
    <hr>
    <!--jstl应用-->
    <c:forEach begin="0" end="10" var="i"> <!--jstl的var变量。相当于向page域中存于key值了-->
        <c:if test="${i == 5}">
            ${i + 20}
        </c:if>
        <c:if test="${i != 5}">
            ${i}
        </c:if>
    </c:forEach>

    <hr>
    <c:forEach items="${userList}" var="user" varStatus="vs">
        编号：${vs.count}  &nbsp&nbsp 名称: ${user.name} <br>
    </c:forEach>
</body>
</html>
