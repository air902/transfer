<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/9
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="zuo fl">
    <h3><a href="#"><img src="../picture/portrait/${user.portrait}" width="50" height="50"/></a>
        <p class="clearfix"><span class="fl">[${user.name}]</span><span>[<a href="${pageContext.request.contextPath}/FrontUserLogoutServlet">退出登录</a>]</span></p></h3>
    <div>
        <h4>个人中心</h4>
        <ul>
            <li><a href="PlayerInFoUpdate.jsp">个人信息修改</a></li>
        </ul>
        <c:if test="${isLogin == '1'}">
            <h4>转会信息</h4>
            <ul>
                <li><a href="${pageContext.request.contextPath}/TransferObtainServlet?userId=${user.id}">转会信息</a></li>
            </ul>
        </c:if>
        <c:if test="${isLogin == '2'}">
            <h4>我的队员</h4>
            <ul>
                <li><a href="#">管理队员</a></li>
            </ul>
        </c:if>
    </div>
</div>