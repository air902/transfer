package com.chedilong.event.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = { "/manage/AdminIndex.jsp","/manage/AdminMenu.jsp","/manage/AdminPlayerInFo.jsp", "/manage/AdminPlayerInform.jsp","/manage/AdminTransferInFo.jsp" })
public class AdminLoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();
        String flag = (String)session.getAttribute("isAdminLogin");
            if(flag != null && flag.equals("1")){
                chain.doFilter(req,resp);
            }else{
                PrintWriter out=response.getWriter();
                out.write("<script>");
                out.write("alert('请先登录再访问！');");
                out.write("location.href='/transfer_war_exploded/manage/Login.jsp'");
                out.write("</script>");
                return;
            }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
