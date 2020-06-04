package com.chedilong.event.servlet.User;

import com.chedilong.event.entity.Player;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.service.impl.PlayerServiceImpl;
import com.jspsmart.upload.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/front/PlayerRegisterServlet")
public class PlayerRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          request.setCharacterEncoding("GBK");
        /**
         *
         * 将用户输入信息保存到session中
         * 注册成功后一定要将其删除
         *
         */
        SmartUpload smartUpload = new SmartUpload();
        //初始化smartUpload
        smartUpload.initialize(this.getServletConfig(),request,response);
        try {
            //设置允许的上传类型上传类型以及上传大小
            smartUpload.setAllowedFilesList("jpg,png,jpeg");
            smartUpload.setMaxFileSize(1024*1024*5);
            smartUpload.upload();
            smartUpload.save("D:/check/cat/二轮考核/code/transfer/web/picture/portrait");
            smartUpload.save("/picture/portrait");
        } catch (SmartUploadException e) {
            //文件上传失败提示信息
            e.printStackTrace();
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('File upload failed, please try again');");
            out.write("location.href='/transfer_war_exploded/front/Register.jsp");
            out.write("</script>");
        }catch (SecurityException a){
            a.printStackTrace();
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('portrait upload failed, please check the portrait format is correct!');");
            out.write("location.href='/transfer_war_exploded/front/Register.jsp'");
            out.write("</script>");
        }
        //获取上传的文件名
        File file = smartUpload.getFiles().getFile(0);
        String portraitTxt = file.getFileName();
        //获取用户其他信息,并将其封装到playerTxt里面
        Request req = smartUpload.getRequest();
        String accountTxt = req.getParameter("accountTxt");
        String passwordTxt = req.getParameter("passwordTxt");
        String nameTxt = req.getParameter("nameTxt");
        int ageTxt = Integer.valueOf(req.getParameter("ageTxt"));
        String introductionTxt = req.getParameter("introductionTxt");
        String lastTeamTxt = req.getParameter("lastTeamTxt");
        String joinDateTxt = req.getParameter("joinDateTxt");
        String emailTxt = req.getParameter("emailTxt");
        Player playerTxt = new Player(null,accountTxt,passwordTxt,portraitTxt,nameTxt,ageTxt,introductionTxt,
                lastTeamTxt,joinDateTxt,emailTxt,"审核中","未加入战队","玩家",null);
        //将用户信息添加到数据中
        PlayerService playerService = new PlayerServiceImpl();
        int result = playerService.register(playerTxt);
        if(result>0){
            //注册成功
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('Registered successfully!');");
            out.write("location.href='/transfer_war_exploded/front/index.jsp'");
            out.write("</script>");
        }else{
            //注册失败
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('Registration failed!');");
            out.write("location.href='/transfer_war_exploded/front/Register.jsp'");
            out.write("</script>");
        }




    }

}
