package com.chedilong.event.servlet.User;

import com.chedilong.event.entity.Player;
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
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet("/TransferAddUpdateServlet")
public class TransferAddUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //获取玩家输入的值
        String transferClassify = request.getParameter("transferClassify");
        String transferPrice = request.getParameter("transferPrice");
        HttpSession session = request.getSession();
        Player player = (Player)session.getAttribute("user");
        PrintWriter out = response.getWriter();
        //判断选手的账户状态
        if(!player.getAccountStatus().equals("审核通过")){
            out.print("007");
            out.close();
        }else {
            //判断选手是否加入战队
            if(player.getTeamStatus().equals("已加入战队")){
                out.print("000");
                out.close();
            }else{
                //判断用户输入金额是否符合要求
                if(transferPrice.matches("[1-9]{1}\\d{0,8}")||transferPrice.matches("[1-9]{1}\\d{0,8}[.]\\d{1,2}")){
                    BigDecimal price = new BigDecimal(transferPrice);
                    //封装选手提交的转会信息
                    TransferInFo transferInFoTxt = new TransferInFo(null,player.getId(),player.getPortrait(),player.getName(),
                            price,transferClassify,"审核中",null);
                    //判断用户是否已经提交转会申请
                    TransferInFoService transferInFoService = new TransferInFoServiceImpl();
                    TransferInFo transferInFo = transferInFoService.detailObtain(player.getId());
                    if(transferInFo == null){
                        //选手未提交转会信息，新增选手转会信息
                        int result = transferInFoService.transferAdd(transferInFoTxt);
                        if(result>0){
                            //新建转会信息成功
                            out.print("002");
                            out.close();
                        }else{
                            //新建转会信息失败
                            out.print("003");
                            out.close();
                        }
                    }else{
                        //选手已提交转会信息，修改选手的转会信息
                        int result = transferInFoService.transferUpdate(transferInFoTxt);
                        if(result>0){
                            //新建转会信息成功
                            out.print("002");
                            out.close();
                        }else{
                            //新建转会信息失败
                            out.print("003");
                            out.close();
                        }
                    }
                }else{
                    //金额不符合要求
                    out.print("001");
                    out.close();
                }
            }
        }
    }
}
