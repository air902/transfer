package com.chedilong.event.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.Random;
import java.util.ResourceBundle;



public class EmailUtil {

    public static void main(String[] args) {
        String code = sendEmail("1371539005@qq.com");
        System.out.println("验证码为："+code);
    }




    /**
     * 绑定邮箱资源文件
     */
    static ResourceBundle bunder = ResourceBundle.getBundle("UserData");
    static String emailFrom = bunder.getString("emailFrom");
    static String emailPassword = bunder.getString("emailPassword");

    /**
     * 发送邮箱验证码
     * @param emailTo
     * @return
     */
    public static String sendEmail(String emailTo){

        //创建一个HtmlEmail实例对象,设置邮箱的SMTP服务器
        HtmlEmail email =new HtmlEmail();
        email.setHostName("smtp.qq.com");
        email.setCharset("utf-8");

        //生成6位随机验证码
        int length = 6;
        Random random = new Random();
        random.nextInt();
        char num;
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String str = String.valueOf(ch[random.nextInt(ch.length)]);
        for (int i = 0; i < length-1; i++) {
            num = ch[random.nextInt(ch.length)];
            str += num;
        }
        try {
            //发送邮箱验证码
            email.addTo(emailTo);
            email.setFrom(emailFrom,"转会系统");
            email.setAuthentication(emailFrom,emailPassword);
            email.setSubject("转会系统操作验证码");
            email.setMsg("您本次操作的验证码为:"+str+"");
            email.send();
            return str;
        } catch (EmailException e) {
            e.printStackTrace();
            return null;
        }
    }
}
