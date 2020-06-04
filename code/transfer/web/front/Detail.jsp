<%@ page import="com.chedilong.event.entity.Player" %><%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/8
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>详情页</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/proList.css"/>
</head>
<body><!------------------------------head------------------------------>
<%@include file="Header.jsp"%>
<!-----------------address------------------------------->
<div class="address">
    <div class="wrapper clearfix">
        <a href="${pageContext.request.contextPath}/FrontTransferObtainServlet?classify=全部&cp=1">首页</a><span>/</span>
        <a href="#">【${transferInFoDetail.classify}】${playerDetail.name}</a>
    </div>
</div><!-----------------------Detail------------------------------>
<div class="detCon">
    <div class="proDet wrapper">
        <div class="proCon clearfix">
            <div class="proImg fl"><img class="det" src="../picture/portrait/${playerDetail.portrait}"/>
                <div class="smallImg clearfix">
                    <img src="../picture/portrait/${playerDetail.portrait}" data-src="../picture/portrait/${playerDetail.portrait}">
                </div>
            </div>
            <div class="fr intro">
                <div class="title">
                    <h4>【选手分类】${transferInFoDetail.classify}</h4>
                    <h4>【选手姓名】${playerDetail.name}</h4>
                    <h4>【选手简介】${playerDetail.introduction}</h4>
                    <h4>【加入LPL时间】${playerDetail.joinDate}</h4>
                    <h4>【效力的上一战队】${playerDetail.lastTeam}</h4>
                    <span>【年费】￥${transferInFoDetail.price}</span>
                    <h4>----------------------------------------------------</h4>
                    <h4>                                          </h4>
                    <h4>                                          </h4>
                    <h4>                                          </h4>
                    <h4>                                          </h4>
                    <h4>                                          </h4>
                </div>

                <div class="btns clearfix">
<%--                    ${pageContext.request.contextPath}/ManagerInvitePlayerServlet?managerId=${user.id}?playerId=${playerDetail.id}--%>
                    <a href="javascript:invite('你确定要邀请选手【${playerDetail.name}】加入你的战队吗？','${pageContext.request.contextPath}/ManagerInvitePlayerServlet?managerId=${user.id}&playerId=${playerDetail.id}')" )><p class="buy fl">邀请加入</p></a>
                    <a href="javascript:invite('你确定要举报选手【${playerDetail.name}】吗？','${pageContext.request.contextPath}/UserChargeServlet?managerId=${user.id}&playerId=${playerDetail.id}')"><p class="cart fr">举报选手</p></a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="introMsg wrapper clearfix">
    <div class="msgL fl">
        <div class="msgTit clearfix"><a class="on">选手高光时刻</a></div>
            <div class="msgAll">
            <div class="msgImgs">
                <c:forEach var="t" items="${transferPictureList}">
                    <img src="../picture/transferPicture/${t.picture}">
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.SuperSlide.2.1.1.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="js/cart.js" type="text/javascript" charset="utf-8"></script>

<script>
    function invite(mess,url) {
        if(${isLogin=='2'}){
            if(confirm(mess)){
                location.href=url;
            }else{
                return false;
            }
        }else if(${isLogin == '1'}){
            alert("你不是战队管理层，无法进行该操作！");
            return false;
        }else{
            alert("请先登录后再进行操作！");
            return false;
        }
    }
</script>


<script type="text/javascript">jQuery(".bottom").slide({
    titCell: ".hd ul",
    mainCell: ".bd .likeList",
    autoPage: true,
    autoPlay: false,
    effect: "leftLoop",
    autoPlay: true,
    vis: 1
});</script>
</body>
</html>