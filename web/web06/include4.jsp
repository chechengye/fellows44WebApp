<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 2021/1/8
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
This is include4
    <!--动态包含  推荐使用 -->
    <jsp:include page="include3.jsp"></jsp:include>
    <!--内部转发的方式-->
    <jsp:forward page="include2.jsp"></jsp:forward>
</body>
</html>
