package com.chedilong.event.servlet.User;

import com.chedilong.event.entity.Player;
import com.chedilong.event.entity.TransferInFo;
import com.chedilong.event.entity.TransferPicture;
import com.chedilong.event.service.TransferInFoService;
import com.chedilong.event.service.TransferPictureService;
import com.chedilong.event.service.impl.TransferInFoServiceImpl;
import com.chedilong.event.service.impl.TransferPictureServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 玩家修改个人转会信息界面获取获取用户的转会信息
 */
@WebServlet("/TransferObtainServlet")
public class TransferObtainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        int playerId = Integer.valueOf(request.getParameter("userId"));
        //获取用户的转会信息以及高光时刻照片
        TransferInFoService transferInFoService = new TransferInFoServiceImpl();
        TransferInFo transferInFo = transferInFoService.detailObtain(playerId);
        if(transferInFo == null){
            transferInFo = new TransferInFo();
            transferInFo.setClassify("");
            transferInFo.setPrice(BigDecimal.valueOf(0));
        }
        TransferPictureService transferPictureService = new TransferPictureServiceImpl();
        List<TransferPicture> playerTransferPicture = transferPictureService.detailObtain(playerId);
        HttpSession session = request.getSession();
        session.setAttribute("transferInFoUpdate",transferInFo);
        session.setAttribute("playerTransferPicture",playerTransferPicture);
        response.sendRedirect("/transfer_war_exploded/front/PlayerSubmitTransferInFo.jsp");
    }
}
