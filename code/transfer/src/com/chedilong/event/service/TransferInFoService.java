package com.chedilong.event.service;

import com.chedilong.event.entity.TransferInFo;

import java.util.List;

/**
 * 转会信息相关操作
 */
public interface TransferInFoService {
    /**
     * 后台管理员获取符合要求的转会信息总记录数和总页数
     * @param count
     * @param classify
     * @param playerName
     * @return
     */
    int[] totalPage(int count,String classify,String playerName);

    /**
     * 后台管理员获取指定页数的转会信息
     * @param currentPage 当前页
     * @param count 每页显示信息条数
     * @param classify 类型
     * @param playerName 玩家姓名
     * @return
     */
    List<TransferInFo> transferInFoFind(int currentPage, int count,String classify,String playerName);

    /**
     * 通过玩家的转会信息
     * @param playerId
     * @return
     */
    int transferPass(int playerId);

    /**
     * 驳回和撤销选手的转会申请
     * @param playerId
     * @return
     */
    int transferBan(int playerId);

    /**
     * 删除玩家的转会信息
     * @param transferId
     * @return
     */
    boolean transferDelete(int transferId);

    /**
     * 前端页面获取符合要求的转会信息总记录数和总页数
     * @param count
     * @param classify
     * @param playerName
     * @param status
     * @return
     */
    int[] frontTotalPage(int count,String classify,String playerName,String status);

    /**
     * 前端页面获取指定页数的转会信息
     * @param currentPage 当前页
     * @param count 每页显示信息条数
     * @param classify 类型
     * @param playerName 玩家姓名
     * @param status 转会信息审核状态
     * @return
     */
    List<TransferInFo> frontTransferInFoFind(int currentPage, int count,String classify,String playerName,String status);

    /**
     * 根据用户id获取用户的转会详情
     * @param playerId
     * @return
     */
    TransferInFo detailObtain(int playerId);

    /**
     * 新增选手的转会信息
     * @param transferInFoTxt
     * @return
     */
    int transferAdd(TransferInFo transferInFoTxt);

    /**
     * 选手修改转会信息
     * @param transferTxt
     * @return
     */
    int transferUpdate(TransferInFo transferTxt);
}
