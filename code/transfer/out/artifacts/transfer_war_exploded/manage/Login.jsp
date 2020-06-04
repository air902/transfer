<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/2
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8"/>
    <title>LPL转会系统后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/admin_login.css"/>
    <script type="text/javascript" src="js/md5.js"></script>
</head>
<body>
<div class="admin_login_wrap">
    <h1>后台管理</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post" onsubmit="return encrypt()">
                <ul class="admin_items">
                    <li>
                        <label for="user">账号：</label>
                        <input type="text" name="name" value="" id="user" size="40" class="admin_input_style" />
                        <input type="hidden" name="rank"  value="超级管理员"/>
                    </li>
                    <li>
                        <label for="input_pwd">密码：</label>
                        <input type="password"  value="" id="input_pwd" size="40" class="admin_input_style" />
                        <input type="hidden" name="password"  id = 'md5_pwd' value=""/>
                    </li>
                    <li>
                        <input type="submit" tabindex="3" value="提交" class="btn btn-primary" />
                    </li>
                </ul>
                <script>
                    function encrypt(){
                        var input_pwd = document.getElementById("input_pwd");
                        var md5_pwd = document.getElementById("md5_pwd");
                        md5_pwd.value = hex_md5(input_pwd.value);
                    }
                </script>
            </form>
        </div>
    </div>
    <p class="admin_copyright"><a tabindex="5" href="../front/index.jsp" target="_blank">返回首页</a>
</div>
<script type="text/javascript" language="javascript">
    <c:if test="${isNotLogin == '1'}">
    alert("用户名或密码错误，登录失败！");
    <% session.removeAttribute("isNotLogin");%>
    </c:if>
</script>
</body>
</html>