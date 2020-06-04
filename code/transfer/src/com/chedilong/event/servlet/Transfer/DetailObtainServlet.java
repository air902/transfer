package com.chedilong.event.servlet.Transfer;

import com.chedilong.event.entity.Player;
import com.chedilong.event.entity.TransferInFo;
import com.chedilong.event.entity.TransferPicture;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.service.TransferInFoService;
import com.chedilong.event.service.TransferPictureService;
import com.chedilong.event.service.impl.PlayerServiceImpl;
import com.chedilong.event.service.impl.TransferInFoServiceImpl;
import com.chedilong.event.service.impl.TransferPictureServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/DetailObtainServlet")
public class DetailObtainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerIdString = request.getParameter("playerId");
        Integer playerId = Integer.valueOf(playerIdString);
        PlayerService playerService = new PlayerServiceImpl();
        TransferInFoService transferInFoService = new TransferInFoServiceImpl();
        TransferPictureService transferPictureService = new TransferPictureServiceImpl();
        //分别获取玩家详细信息，转会信息，转会信息的图片
        Player playerDetail = playerService.detailObtain(playerId);
        TransferInFo transferInFoDetail = transferInFoService.detailObtain(playerId);
        List<TransferPicture> transferPictureList = transferPictureService.detailObtain(playerId);

        //将信息添加到session中
        HttpSession session = request.getSession();
        session.setAttribute("playerDetail",playerDetail);
        session.setAttribute("transferInFoDetail",transferInFoDetail);
        session.setAttribute("transferPictureList",transferPictureList);
        response.sendRedirect("/transfer_war_exploded/front/Detail.jsp");
    }
}
