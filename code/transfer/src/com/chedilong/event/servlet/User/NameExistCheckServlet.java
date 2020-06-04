package com.chedilong.event.servlet.User;

import com.chedilong.event.service.ManagerService;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.service.impl.ManagerServiceImpl;
import com.chedilong.event.service.impl.PlayerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 查询用户注册或修改的账号是否被注册
 */
@WebServlet("/NameExistCheckServlet")
public class NameExistCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //获取用户输入账号
        String accountTxt = request.getParameter("accountTxt");
        //查询该账号是否被注册
        int result = 0;
        PlayerService playerService = new PlayerServiceImpl();
        result = playerService.accountExistCheck(accountTxt);
        ManagerService managerService = new ManagerServiceImpl();
        result += managerService.accountExistCheck(accountTxt);
        //返回查询结果
        PrintWriter out = response.getWriter();
        if(result>0){
            //该账号已被注册
            out.print("false");
        }else{
            //该账号未被注册
            out.print("true");
        }
    }
}
