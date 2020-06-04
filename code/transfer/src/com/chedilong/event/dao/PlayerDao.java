package com.chedilong.event.dao;


import com.chedilong.event.entity.Player;

import java.util.List;

public interface PlayerDao {
    /**
     * 用户的增删改操作
     * @param sql
     * @param message
     * @return
     */
    int userCud(String sql, List<Object> message);

    /**
     * 玩家的查询
     * @param sql
     * @param message
     * @return
     */
    List<Player> userFind(String sql, List<Object> message);

    /**
     * 玩家个人信息申请驳回和玩家封禁
     * @param sql
     * @param message
     * @return
     */
    int userBan(String sql,List<Object> message);
}
