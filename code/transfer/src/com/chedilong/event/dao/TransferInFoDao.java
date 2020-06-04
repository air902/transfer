package com.chedilong.event.dao;

import com.chedilong.event.entity.Player;
import com.chedilong.event.entity.TransferInFo;

import java.util.List;

public interface TransferInFoDao {
    /**
     * 转会信息的增删改操作
     * @param sql
     * @param message
     * @return
     */
    int transferInFoCud(String sql, List<Object> message);

    /**
     * 转会信息的查询
     * @param sql
     * @param message
     * @return
     */
    List<TransferInFo> transferInFoFind(String sql, List<Object> message);

    /**
     * 通过玩家的转会信息
     * @param playerId
     * @return
     */
    int transferPass(int playerId);
}
