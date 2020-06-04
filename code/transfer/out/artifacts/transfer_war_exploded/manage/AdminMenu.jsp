<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/2
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8"/>
    <title>转会系统后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="AdminIndex.jsp" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="AdminIndex.jsp">首页</a></li>
                <li><a href="../front/index.jsp" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="/transfer_war_exploded/manage/AdminLogoutServlet">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="/transfer_war_exploded/manage/AdminPlayerInFoFindServlet"><i class="icon-font">&#xe008;</i>选手信息管理</a></li>
                        <li><a href="/transfer_war_exploded/manage/AdminDoTransferFindServlet"><i class="icon-font">&#xe005;</i>转会信息管理</a></li>
                        <li><a href="/transfer_war_exploded/manage/AdminPlayerInformFindServlet"><i class="icon-font">&#xe005;</i>举报信息管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
