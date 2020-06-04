<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/2
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "AdminMenu.jsp"%>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="AdminIndex.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">选手信息管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="${pageContext.request.contextPath}/manage/AdminPlayerInFoFindServlet" method="get">
                    <table class="search-tab">
                        <tr>
                            <th width="120">用户状态:</th>
                            <td>
                                <select name="accountStatus" value = "${searchAccountStatus}" id="">
                                    <option value="${searchAccountStatus}">${searchAccountStatus}</option>
                                    <c:if test="${searchAccountStatus!='全部'}"><option value="全部">全部</option></c:if>
                                    <c:if test="${searchAccountStatus!='审核中'}"><option value="审核中">审核中</option></c:if>
                                    <c:if test="${searchAccountStatus!='审核通过'}"><option value="审核通过">审核通过</option></c:if>
                                    <c:if test="${searchAccountStatus!='封禁'}"><option value="封禁">封禁</option></c:if>
                                </select>
                            </td>
                            <th width="70">用户姓名：</th>
                            <td><input class="common-text" placeholder="" name="playerName" value="${searchName}" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>账号</th>
                            <th>头像</th>
                            <th>姓名</th>
                            <th>年龄</th>
                            <th>简介</th>
                            <th>上一效力战队</th>
                            <th>加入时间</th>
                            <th>邮箱</th>
                            <th>审核状态</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="p" items="${playerList}">
                            <tr>
                                <td title="account"><a target="_blank" href="#" title="account">${p.account}</a></td>
                                <td><img src="../picture/portrait/${p.portrait}" width="80" height="80"></td>
                                <td>${p.name}</td>
                                <td>${p.age}</td>
                                <td>${p.introduction}</td>
                                <td>${p.lastTeam}</td>
                                <td>${p.joinDate}</td>
                                <td>${p.email}</td>
                                <td>${p.accountStatus}</td>
                                <td>
                                    <!--判断信息表的状态为审核中时-->
                                    <c:if test="${p.accountStatus=='审核中'}">
                                        <a class="link-update" href="javascript:userWarn('你确定通过用户【${p.name}】的账号申请吗?','${pageContext.request.contextPath}/manage/AdminThroughApplicationServlet?throughPlayer=${p.account}&accountStatus=${searchAccountStatus}&playerName=${searchName}&cp=${currentPage}&sub=查询')">通过申请</a>
                                        <a class="link-del" href="javascript:userWarn('你确定驳回用户【${p.name}】的账户信息申请吗?','${pageContext.request.contextPath}/manage/AdminPlayerBanServlet?banPlayer=${p.id}&accountStatus=${searchAccountStatus}&playerName=${searchName}&cp=${currentPage}&operation=审核不通过&sub=查询')">驳回</a>
                                    </c:if>
                                    <c:if test="${p.accountStatus=='审核不通过'}">
                                        <a class="link-update" href="javascript:userWarn('你确定通过用户【${p.name}】的账号申请吗?','${pageContext.request.contextPath}/manage/AdminThroughApplicationServlet?throughPlayer=${p.account}&accountStatus=${searchAccountStatus}&playerName=${searchName}&cp=${currentPage}&sub=查询')">通过申请</a>
                                    </c:if>
                                    <!--判断信息表状态为审核通过时   /manager/AdminPlayerBanServlet-->
                                    <c:if test="${p.accountStatus== '审核通过'}">
                                        <a class="link-del" href="javascript:userWarn('你确定封禁用户【${p.name}】吗?','${pageContext.request.contextPath}/manage/AdminPlayerBanServlet?banPlayer=${p.id}&accountStatus=${searchAccountStatus}&playerName=${searchName}&cp=${currentPage}&operation=封禁&sub=查询')">封禁</a>
                                    </c:if>
                                    <!--判断信息表的状态为审核不通过时-->
                                    <c:if test="${p.accountStatus == '封禁'}">
                                        <a class="link-update" href="javascript:userWarn('你确定解封用户【${p.name}】吗?','${pageContext.request.contextPath}/manage/AdminThroughApplicationServlet?throughPlayer=${p.account}&accountStatus=${searchAccountStatus}&playerName=${searchName}&cp=${currentPage}&sub=查询')">解封</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>

                        <script>
                            function userWarn(mess,url){
                                if(confirm(mess)){
                                    location.href=url;
                                }
                            }
                        </script>


                    </table>
                    <div class="list-page">
                        共 ${totalRecord} 条记录，当前${currentPage}/${totalPage} 页
                        <a href="${pageContext.request.contextPath}/manage/AdminPlayerInFoFindServlet?accountStatus=${searchAccountStatus}&playerName=${searchName}&cp=1&sub=查询">首页</a>
                        <a href="${pageContext.request.contextPath}/manage/AdminPlayerInFoFindServlet?accountStatus=${searchAccountStatus}&playerName=${searchName}&cp=${currentPage-1<1?1:currentPage-1}&sub=查询">上一页</a>
                        <a href="${pageContext.request.contextPath}/manage/AdminPlayerInFoFindServlet?accountStatus=${searchAccountStatus}&playerName=${searchName}&cp=${currentPage+1>totalPage?totalPage:currentPage+1}&sub=查询">下一页</a>
                        <a href="${pageContext.request.contextPath}/manage/AdminPlayerInFoFindServlet?accountStatus=${searchAccountStatus}&playerName=${searchName}&cp=${totalPage}&sub=查询">尾页</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>