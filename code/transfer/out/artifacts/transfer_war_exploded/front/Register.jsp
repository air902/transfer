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
    <title>ע��</title>
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
        <h1 style="padding: 0px;margin: 0px;font-size: 30px;background: #3344AA;text-align: center;line-height: 40px;color: #FFFFFF">���ע��</h1>
        <p><input type="text" name="accountTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="�������˺�"><span></span></p>
        <p>
            <input type="text"  name="passwordCheck" value="" id="input_pwd" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="����������" class="admin_input_style" /><span></span>
            <input type="hidden" name="passwordTxt"  id = 'md5_pwd' value=""/>
        </p>
        <p>���ϴ�ͷ��<input class="common-text require" name="portraitTxt" id="portraitTxt" type="file"></p>
        <p><input type="text" name="nameTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="����������"><span></span></p>
        <p><input type="text" name="ageTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="����������"><span></span></p>
        <p><input type="text" name="introductionTxt" onfocus="FocusItem(this)" onblur="CheckItem(this)" value="" placeholder="��������"><span></span></p>
        <p><input type="text" name="lastTeamTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="������������һս��"><span></span></p>
        <p>��ѡ�����LPL��ʱ��:  <input type="date" name="joinDateTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="��ѡ�����LPL��ʱ��"><span></span></p>
        <p><input type="text" name="emailTxt" value="" id="emailId" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="����������"><span></span></p>
        <p>
            <input type="text" name="verifyCodeTxt" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="����д������֤��">
            <button type="button" class="ui_btn ui_org_btn" onclick="sendEmailCode() " id="tel_btn" style="height: 30px">��ȡ��֤��</button>
            <span></span>
        </p>
        <p><input type="submit" name="" value="ע��"></p>

        <p class="txt"><a href="Login.jsp"><span></span>�����˺ŵ�¼</a></p>
        <p class="txt"><a href="index.jsp"><span></span>�ص���ҳ��</a></p>
        <a href="#" class="off"><img src="img/temp/off.png"></a>
<%--        �û��������--%>
<%--        <script>--%>
<%--            function encrypt(){--%>
<%--                var input_pwd = document.getElementById("input_pwd");--%>
<%--                var md5_pwd = document.getElementById("md5_pwd");--%>
<%--                md5_pwd.value = hex_md5(input_pwd.value);--%>
<%--                return true;--%>
<%--            }--%>
<%--        </script>--%>
<%--        ��ȡ������֤�룬10s����ʱ--%>
        <script>
            function sendEmailCode() {
                $.ajax({
                    type:"get",
                    url:"${pageContext.request.contextPath}/EmailCodeServlet?"+new Date().getTime()+"",
                    data:{"emailAddress":document.getElementById("emailId").value},
                    success:function(data){
                        if(data == "true"){
                            alert("��֤�뷢�ͳɹ���");
                            var x=10;
                            var btn = $("#tel_btn");
                            function countSecond()
                            {
                                if(x>0) {
                                    x = x-1;
                                    btn.addClass("disabled");
                                    btn.attr("disabled", true);
                                    btn.html("���·���(" + x + ")");
                                    setTimeout(function(){
                                        countSecond();
                                    },1000);
                                }else{
                                    btn.attr("disabled", false);
                                    btn.html("���»�ȡ��֤��");
                                    btn.removeClass("disabled");
                                }
                            }
                            countSecond();
                        }else{
                            alert("��֤�뷢��ʧ�ܣ�������������ַ�Ƿ���ȷ��");
                        }
                    },
                    error:function () {
                        alert("��֤�뷢��ʧ�ܣ������ԣ�");
                    }
                });
            }
        </script>

        <%--����������span--%>
        <script>
            function FocusItem(obj) {
                if($(obj).attr('name') == 'verifyCodeTxt'){
                    $(obj).next().next('span').html('').removeClass('error1');
                }
                $(obj).next('span').html('').removeClass('error1');
            }
        </script>
        <%--����뿪������û�����ֵ--%>
        <script>
            function CheckItem(obj) {
                var messageBox = $(obj).next('span');
                switch ($(obj).attr('name')) {
                    case "accountTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('�˺Ų���Ϊ��');
                            messageBox.addClass('error1');
                            flag = false;
                        }else{
                            $.ajax({
                                type:"get",
                                url:"${pageContext.request.contextPath}/NameExistCheckServlet?"+new Date().getTime()+"",
                                data:{"accountTxt":encodeURI($(obj).val())},
                                success:function(data){
                                    if(data == "false") {
                                        messageBox.html('���˺��ѱ�ע��');
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
                            messageBox.html('���벻��Ϊ��');
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
                            messageBox.html('��������Ϊ��');
                            messageBox.addClass('error1');
                            flag = false;
                        }else{
                            // flag = true;
                        }
                        break;
                    case "ageTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('���䲻��Ϊ��');
                            messageBox.addClass('error1');
                            flag = false;
                        }else{
                            var reg = /^(([0-9]|[1-9][1-9]|1[0-7][0-9])(\\.[0-9]+)?|180)$/;
                            if (!reg.test(obj.value)){
                                messageBox.html('��������ȷ������');
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
                            messageBox.html('��鲻��Ϊ��');
                            messageBox.addClass('error1');
                            flag = false;
                        }else {
                            // flag = true;
                        }
                        break;
                    case "lastTeamTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('�������һս�Ӳ���Ϊ��');
                            messageBox.addClass('error1');
                            flag = false;
                        }else {
                            // flag = true;
                        }
                        break;
                    case "joinDateTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('����LPLʱ�䲻��Ϊ��');
                            messageBox.addClass('error1');
                            flag = false;
                        }else {
                            // flag = true;
                        }
                        break;
                    case "emailTxt":
                        if(obj.value.trim() == ""){
                            messageBox.html('�����ַ����Ϊ��');
                            messageBox.addClass('error1');
                            flag = false;
                        }else{
                            var reg =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                            if (!reg.test(obj.value)){
                                messageBox.html('��������ȷ�������ַ');
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
                            codeMessage.html('��֤�벻��Ϊ��');
                            codeMessage.addClass('error1');
                            flag = false;
                        }else{
                            $.ajax({
                                type:"get",
                                url:"${pageContext.request.contextPath}/CodeCheckServlet?"+new Date().getTime()+"",
                                data:{"codeTxt":encodeURI($(obj).val())},
                                success:function(data){
                                    if(data == "false") {
                                        codeMessage.html('��֤���������');
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