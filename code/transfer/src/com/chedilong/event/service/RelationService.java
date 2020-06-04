package com.chedilong.event.service;

public interface RelationService {

    /**
     * 战队管理层邀请用户加入自己的战队
     * @param playerId
     * @param managerId
     * @return
     */
    boolean relationAdd(int playerId,int managerId);
}
