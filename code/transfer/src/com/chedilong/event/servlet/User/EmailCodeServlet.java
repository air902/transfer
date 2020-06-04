package com.chedilong.event.servlet.User;

import com.chedilong.event.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *获取邮箱验证码
 */
@WebServlet("/EmailCodeServlet")
public class EmailCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //发送邮箱验证码
        String emailAddress = request.getParameter("emailAddress");
        System.out.println("EmailCodeServlet中获取到的邮箱地址为：" + emailAddress);
        String sendEmailCode = EmailUtil.sendEmail(emailAddress);
        if(sendEmailCode != null){
            //将获取验证码的邮箱以及邮箱验证码保存到session
            System.out.println("本次验证码为："+sendEmailCode);
            HttpSession session = request.getSession();
            PrintWriter out = response.getWriter();
            session.setAttribute("emailCode", sendEmailCode);
            session.setAttribute("getCodeEmail",emailAddress);
            out.print("true");
            out.close();
        }else{
            PrintWriter out = response.getWriter();
            out.print("false");
            out.close();
        }
    }
}
