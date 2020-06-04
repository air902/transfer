package com.chedilong.event.servlet.User;

import com.chedilong.event.entity.Player;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.service.impl.PlayerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/AdminPlayerInformFindServlet")
public class AdminPlayerInformFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当前页
        int currentPage = 1;
        //每页显示记录条数
        int count = 5;
        String cp = request.getParameter("cp");
        if(cp != null){
            currentPage = Integer.valueOf(cp);
        }
        PlayerService playerService = new PlayerServiceImpl();
        //获取被举报用户的总记录数和总页数
        int[] arr = playerService.informTotalPage(count);
        //获取当前页的举报信息
        List<Player> playerList = playerService.informFind(currentPage,count);
        HttpSession session = request.getSession();
        session.setAttribute("playerList",playerList);
        session.setAttribute("totalRecord",arr[0]);
        session.setAttribute("totalPage",arr[1]);
        session.setAttribute("currentPage",currentPage);
        response.sendRedirect("/transfer_war_exploded/manage/AdminPlayerInform.jsp");
    }
}
