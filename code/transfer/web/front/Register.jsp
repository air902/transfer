<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/7
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script src="js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="js/md5.js"></script>
    <script src="js/function.js"></script>

    <style>
        p .error1{
            display: inline-block;
            border: 1px solid #bd0a01;
            background-color: #ffe8e0;
            height: 25px;
            line-height: 25px;
            padding: 0px 20px;
            margin-left: 20px;
        }
    </style>
</head>
<body><!-------------------reg-------------------------->
<div class="reg">
    <form action="${pageContext.request.contextPath}/front/PlayerRegisterServlet" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this)"><h1><a href="index.jsp"><img src="img/temp/logo.png"></a></h1>
        <h1 style="padding: 0px;margin: 0px;font-size: 30px;background: #3344AA;text-align: center;line-height: 40px;color: #FFFFFF">玩家注册</h1>
        <p><input type="text" name="accountTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入账号"><span></span></p>
        <p>
            <input type="text"  name="passwordCheck" value="" id="input_pwd" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入密码" class="admin_input_style" /><span></span>
            <input type="hidden" name="passwordTxt"  id = 'md5_pwd' value=""/>
        </p>
        <p>请上传头像：<input class="common-text require" name="portraitTxt" id="portraitTxt" type="file"></p>
        <p><input type="text" name="nameTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入姓名"><span></span></p>
        <p><input type="text" name="ageTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入年龄"><span></span></p>
        <p><input type="text" name="introductionTxt" onfocus="FocusItem(this)" onblur="CheckItem(this)" value="" placeholder="请输入简介"><span></span></p>
        <p><input type="text" name="lastTeamTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入加入的上一战队"><span></span></p>
        <p>请选择加入LPL的时间:  <input type="date" name="joinDateTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请选择加入LPL的时间"><span></span></p>
        <p><input type="text" name="emailTxt" value="" id="emailId" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入邮箱"><span></span></p>
        <p>
            <input type="text" name="verifyCodeTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请填写邮箱验证码">
            <button type="button" class="ui_btn ui_org_btn" onclick="sendEmailCode() " id="tel_btn" style="height: 30px">获取验证码</button>
            <span></span>
        </p>
        <p><input type="submit" name="" value="注册"></p>

        <p class="txt"><a href="Login.jsp"><span></span>已有账号登录</a></p>
        <p class="txt"><a href="index.jsp"><span></span>回到主页面</a></p>
        <a href="#" class="off"><img src="img/temp/off.png"></a>
<%--        用户密码加密--%>
<%--        <script>--%>
<%--            function encrypt(){--%>
<%--                var input_pwd = document.getElementById("input_pwd");--%>
<%--                var md5_pwd = document.getElementById("md5_pwd");--%>
<%--                md5_pwd.value = hex_md5(input_pwd.value);--%>
<%--                return true;--%>
<%--            }--%>
<%--        </script>--%>
<%--        获取邮箱验证码，10s倒计时--%>
        <script>
            function sendEmailCode() {
                $.ajax({
                    type:"get",
                    url:"${pageContext.request.contextPath}/EmailCodeServlet?"+new Date().getTime()+"",
                    data:{"emailAddress":document.getElementById("emailId").value},
                    success:function(data){
                        if(data == "true"){
                            alert("验证码发送成功！");
                            var x=10;
                            var btn = $("#tel_btn");
                            function countSecond()
                            {
                                if(x>0) {
                                    x = x-1;
                                    btn.addClass("disabled");
                                    btn.attr("disabled", true);
                                    btn.html("重新发送(" + x + ")");
                                    setTimeout(function(){
                                        countSecond();
                                    },1000);
                                }else{
                                    btn.attr("disabled", false);
                                    btn.html("重新获取验证码");
                                    btn.removeClass("disabled");
                                }
                            }
                            countSecond();
                        }else{
                            alert("验证码发送失败，请检查你的邮箱地址是否正确！");
                        }
                    },
                    error:function () {
                        alert("验证码发送失败，请重试！");
                    }
                });
            }
        </script>

        <%--鼠标点击，清空span--%>
        <script>
            function FocusItem(obj) {
                if($(obj).attr('name') == 'verifyCodeTxt'){
                    $(obj).next().next('span').html('').removeClass('error1');
                }
                $(obj).next('span').html('').removeClass('error1');
            }
        </script>
        <%--鼠标离开，检查用户输入值--%>
        <script>
            function CheckItem(obj) {
                var messageBox = $(obj).next('span');
                switch ($(obj).attr('name')) {
                    case "accountTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('账号不能为空');
                            messageBox.addClass('error1');
                            flag = false;
                        }else{
                            $.ajax({
                                type:"get",
                                url:"${pageContext.request.contextPath}/NameExistCheckServlet?"+new Date().getTime()+"",
                                data:{"accountTxt":encodeURI($(obj).val())},
                                success:function(data){
                                    if(data == "false") {
                                        messageBox.html('该账号已被注册');
                                        messageBox.addClass('error1');
                                        flag = false;
                                    }else{
                                        messageBox.html().removeClass('error1');
                                        // flag = true;
                                    }
                                }
                            });
                        }
                        break;
                    case "passwordCheck":
                        if(obj.value.trim() == ""){
                            messageBox.html('密码不能为空');
                            messageBox.addClass('error1');
                            flag = false;
                        }else{
                            var input_pwd = document.getElementById("input_pwd");
                            var md5_pwd = document.getElementById("md5_pwd");
                            md5_pwd.value = hex_md5(input_pwd.value);
                            // flag = true;
                        }
                        break;
                    case "nameTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('姓名不能为空');
                            messageBox.addClass('error1');
                            flag = false;
                        }else{
                            // flag = true;
                        }
                        break;
                    case "ageTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('年龄不能为空');
                            messageBox.addClass('error1');
                            flag = false;
                        }else{
                            var reg = /^(([0-9]|[1-9][1-9]|1[0-7][0-9])(\\.[0-9]+)?|180)$/;
                            if (!reg.test(obj.value)){
                                messageBox.html('请输入正确的年龄');
                                messageBox.addClass('error1');
                                flag = false;
                            }else{
                                messageBox.html().removeClass('error1');
                                // flag = true;
                            }
                        }
                        break;
                    case "introductionTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('简介不能为空');
                            messageBox.addClass('error1');
                            flag = false;
                        }else {
                            // flag = true;
                        }
                        break;
                    case "lastTeamTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('加入的上一战队不能为空');
                            messageBox.addClass('error1');
                            flag = false;
                        }else {
                            // flag = true;
                        }
                        break;
                    case "joinDateTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('加入LPL时间不能为空');
                            messageBox.addClass('error1');
                            flag = false;
                        }else {
                            // flag = true;
                        }
                        break;
                    case "emailTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('邮箱地址不能为空');
                            messageBox.addClass('error1');
                            flag = false;
                        }else{
                            var reg =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                            if (!reg.test(obj.value)){
                                messageBox.html('请输入正确的邮箱地址');
                                messageBox.addClass('error1');
                                flag = false;
                            }else{
                                messageBox.html().removeClass('error1');
                                // flag = true;
                            }
                        }
                        break;
                    case "verifyCodeTxt":
                        var codeMessage = $(obj).next().next();
                        if(obj.value.trim() == ""){
                            codeMessage.html('验证码不能为空');
                            codeMessage.addClass('error1');
                            flag = false;
                        }else{
                            $.ajax({
                                type:"get",
                                url:"${pageContext.request.contextPath}/CodeCheckServlet?"+new Date().getTime()+"",
                                data:{"codeTxt":encodeURI($(obj).val())},
                                success:function(data){
                                    if(data == "false") {
                                        codeMessage.html('验证码输入错误');
                                        codeMessage.addClass('error1');
                                        flag = false;
                                    }else{
                                        codeMessage.html().removeClass('error1');
                                        // flag = true;
                                    }
                                }
                            });
                        }
                        break;
                }
            }
        </script>

        <script>
            function checkForm(frm) {
                var els = frm.getElementsByTagName('input');
                flag = true;
                for(var i=0 ;i<els.length ;i++){
                    if(els[i] != null){
                        if(els[i].getAttribute("onblur")){
                            CheckItem(els[i]);
                        }
                    }
                }
                return flag;
            }
        </script>
    </form>
</div>
</body>
</html>