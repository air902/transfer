<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/2
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file = "AdminMenu.jsp"%>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="AdminIndex.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">转会信息管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="${pageContext.request.contextPath}/manage/AdminDoTransferFindServlet" method="get">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="classify" value = "" id="">
                                    <option value="${searchClassify}">${searchClassify}</option>
                                    <c:if test="${searchClassify!='全部'}"><option value="全部">全部</option></c:if>
                                    <c:if test="${searchClassify!='上路'}"><option value="上路">上路</option></c:if>
                                    <c:if test="${searchClassify!='打野'}"><option value="打野">打野</option></c:if>
                                    <c:if test="${searchClassify!='中路'}"><option value="中路">中路</option></c:if>
                                    <c:if test="${searchClassify!='下路'}"><option value="下路">下路</option></c:if>
                                    <c:if test="${searchClassify!='辅助'}"><option value="辅助">辅助</option></c:if>
                                </select>
                            </td>
                            <th width="70">用户姓名:</th>
                            <td><input class="common-text"  name="searchName" value="${searchName}" id="" type="text"></td>
                            <td><input class="intro" type="hidden" name="cp" value=1 /></td>
                            <td><input class="btn btn-primary btn2"  name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>表id</th>
                            <th>选手id</th>
                            <th>选手姓名</th>
                            <th>转会费用</th>
                            <th>分类</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="t" items="${transferInFoList}">
                                <tr>
                                    <td>${t.id}</td>
                                    <td>${t.playerId}</td>
                                    <td>${t.playerName}</td>
                                    <td>${t.price}</td>
                                    <td>${t.classify}</td>
                                    <td>${t.status}</td>
                                    <td>
                                        <!--判断信息表的状态为审核中时-->
                                        <c:if test="${t.status=='审核中'}">
                                            <a class="link-update" href="javascript:userWarn('你确定通过用户【${t.playerName}】的转会信息申请吗?','${pageContext.request.contextPath}/manage/AdminTransferPassServlet?transferPlayerId=${t.playerId}&classify=${searchClassify}&searchName=${searchName}&cp=${currentPage}&operation=审核通过&sub=查询')">通过申请</a>
                                            <a class="link-del" href="javascript:userWarn('你确定驳回用户【${t.playerName}】的转会信息申请吗?','${pageContext.request.contextPath}/manage/AdminTransferBanServlet?transferPlayerId=${t.playerId}&classify=${searchClassify}&searchName=${searchName}&cp=${currentPage}&operation=审核不通过&sub=查询')">驳回</a>
                                        </c:if>
                                        <!--判断信息表状态为审核通过时-->
                                        <c:if test="${t.status== '审核通过'}">
                                            <a class="link-del" href="javascript:userWarn('你确定撤销通过用户【${t.playerName}】的转会信息申请吗?','${pageContext.request.contextPath}/manage/AdminTransferBanServlet?transferPlayerId=${t.playerId}&classify=${searchClassify}&searchName=${searchName}&cp=${currentPage}&operation=审核不通过&sub=查询')">撤销通过</a>
                                        </c:if>
                                        <a class="link-del" href="javascript:userWarn('你确定删除用户【${t.playerName}】的转会信息申请吗?','${pageContext.request.contextPath}/manage/AdminInformDeleteServlet?transferId=${t.id}&classify=${searchClassify}&searchName=${searchName}&cp=${currentPage}&sub=查询')">删除</a>


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
                        <a href="${pageContext.request.contextPath}/manage/AdminDoTransferFindServlet?classify=${searchClassify}&playerName=${searchName}&cp=1&sub=查询">首页</a>
                        <a href="${pageContext.request.contextPath}/manage/AdminDoTransferFindServlet?classify=${searchClassify}&playerName=${searchName}&cp=${currentPage-1<1?1:currentPage-1}&sub=查询">上一页</a>
                        <a href="${pageContext.request.contextPath}/manage/AdminDoTransferFindServlet?classify=${searchClassify}&playerName=${searchName}&cp=${currentPage+1>totalPage?totalPage:currentPage+1}&sub=查询">下一页</a>
                        <a href="${pageContext.request.contextPath}/manage/AdminDoTransferFindServlet?classify=${searchClassify}&playerName=${searchName}&cp=${totalPage}&sub=查询">尾页</a>
                </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>