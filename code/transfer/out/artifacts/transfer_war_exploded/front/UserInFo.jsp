<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/8
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/mygxin.css"/>
</head>
<body><!------------------------------head------------------------------>
<%@include file="Header.jsp"%>
<!------------------------------idea------------------------------>
<div class="address mt" id="add">
    <div class="wrapper clearfix"><a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=全部&cp=1" class="fl">首页</a><span>/</span><a href="#" class="on">个人中心</a>
    </div>
</div><!------------------------------Bott------------------------------>
<div class="Bott">
    <div class="wrapper clearfix">
       <%@include file="memu.jsp"%>
        <div class="you fl">
            <div class="tx clearfix">
                <div class="fl clearfix"><a href="#" class="fl"><img src="../picture/portrait/${user.portrait}" width="85" height="85"/></a>
                    <p class="fl"><span>${user.name}</span><a href="PlayerInFoUpdate.jsp">修改个人信息></a></p></div>
                <div class="fr">绑定邮箱：${user.email}</div>
            </div>
        </div>
    </div>
</div>
<!--返回顶部-->
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>