package com.chedilong.event.servlet.User;

import com.chedilong.event.entity.Player;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.service.TransferInFoService;
import com.chedilong.event.service.impl.PlayerServiceImpl;
import com.chedilong.event.service.impl.TransferInFoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/AdminPlayerInFoFindServlet")
public class AdminPlayerInFoFindServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当前页
        int currentPage = 1;
        //每页显示记录条数
        int count = 5;
        //获取jsp传过来的用户指定的页面，搜索关键词：账户状态，玩家姓名
        String cp = request.getParameter("cp");
        String accountStatus = request.getParameter("accountStatus");
        String playerName = request.getParameter("playerName");

        //AdminThroughApplicationServlet通过session的值
        HttpSession sessionServlet = request.getSession();
        if((String)sessionServlet.getAttribute("cp") != null){
            cp = (String)sessionServlet.getAttribute("cp");
            sessionServlet.setAttribute("cp",null);
        }
        if((String)sessionServlet.getAttribute("accountStatus") != null){
            accountStatus = (String)sessionServlet.getAttribute("accountStatus");
            sessionServlet.setAttribute("accountStatus",null);
        }
        if((String)sessionServlet.getAttribute("playerName") != null){
            playerName = (String)sessionServlet.getAttribute("playerName");
            sessionServlet.setAttribute("playerName",null);
        }

        if(cp != null){
            currentPage = Integer.valueOf(cp);
        }
        if(accountStatus == null){
            accountStatus = "全部";
        }

        TransferInFoService transferInFoService = new TransferInFoServiceImpl();
        PlayerService playerService = new PlayerServiceImpl();
        //获取符合要求的总记录数和总页数
        int[] arr = playerService.playerTotalPage(count,accountStatus,playerName);
        //获取当前页的转会信息
        List<Player> playerList = playerService.playerInFoFind(currentPage,count,accountStatus,playerName);

        HttpSession session = request.getSession();
        session.setAttribute("playerList",playerList);
        session.setAttribute("totalRecord",arr[0]);
        session.setAttribute("totalPage",arr[1]);
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("searchName",playerName);
        session.setAttribute("searchAccountStatus",accountStatus);
        response.sendRedirect("/transfer_war_exploded/manage/AdminPlayerInFo.jsp");
    }
}
