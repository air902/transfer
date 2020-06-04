<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/9
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%--用户个人信息的更新--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/mygrxx.css"/>
</head>
<body><!------------------------------head------------------------------>
<%@include file="Header.jsp"%>
<!------------------------------idea------------------------------>
<div class="address mt">
    <div class="wrapper clearfix"><a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=全部&cp=1" class="fl">首页</a><span>/</span><a href="#" class="on">个人信息</a>
    </div>
</div><!------------------------------Bott------------------------------>
<div class="Bott">
    <div class="wrapper clearfix">
        <%@include file="memu.jsp"%>
        </div>
        <div class="you fl"><h2>个人信息</h2>
            <div class="gxin">
                <div class="tx"><a href="#"><img src="../picture/portrait/${user.portrait}" width="85" height="85"/>
                    <p id="avatar">修改头像</p></a>
                </div>
                <div class="xx"><h3 class="clearfix"><strong class="fl">基础资料</strong><a href="#" class="fr" id="edit1">编辑</a>
                </h3>
                    <div>账号：${user.account}</div>
                    <div>姓名：${user.name}</div>
                    <div>年龄：${user.age}</div>
                    <div>邮箱地址：${user.email}</div>
                    <c:if test="${user.rank=='玩家'}">
                        <div>简介：${user.introduction}</div>
                        <div>效力的上一战队：${user.lastTeam}</div>
                        <div>加入时间：${user.joinDate}</div>
                        <div>账户审核状态：${user.accountStatus}</div>
                        <div>战队状态：${user.teamStatus}</div>
                    </c:if>
                    <c:if test="${user.rank=='战队管理层'}">
                        <div>战队成员数量：${user.amount}</div>
                        <div>所属战队：${user.team}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div><!--遮罩-->
<div class="mask"></div><!--编辑弹框-->
<div class="bj">
    <div class="clearfix"><a href="#" class="fr gb"><img src="img/icon4.png"/></a></div>
    <h3>修改个人信息</h3>
    <form action="${pageContext.request.contextPath}/UserUpdateServlet" method="post">
        <%--        <p><label>性别：</label><span><input type="radio"/>男</span><span><input type="radio"/>女</span></p>--%>
            <p><label>账号：</label><input name="accountTxt" type="text" value="${user.account}"/></p>
            <p><label>姓名：</label><input name="nameTxt" type="text" value="${user.name}"/></p>
            <p><label></label></p>
            <p><label>年龄：</label><input name="ageTxt" type="text" value="${user.age}"/></p>
            <p><label>邮箱地址：</label><input name="emailTxt" type="text" value="${user.email}"/></p>
            <c:if test="${user.rank=='玩家'}">
                <p><label>简介：</label><input name="introductionTxt" type="text" value="${user.introduction}"></p>
                <p><label>效力的上一战队：</label><input name="lastTeam" type="text" value="${user.lastTeam}"></p>
                <p><label>加入日期：</label><input type="date" name="joinDate" value="${user.joinDate}"/></p>
            </c:if>
            <c:if test="${user.rank=='战队管理层'}">
                <p><label>所属战队：</label><input type="text" name="teamTxt" value="${user.team}"></p>
            </c:if>
        <div class="bc"><input type="submit" value="确认修改"/><input type="button" value="取消"/></div>
    </form>
</div>
<!--修改头像-->
<div class="avatar">
    <div class="clearfix"><a href="#" class="fr gb" >
        <img src="img/icon4.png"/></a></div>
    <h3>修改头像</h3>
    <form action="${pageContext.request.contextPath}/UserPortraitUpdateServlet" method="post" enctype="multipart/form-data">
        <h4>请上传图片</h4>
        <input type="file" value="上传头像" name="portraitTxt"/>
        <p>jpg或png或jpeg，大小不超过5M</p>
        <input type="submit" value="提交"/>
    </form>
</div><!--返回顶部-->

<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>