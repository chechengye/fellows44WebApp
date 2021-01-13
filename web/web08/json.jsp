<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 2021/1/13
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSON案例</title>
</head>

<script>
    //1、{"key":obj,"key1":obj2}
    var dog = {"name":"旺财","month":12};
    alert(dog.name)
    //2、[{key:val,key1:val},{key:val}]
    var cat = [{"name":"猫咪","color":"黑色"},{"name":"小花"}];
    for(var i = 0 ; i < cat.length ; i++){
        alert(cat[i].name)
    }
    alert(cat[0].color)
    //3、{key:val , key1:[{},{}]}
    var wxb = {"name":"韦小宝","wife":[{"name":"建宁","height":165},{"name":"阿珂","age":20}]};
    alert(wxb.name)
    alert(wxb.wife[1].name)
    //4、{key:[],key1:[],key2:[]}
    var hd = {"cz":[{"name":"多隆"},{"name":"韦小宝"}],"fz":[{"name":"妃子1"},{"name":"妃子2"}]};
    alert(hd.cz[0].name)
    //5、{key:val,key1:{},key2:[{},{}],key3:val2}
    var orders = {"code":200,"order":{"ono":"dgshdg6376732","createTime":"2021-1-13","amount":"63.31"}
                ,"products":[{"name":"手机","price":"2600"},{"name":"电脑","price":3657.9}],"message":"成功"};
    alert(orders.order.ono)

</script>
<body>

</body>
</html>
