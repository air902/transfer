<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/2
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script type="text/javascript" src="js/md5.js"></script>
</head>
<body><!-------------------login-------------------------->
<div class="login">
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post" onsubmit="return encrypt()"><h1><a href="index.jsp"><img src="img/temp/logo.png"></a></h1>
        <p></p>
        <div class="msg-warn hide"><b></b>公共场所不建议自动登录，以防账号丢失</div>
        <p><input type="text" name="name" value="" placeholder="账号"></p>
        <p>
            <input type="text"  value="" id="input_pwd" placeholder="密码" class="admin_input_style" />
            <input type="hidden" name="password"  id = 'md5_pwd' value=""/>
        </p>
        <p>
            <input style="width: 15px;height: 15px" type="radio" name="rank" value="玩家" checked="checked">玩家&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input style="width: 15px;height: 15px" type="radio" name="rank" value="战队管理层" checked="checked">战队管理层
        </p>
        <p><input type="submit" name="" value="登  录"></p>
        <p class="txt"><a class="" href="Register.jsp">免费注册</a><a href="index.jsp">回到主页面</a></p></form>
    <script>
        function encrypt(){
            var input_pwd = document.getElementById("input_pwd");
            var md5_pwd = document.getElementById("md5_pwd");
            md5_pwd.value = hex_md5(input_pwd.value);
        }
    </script>
</div>
<script type="text/javascript" language="javascript">
    <c:if test="${isNotLogin == '1'}">
        alert("用户名或密码或身份信息错误，登录失败！");
        <% session.removeAttribute("isNotLogin");%>
    </c:if>
    <c:if test="${isNotLogin == '2'}">
    alert("您的账号已被封禁，请联系管理员解封后登陆！");
    <% session.removeAttribute("isNotLogin");%>
    </c:if>
</script>
</body>
</html>