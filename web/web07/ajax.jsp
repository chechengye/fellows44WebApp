<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 2021/1/11
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax学习</title>
</head>
<script type="text/javascript">
    function testFn() {
        //JS发起Ajax请求
        var xmlhttp;//ajax引擎对象
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
        } else {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        //2、建立监听
        xmlhttp.onreadystatechange = function () {
            //3、确定服务端响应成功
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
                alert(xmlhttp.responseText);
            }
        }
        //3、建立服务请求
        xmlhttp.open("GET" , "${pageContext.request.contextPath}/ajax?name=zhangsan" , true);
        //4、发送请求
        xmlhttp.send();
    }

    function testPostFn() {
        //JS发起Ajax请求
        var xmlhttp;//ajax引擎对象
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
        } else {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        //2、建立监听
        xmlhttp.onreadystatechange = function () {
            //3、确定服务端响应成功
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
                alert(xmlhttp.responseText);
            }
        }
        //3、建立服务请求
        xmlhttp.open("POST" , "${pageContext.request.contextPath}/ajax" , false);
        xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        //4、发送请求
        xmlhttp.send("name=wangwu");


    }

</script>
<body>
    <input type="button" value="JS异步请求" onclick="testFn()"/>
    <input type="button" value="JS异步POST请求" onclick="testPostFn()"/>

    <input type="button" value="测试" onclick="alert('test')"/>
</body>
</html>
