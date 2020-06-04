package com.chedilong.event.dao;

public interface RelationDao {

    /**
     * 战队管理层邀请选手加入战队的数据库操作
     * @param playerId
     * @param managerId
     * @return
     */
    int add(int playerId,int managerId);

}
