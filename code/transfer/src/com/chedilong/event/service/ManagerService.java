package com.chedilong.event.service;

import com.chedilong.event.entity.Manager;
import com.chedilong.event.entity.Player;

public interface ManagerService {
    /**
     * 战队管理层和超级管理员登录
     * @param name
     * @param password
     * @param rank
     * @return
     */
    Manager login(String name, String password, String rank);

    /**
     * 战队管理层注册
     * @param manager
     * @return
     */
    int register(Manager manager);

    /**
     * 根据管理员id获取战队管理员的信息
     * @param managerId
     * @return
     */
    Manager managerObtain(int managerId);

    /**
     * 战队管理层信息修改
     * @param managerTxt
     * @return
     */
    Manager managerUpdate(Manager managerTxt);

    /**
     * 战队管理层更新个人信息
     * 修改数据库信息，删除管理员旧头像
     * @param playerId
     * @param oldPortrait
     * @param newPortrait
     * @return
     */
    int managerPortraitUpdate(int playerId,String oldPortrait,String newPortrait);

    /**
     * 检查该账号是否被管理员注册
     * @param accountTxt
     * @return
     */
    int accountExistCheck(String accountTxt);
}
