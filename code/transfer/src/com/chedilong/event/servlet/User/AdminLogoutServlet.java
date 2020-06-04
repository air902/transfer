package com.chedilong.event.servlet.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/manage/AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("isAdminLogin");

        PrintWriter out=response.getWriter();
        out.write("<script>");
        out.write("alert('退出成功！');");
        out.write("location.href='/transfer_war_exploded/front/index.jsp'");
        out.write("</script>");
    }
}
