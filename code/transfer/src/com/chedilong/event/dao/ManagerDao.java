package com.chedilong.event.dao;

import com.chedilong.event.entity.Manager;
import com.chedilong.event.entity.Player;

import java.util.List;

public interface ManagerDao {
    /**
     * 用户的增删改操作
     * @param sql
     * @param message
     * @return
     */
    int userCud(String sql, List<Object> message);

    /**
     * 管理员的查询
     * @param sql
     * @param message
     * @return
     */
    List<Manager> userFind(String sql,List<Object> message);

    /**
     * 管理员注册
     * @param manager
     * @return
     */
    int register(Manager manager);
}
