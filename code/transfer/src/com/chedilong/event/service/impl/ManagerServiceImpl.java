package com.chedilong.event.service.impl;

import com.chedilong.event.dao.ManagerDao;
import com.chedilong.event.dao.PlayerDao;
import com.chedilong.event.dao.impl.ManagerDaoImpl;
import com.chedilong.event.dao.impl.PlayerDaoImpl;
import com.chedilong.event.entity.Manager;
import com.chedilong.event.entity.Player;
import com.chedilong.event.service.ManagerService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    /**
     * 战队管理层和超级管理员登录
     *
     * @param name
     * @param password
     * @param rank
     * @return
     */
    @Override
    public Manager login(String name, String password, String rank) {
        List<Object> message = new ArrayList<>();
        Manager result = null;
        message.add(name);
        message.add(password);
        message.add(rank);
        String sql = "select * from `manager` where account = ? and password = ? and rank = ?";
        ManagerDao managerDao = new ManagerDaoImpl();
        List<Manager> list = managerDao.userFind(sql,message);
        if(list.size()>0){
            result = list.get(0);
        }
        return result;
    }

    /**
     * 战队管理层注册
     * @param manager
     * @return
     */
    @Override
    public int register(Manager manager) {
        List<Object> message = new ArrayList<>();
        message.add(manager.getAccount());
        message.add(manager.getPassword());
        message.add(manager.getPortrait());
        message.add(manager.getName());
        message.add(manager.getAge());
        message.add(manager.getRank());
        message.add(0);
        message.add(manager.getTeam());
        message.add(manager.getEmail());

        String sql = "insert into player values(null,?,?,?,?,?,?,?,?,?)";
        ManagerDao managerDao = new ManagerDaoImpl();
        int result = managerDao.userCud(sql,message);
        return result;
    }

    /**
     * 根据管理员id获取战队管理员的信息
     *
     * @param managerId
     * @return
     */
    @Override
    public Manager managerObtain(int managerId) {
        ManagerDao managerDao = new ManagerDaoImpl();
        String sql = "select * from manager where id = ?";
        List<Object> message = new ArrayList<>();
        message.add(managerId);
        List<Manager> managerList = managerDao.userFind(sql,message);
        Manager result = null;
        if(managerList.size()>0){
            result = managerList.get(0);
        }
        return result;
    }

    /**
     * 战队管理层信息修改
     *
     * @param managerTxt
     * @return
     */
    @Override
    public Manager managerUpdate(Manager managerTxt) {
        //修改战队管理层信息
        ManagerDao managerDao = new ManagerDaoImpl();
        String sql = "update manager set account = ?," +
                "name = ?,age = ?,email = ?,team = ? "+
                "where id = ?";
        List<Object> message = new ArrayList<>();
        message.add(managerTxt.getAccount());
        message.add(managerTxt.getName());
        message.add(managerTxt.getAge());
        message.add(managerTxt.getEmail());
        message.add(managerTxt.getTeam());
        message.add(managerTxt.getId());
        int flag = managerDao.userCud(sql,message);
        if(flag>0){
            //修改成功,查找该战队管理层的全部信息并返回
            String sqlFind = "select * from manager where id = ?";
            List<Object> messageFind = new ArrayList<>();
            messageFind.add(managerTxt.getId());
            List<Manager> result = managerDao.userFind(sqlFind,messageFind);
            if(result.size()>0){
                return result.get(0);
            }else{
                return null;
            }
        }else {
            return null;
        }
    }

    /**
     * 战队管理层更新个人信息
     * 修改数据库信息，删除管理员旧头像
     *
     * @param playerId
     * @param oldPortrait
     * @param newPortrait
     * @return
     */
    @Override
    public int managerPortraitUpdate(int playerId, String oldPortrait, String newPortrait) {
        //删除战队管理层的旧头像
        boolean flag = false;
        String picturePath = "D:/check/cat/二轮考核/code/transfer/web/picture/portrait/"+oldPortrait;
        File file = new File(picturePath);
        //判断文件是否存在,若存在则删除该图片
        if (file.exists() == true){
            if (file.delete()){
                flag = true;
            }else {
                flag = false;
            }
        }else {
            flag = false;
        }
        //更新数据库信息
        ManagerDao managerDao = new ManagerDaoImpl();
        String sql = "update player set portrait = ? where id = ?";
        List<Object> message = new ArrayList<>();
        message.add(newPortrait);
        message.add(playerId);
        int result = managerDao.userCud(sql,message);
        if(flag && result>0){
            return result;
        }else {
            return 0;
        }
    }

    /**
     * 检查该账号是否被管理员注册
     *
     * @param accountTxt
     * @return
     */
    @Override
    public int accountExistCheck(String accountTxt) {
        ManagerDao managerDao = new ManagerDaoImpl();
        String sql = "select * from manager where account = ?";
        List<Object> message = new ArrayList<>();
        message.add(accountTxt);
        List<Manager> managerList = managerDao.userFind(sql,message);
        int result = managerList.size();
        return result;
    }
}
