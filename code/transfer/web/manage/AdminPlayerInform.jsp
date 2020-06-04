<%--
  Created by IntelliJ IDEA.
  User: ko
  Date: 2020/5/2
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file = "AdminMenu.jsp"%>
<!--用户举报处理-->
<div class="main-wrap">
    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i><a href="AdminIndex.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">举报信息管理</span></div>
    </div>
            <div class="result-content">
                <td><input class="intro" type="hidden" name="cp" value=1 /></td>
                <table class="result-tab" width="100%">
                    <tr>
                        <th>用户id</th>
                        <th>用户账号</th>
                        <th>用户姓名</th>
                        <th>举报理由</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach var="p" items="${playerList}">
                        <tr>
                            <td>${p.id}</td>
                            <th>${p.account}</th>
                            <td>${p.name}</td>
                            <td>${p.reason}</td>
                            <td>
                                <a class="link-del" href="javascript:userWarn('你确定封禁用户【${p.name}】吗?','${pageContext.request.contextPath}/manage/AdminInformPlayerBanServlet?banPlayer=${p.id}&cp=${currentPage}&operation=封禁&sub=查询')">封禁</a>
                                <a class="link-update" href="javascript:userWarn('你确定驳回用户【${p.name}】举报吗?','${pageContext.request.contextPath}/manage/AdminInformPlayerThroughServlet?throughPlayer=${p.account}&cp=${currentPage}&operation=审核同过&sub=查询')">驳回</a>
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
                    <a href="${pageContext.request.contextPath}/manage/AdminPlayerInformFindServlet?cp=1">首页</a>
                    <a href="${pageContext.request.contextPath}/manage/AdminPlayerInformFindServlet?cp=${currentPage-1<1?1:currentPage-1}">上一页</a>
                    <a href="${pageContext.request.contextPath}/manage/AdminPlayerInformFindServlet?cp=${currentPage+1>totalPage?totalPage:currentPage+1}">下一页</a>
                    <a href="${pageContext.request.contextPath}/manage/AdminPlayerInformFindServlet?cp=${totalPage}">尾页</a>
                </div>
            </div>
        </form>
    </div>
</div>
<!--/main-->
</div>
</body>
</html>
