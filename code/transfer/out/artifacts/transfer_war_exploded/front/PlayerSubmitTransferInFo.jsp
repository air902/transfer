<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/10
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
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
    <div class="wrapper clearfix"><a href="index.jsp" class="fl">首页</a><span>/</span><a href="mygxin.html" class="on">个人信息</a>
    </div>
</div><!------------------------------Bott------------------------------>
<div class="Bott">
    <div class="wrapper clearfix">
        <%@include file="memu.jsp"%>
        <div class="you fl"><h2>提交/修改转会信息</h2>
            <form action="#" method="get" onsubmit="return putin()" class="remima"><p><span>转会信息：</span></p>
                <p><span>类型：</span>
                    <select name="classify" value = "" id="classifyId">
                            <c:if test="${transferInFoUpdate.classify==''}">
                                <option value="上路">上路</option>
                                <option value="打野">打野</option>
                                <option value="中路">中路</option>
                                <option value="下路">下路</option>
                                <option value="辅助">辅助</option>
                            </c:if>
                            <c:if test="${transferInFoUpdate.classify!=''}">
                                <option value="${transferInFoUpdate.classify}">${transferInFoUpdate.classify}</option>
                                <c:if test="${transferInFoUpdate.classify!='上路'}"><option value="上路">上路</option></c:if>
                                <c:if test="${transferInFoUpdate.classify!='打野'}"><option value="打野">打野</option></c:if>
                                <c:if test="${transferInFoUpdate.classify!='中路'}"><option value="中路">中路</option></c:if>
                                <c:if test="${transferInFoUpdate.classify!='下路'}"><option value="下路">下路</option></c:if>
                                <c:if test="${transferInFoUpdate.classify!='辅助'}"><option value="辅助">辅助</option></c:if>
                            </c:if>
                    </select>
                </p>
                <p>
                    <span>年薪（元）：</span>
<%--                    <input type="text" name="price" value="0" id="priceId"><span></span>--%>
                    <input type="text" name="price" value="${transferInFoUpdate.price}" id="priceId">
                </p>
                <p>
                    <button type="button" class="ui_btn ui_org_btn" onclick="putin() " id="tel_btn" style="height: 38px;width: 70px">提交</button>
                </p>
<%--                <p><input type="submit" value="提交"/></p>--%>

                <script>
                    function putin() {
                        $.ajax({
                            type:"get",
                            url:"${pageContext.request.contextPath}/TransferAddUpdateServlet?"+new Date().getTime()+"",
                            data:{"transferClassify":document.getElementById("classifyId").value,
                                  "transferPrice":document.getElementById("priceId").value},
                            success:function(data){
                                if(data == "000") {
                                    alert("你已加入战队，无法再次提交转会申请！");
                                }else if(data == "001") {
                                    alert("请检查你输入的年薪是否符合要求！");
                                }else if(data == "002"){
                                    alert("提交转会信息成功，正在等待管理员审核！");
                                }else if(data == "003"){
                                    alert("提交转会信息失败，请重新尝试！");
                                }else if(data == "007"){
                                    alert("您的账号未审核通过或被封禁，暂时无法进行该操作！");
                                }
                            },
                            error:function () {
                                alert("服务器未响应，修改失败！");
                            }
                        });
                    }
                </script>

            </form>
        </div>
    </div>
</div><!--返回顶部-->




<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>