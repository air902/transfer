package com.chedilong.event.servlet.User;

import com.chedilong.event.entity.Player;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.service.impl.PlayerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserChargeServlet")
public class UserChargeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        int managerId = Integer.valueOf(request.getParameter("managerId"));
        int playerId = Integer.valueOf(request.getParameter("playerId"));
        PlayerService playerService = new PlayerServiceImpl();
        Player player = playerService.detailObtain(playerId);
        PrintWriter out=response.getWriter();
        if(player.getAccountStatus().equals("被举报")){
            out.print("<script language='javascript'>alert('该用户已被举报！');window.location.href='/transfer_war_exploded/DetailObtainServlet?playerId="+playerId+"';</script>");
        }else{
            if(playerService.playerCharge(playerId) > 0){
                out.print("<script language='javascript'>alert('举报成功！');window.location.href='/transfer_war_exploded/DetailObtainServlet?playerId="+playerId+"';</script>");
            }
        }
    }
}
