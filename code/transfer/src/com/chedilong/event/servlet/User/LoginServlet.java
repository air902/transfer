package com.chedilong.event.servlet.User;

import com.chedilong.event.entity.Manager;
import com.chedilong.event.entity.Player;
import com.chedilong.event.service.ManagerService;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.service.impl.ManagerServiceImpl;
import com.chedilong.event.service.impl.PlayerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            String userName = request.getParameter("name");
            String password = request.getParameter("password");
            String rank = request.getParameter("rank");

            if(rank.equals("玩家")){
                //玩家登录
                PlayerService playerService=new PlayerServiceImpl();
                Player result = playerService.login(userName,password);
                HttpSession session=request.getSession();
                if(result!=null){
                    if(result.getAccountStatus().equals("封禁")){
                        //标记玩家账号被封禁
                        session.setAttribute("isNotLogin","2");
                        response.sendRedirect("/transfer_war_exploded/front/Login.jsp");
                    }else{
                        session.setAttribute("user",result);
                        //标记位，标记玩家登录成功
                        session.setAttribute("isLogin","1");
                        response.sendRedirect("/transfer_war_exploded/FrontTransferObtainServlet");
                    }
                }else{
                        session.setAttribute("isNotLogin", "1");
                        response.sendRedirect("/transfer_war_exploded/front/Login.jsp");
                }
            }else {
                //战队管理层和超级管理员登录
                ManagerService managerService = new ManagerServiceImpl();
                Manager result = managerService.login(userName, password, rank);

                if (result != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", result);
                    if(result.getRank().equals("超级管理员")){
                        //标记位，标记超级管理员登录成功
                        session.setAttribute("isAdminLogin", "1");
                        response.sendRedirect("/transfer_war_exploded/manage/AdminIndex.jsp");
                    }else if(result.getRank().equals("战队管理层")){
                        //标记位，标记战队管理层登录成功
                        session.setAttribute("isLogin", "2");
                        response.sendRedirect("/transfer_war_exploded/FrontTransferObtainServlet");
                    }
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("isNotLogin", "1");
                    if(rank.equals("超级管理员")){
                        response.sendRedirect("/transfer_war_exploded/manage/Login.jsp");
                    }else{
                        response.sendRedirect("/transfer_war_exploded/front/Login.jsp");
                    }
                }
            }
    }

}
