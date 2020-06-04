package com.chedilong.event.servlet.User;

import com.chedilong.event.entity.Manager;
import com.chedilong.event.entity.Player;
import com.chedilong.event.service.ManagerService;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.service.RelationService;
import com.chedilong.event.service.impl.ManagerServiceImpl;
import com.chedilong.event.service.impl.PlayerServiceImpl;
import com.chedilong.event.service.impl.RelationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ManagerInvitePlayerServlet")
public class ManagerInvitePlayerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        int managerId = Integer.valueOf(request.getParameter("managerId"));
        int playerId = Integer.valueOf(request.getParameter("playerId"));
        //获取战队管理层及选手的详细信息
        ManagerService managerService = new ManagerServiceImpl();
        Manager manager = managerService.managerObtain(managerId);
        PlayerService playerService = new PlayerServiceImpl();
        Player player = playerService.detailObtain(playerId);
        //判断该战队管理层的队员是否大于等于5个
        if(5 <= manager.getAmount()){
            //人数超出限制，邀请失败
            HttpSession session = request.getSession();
            session.setAttribute("inviteFail",'1');
            PrintWriter out=response.getWriter();
            out.print("<script language='javascript'>alert('您的战队人数已达上限，邀请失败');window.location.href='/transfer_war_exploded/DetailObtainServlet?playerId="+playerId+"';</script>");
        }else{
            //人数符合要求
            if(player.getTeamStatus().equals("已加入战队")){
                PrintWriter out=response.getWriter();
                out.print("<script language='javascript'>alert('该选手已加入战队，邀请失败！');window.location.href='/transfer_war_exploded/DetailObtainServlet?playerId="+playerId+"';</script>");
            }else{
                //该选手未加入战队

                RelationService relationService = new RelationServiceImpl();
                boolean result = relationService.relationAdd(playerId,managerId);
                if(result){
                    //邀请成功
                    HttpSession session = request.getSession();
                    session.setAttribute("inviteSuccess",'1');
                    PrintWriter out=response.getWriter();
                    out.print("<script language='javascript'>alert('邀请成功！');window.location.href='/transfer_war_exploded/FrontTransferObtainServlet?classify=全部&cp=1';</script>");
                }else{
                    //后台操作出现错误，邀请失败  操作失败，请重新尝试
                    HttpSession session = request.getSession();
                    session.setAttribute("inviteFail",'2');
                    PrintWriter out=response.getWriter();
                    out.print("<script language='javascript'>alert('操作失败，请重新尝试!');window.location.href='/transfer_war_exploded/DetailObtainServlet?playerId="+playerId+"';</script>");
                }
            }



        }
    }
}
