package com.chedilong.event.servlet.Transfer;

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
import java.io.PrintWriter;

/**
 * 通过选手的转会信息申请
 */
@WebServlet("/manage/AdminTransferPassServlet")
public class AdminTransferPassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        int transferPlayerId = Integer.valueOf(request.getParameter("transferPlayerId"));
        String cp = request.getParameter("cp");
        String searchClassify = request.getParameter("classify");
        String searchName = request.getParameter("searchName");
        String operation = request.getParameter("operation");
        //到数据库更改转会信息
        TransferInFoService transferInFoService = new TransferInFoServiceImpl();
        int result = transferInFoService.transferPass(transferPlayerId);
        if(result>0){
            HttpSession session = request.getSession();
            session.setAttribute("classify",searchClassify);
            session.setAttribute("playerName",searchName);
            session.setAttribute("cp",cp);
            response.sendRedirect("/transfer_war_exploded/manage/AdminDoTransferFindServlet");
        }else{
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('操作失败')");
            out.write("location.href='/transfer_war_exploded/manage/AdminTransferInFo.jsp");
            out.write("</script>");
        }
    }
}
