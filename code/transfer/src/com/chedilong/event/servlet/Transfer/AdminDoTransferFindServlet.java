package com.chedilong.event.servlet.Transfer;

import com.chedilong.event.entity.TransferInFo;
import com.chedilong.event.service.TransferInFoService;
import com.chedilong.event.service.impl.TransferInFoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 获取转会信息
 */
@WebServlet("/manage/AdminDoTransferFindServlet")
public class AdminDoTransferFindServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当前页
        int currentPage = 1;
        //每页显示记录条数
        int count = 5;
        //获取用户指定的页面，搜索关键词：分类，玩家姓名
        String cp = request.getParameter("cp");
        String searchClassify = request.getParameter("classify");
        String searchName = request.getParameter("searchName");

        //AdminTransferPassServlet通过session传进的值
        HttpSession sessionServlet = request.getSession();
        if((String)sessionServlet.getAttribute("cp") != null){
            cp = (String)sessionServlet.getAttribute("cp");
            sessionServlet.setAttribute("cp",null);
        }
        if((String)sessionServlet.getAttribute("classify") != null){
            searchClassify = (String)sessionServlet.getAttribute("classify");
            sessionServlet.setAttribute("classify",null);
        }
        if((String)sessionServlet.getAttribute("playerName") != null){
            searchName = (String)sessionServlet.getAttribute("playerName");
            sessionServlet.setAttribute("playerName",null);
        }

        if(cp != null){
            currentPage = Integer.valueOf(cp);
        }
        if(searchClassify == null){
            searchClassify = "全部";
        }

        TransferInFoService transferInFoService = new TransferInFoServiceImpl();
        //获取符合要求的总记录数和总页数
        int[] arr = transferInFoService.totalPage(count,searchClassify,searchName);
        //获取当前页的转会信息
        List<TransferInFo> transferInFoList = transferInFoService.transferInFoFind(currentPage,count,searchClassify,searchName);

        HttpSession session = request.getSession();
        session.setAttribute("transferInFoList",transferInFoList);
        session.setAttribute("totalRecord",arr[0]);
        session.setAttribute("totalPage",arr[1]);
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("searchName",searchName);
        session.setAttribute("searchClassify",searchClassify);
        response.sendRedirect("/transfer_war_exploded/manage/AdminTransferInFo.jsp");
    }
}
