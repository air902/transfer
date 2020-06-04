package com.chedilong.event.servlet.User;

import com.chedilong.event.dao.PlayerDao;
import com.chedilong.event.dao.impl.PlayerDaoImpl;
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

/**
 * 用户文字信息的修改操作
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        //获取用户修改信息
        String accountTxt = request.getParameter("accountTxt");
        String nameTxt = request.getParameter("nameTxt");
        String ageTxtString = request.getParameter("ageTxt");
        int ageTxt = Integer.valueOf(ageTxtString);
        String emailTxt = request.getParameter("emailTxt");
        String introductionTxt;
        String lastTeamTxt;
        String joinDateTxt;
        String teamTxt;

        if("1".equals((String)session.getAttribute("isLogin"))){
            //封装玩家修改个人信息
            Player playerLogin = (Player)session.getAttribute("user");
            int playerId = playerLogin.getId();
            introductionTxt = request.getParameter("introductionTxt");
            lastTeamTxt  = request.getParameter("lastTeam");
            joinDateTxt  = request.getParameter("joinDate");
            Player playerTxt = new Player(playerId,accountTxt,null,null,nameTxt,ageTxt,introductionTxt,
                    lastTeamTxt,joinDateTxt,emailTxt,null,null,null,null);
            //修改玩家信息
            PlayerService playerService = new PlayerServiceImpl();
            Player result = playerService.playerUpdate(playerTxt);
            if(result != null){
                session.setAttribute("user",result);
                PrintWriter out=response.getWriter();
                out.print("<script language='javascript'>alert('修改成功，正在等待管理员审核！');window.location.href='/transfer_war_exploded/front/PlayerInFoUpdate.jsp';</script>");
            }else{
                PrintWriter out=response.getWriter();
                out.print("<script language='javascript'>alert('修改失败，请重新尝试！');window.location.href='/transfer_war_exploded/front/PlayerInFoUpdate.jsp';</script>");
            }


        }else if("2".equals((String)session.getAttribute("isLogin"))){
            //封装战队管理层修改的个人信息
            Manager managerLogin = (Manager)session.getAttribute("user");
            int managerId = managerLogin.getId();
            teamTxt = request.getParameter("teamTxt");
            Manager managerTxt = new Manager(managerId,accountTxt,null,null,
                                      nameTxt,ageTxt,null,null,teamTxt,emailTxt);
            //修改战队管理层信息
            ManagerService managerService = new ManagerServiceImpl();
            Manager result = managerService.managerUpdate(managerTxt);
            if(result != null){
                session.setAttribute("user",result);
                PrintWriter out=response.getWriter();
                out.print("<script language='javascript'>alert('修改成功！');window.location.href='/transfer_war_exploded/front/PlayerInFoUpdate.jsp';</script>");
            }else{
                PrintWriter out=response.getWriter();
                out.print("<script language='javascript'>alert('修改失败，请重新尝试！');window.location.href='/transfer_war_exploded/front/PlayerInFoUpdate.jsp';</script>");
            }


        }
    }

}
