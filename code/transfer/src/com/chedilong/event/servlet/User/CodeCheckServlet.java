package com.chedilong.event.servlet.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CodeCheckServlet")
public class CodeCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String codeTxt = request.getParameter("codeTxt");
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute("emailCode");
        PrintWriter out = response.getWriter();
        if (code.equals(codeTxt)) {
            out.print("true");
        }else{
            out.print("false");
        }
    }
}
