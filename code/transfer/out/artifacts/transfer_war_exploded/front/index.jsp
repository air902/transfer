<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/8
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>英雄联盟转会系统</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/proList.css"/>
</head>
<body><!------------------------------head------------------------------>
<%@include file="Header.jsp"%>
<!------------------------------banner------------------------------>
<div class="banner"><a href="#"><img src="img/temp/bzbig.jpg"/></a></div>
<!-----------------address------------------------------->
<div class="address">
    <div class="wrapper clearfix"><a href="index.jsp">${searchClassify}</a>
    </div>
</div><!-------------------current---------------------->
<!----------------proList------------------------->
<ul class="proList wrapper clearfix">
    <c:forEach var="t" items="${transferInFoList}">
        <li><a href="${pageContext.request.contextPath}/DetailObtainServlet?playerId=${t.playerId}">
            <dl>
                <dt><img src="../picture/portrait/${t.portrait}"></dt>
                <dd>【LPL选手】${t.playerName}</dd>
                <dd>【分类】${t.classify}</dd>
                <dd>【年费】￥${t.price}</dd>
            </dl>
        </a></li>
    </c:forEach>
</ul><!----------------mask------------------->
<div class="msk"></div><!--footer-->
<div class="footer">
    <div class="top">
        <div class="wrapper">
            <div class="list-page">
                共 ${totalRecord} 条记录，当前${currentPage}/${totalPage} 页
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=${searchClassify}&name=${searchName}&cp=1&sub=查询">首页</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=${searchClassify}&name=${searchName}&cp=${currentPage-1<1?1:currentPage-1}&sub=查询">上一页</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=${searchClassify}&name=${searchName}&cp=${currentPage+1>totalPage?totalPage:currentPage+1}&sub=查询">下一页</a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=${searchClassify}&name=${searchName}&cp=${totalPage}&sub=查询">尾页</a>
            </div>
                  </div>
        </div>
    </div>
    <p class="dibu">LPL<br/>
       英雄联盟选手转会系统</p></div>

<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>