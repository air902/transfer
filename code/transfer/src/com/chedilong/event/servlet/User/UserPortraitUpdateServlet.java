package com.chedilong.event.servlet.User;

import com.chedilong.event.entity.Manager;
import com.chedilong.event.entity.Player;
import com.chedilong.event.service.ManagerService;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.service.impl.ManagerServiceImpl;
import com.chedilong.event.service.impl.PlayerServiceImpl;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserPortraitUpdateServlet")
public class UserPortraitUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
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
            out.write("alert('portrait upload failed, please try again!');");
            out.write("location.href='/transfer_war_exploded/front/PlayerInFoUpdate.jsp'");
            out.write("</script>");
        } catch (SecurityException a){
            a.printStackTrace();
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('portrait upload failed, please check the portrait format is correct!');");
            out.write("location.href='/transfer_war_exploded/front/PlayerInFoUpdate.jsp'");
            out.write("</script>");
        }
        //获取新上传的图片名称
        Files files = smartUpload.getFiles();
        File file = files.getFile(0);
        String portrait = file.getFileName();
        //上传成功，修改数据库中用户的头像信息以及session中的user的portrait
        HttpSession session = request.getSession();
        if("1".equals((String)session.getAttribute("isLogin"))){
            //选手修改个人头像
            Player player = (Player)session.getAttribute("user");
            //删除头像及更新数据库信息
            int playerId = player.getId();
            String oldPortrait = player.getPortrait();
            PlayerService playerService = new PlayerServiceImpl();
            int result = playerService.playerPortraitUpdate(playerId,oldPortrait,portrait);
            if(result>0){
                //修改成功
                player.setPortrait(portrait);
                session.setAttribute("user",player);
                PrintWriter out=response.getWriter();
                out.write("<script>");
                out.write("alert('Portrait modified successfully, please wait for the administrator to review!');");
                out.write("location.href='/transfer_war_exploded/front/PlayerInFoUpdate.jsp'");
                out.write("</script>");
            }else{
                PrintWriter out=response.getWriter();
                out.write("<script>");
                out.write("alert('portrait upload failed, please try again!');");
                out.write("location.href='/transfer_war_exploded/front/PlayerInFoUpdate.jsp'");
                out.write("</script>");
            }
        }else if("2".equals((String)session.getAttribute("isLogin"))){
            //战队管理层修改个人头像
            Manager manager = (Manager) session.getAttribute("user");
            //删除头像及更新数据库信息
            int playerId = manager.getId();
            String oldPortrait = manager.getPortrait();
            ManagerService managerService = new ManagerServiceImpl();
            int result = managerService.managerPortraitUpdate(playerId,oldPortrait,portrait);
            if(result>0){
                //修改成功
                manager.setPortrait(portrait);
                session.setAttribute("user",manager);
                PrintWriter out=response.getWriter();
                out.write("<script>");
                out.write("alert('Portrait modified successfully!');");
                out.write("location.href='/transfer_war_exploded/front/PlayerInFoUpdate.jsp'");
                out.write("</script>");
            }else{
                PrintWriter out=response.getWriter();
                out.write("<script>");
                out.write("alert('portrait upload failed, please try again!');");
                out.write("location.href='/transfer_war_exploded/front/Register.jsp'");
                out.write("</script>");
            }
        }
    }
}
