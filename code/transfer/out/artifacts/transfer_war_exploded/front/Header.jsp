<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/8
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="head">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top"><h1 class="fl"><a href="index.jsp"><img src="img/logo.png" width="80" height="80"/></a></h1>
            <div class="fr clearfix" id="top1"><p class="fl">
                <c:if test="${isLogin == null}">
                    <a href="Login.jsp" id="login">登录</a><a href="Register.jsp" id="reg">玩家注册</a>
                </c:if>
                <c:if test="${isLogin =='1'}">
                    你好:<a href="UserInFo.jsp" id="login">${user.name}</a>
                </c:if>
                <c:if test="${isLogin =='2'}">
                    你好:<a href="UserInFo.jsp" id="login">${user.name}</a>
                </c:if>
            </p>
                <form action="${pageContext.request.contextPath}/FrontTransferObtainServlet" method="get" class="fl">

                    <input type="text" name="name" value="${searchName}" placeholder="搜索选手"/>
                    <input  type="submit">
                    <input type="hidden" name="classify" value="${searchClassify}">
                </form>
                <div class="btn fl clearfix">
                    <!--判断用户是否登录，若未登录，则点击会跳转登录界面-->
                    <c:if test="${isLogin == '1' || isLogin == '2'}">
                        <a href="UserInFo.jsp"><img src="img/grzx.png"/></a>
                    </c:if>
                    <c:if test="${isLogin == null}">
                        <a href="Login.jsp"><img src="img/grzx.png"/></a>
                    </c:if>
                </div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=全部&cp=1" name="classify">全部选手</a></li>
            <li><a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=上路&cp=1" name="classify">上路</a></li>
            <li><a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=打野&cp=1" name="classify">打野</a></li>
            <li><a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=中路&cp=1" name="classify">中路</a></li>
            <li><a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=下路&cp=1" name="classify">下路</a></li>
            <li><a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=辅助&cp=1" name="classify">辅助</a></li>
        </ul>
    </div>
</div>
