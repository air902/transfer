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

@WebServlet("/FrontTransferObtainServlet")
public class FrontTransferObtainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当前页
        int currentPage = 1;
        //每页显示记录条数
        int count = 5;
        //获取当前页数，当前用户选择的转会信息类型以及用户搜索的关键词
        String cp = request.getParameter("cp");
        String searchClassify = request.getParameter("classify");
        String searchName = request.getParameter("name");

        //PlayerGainServlet（战队管理员邀请选手加入自己的战队）通过session传进的值，
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
        if(searchClassify == null || searchClassify == ""){
            searchClassify = "全部";
        }

        TransferInFoService transferInFoService = new TransferInFoServiceImpl();
        //获取符合要求的转会信息总记录数和总页数
        int[] arr = transferInFoService.frontTotalPage(count,searchClassify,searchName,"审核通过");
        //获取当前页的转会信息
        List<TransferInFo> transferInFoList = transferInFoService.frontTransferInFoFind(currentPage,count,searchClassify,searchName,"审核通过");

        HttpSession session = request.getSession();
        session.setAttribute("transferInFoList",transferInFoList);
        session.setAttribute("totalRecord",arr[0]);
        session.setAttribute("totalPage",arr[1]);
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("searchName",searchName);
        session.setAttribute("searchClassify",searchClassify);
        response.sendRedirect("/transfer_war_exploded/front/index.jsp");
    }
}
