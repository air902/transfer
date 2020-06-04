package com.chedilong.event.service;

import com.chedilong.event.entity.Player;

import java.util.List;

public interface PlayerService {
    /**
     * 玩家登录
     * @param name
     * @param password
     * @return
     */
    Player login(String name, String password);

    /**
     * 玩家注册
     * @param player
     * @return
     */
    int register(Player player);


    /**
     * 获取被举报用户的总记录数和总页数
     * @param count
     * @return
     */
    int[] informTotalPage(int count);

    /**
     * 获取指定页数的举报信息
     * @param currentPage 当前页
     * @param count 每页显示信息条数
     * @return
     */
    List<Player> informFind(int currentPage, int count);

    /**
     * 获取符合要求的用户总记录数和总页数
     * @param count
     * @param accountStatus
     * @param playerName
     * @return
     */
    int[] playerTotalPage(int count,String accountStatus,String playerName);

    /**
     * 获取指定页数的用户信息
     * @param currentPage 当前页
     * @param count 每页显示信息条数
     * @param accountStatus 用户账户状态
     * @param playerName 玩家姓名
     * @return
     */
    List<Player> playerInFoFind(int currentPage, int count,String accountStatus,String playerName);

    /**
     * 通过用户账号审核
     * @param throughPlayer
     * @return
     */
    int throughApplication(String throughPlayer);

    /**
     * 驳回用户信息申请和封禁用户
     * @param banPlayer
     * @param operation
     * @return
     */
    int banApplication(int banPlayer,String operation);

    /**
     * 根据玩家id获取玩家详细信息
     * @param playerId
     * @return
     */
    Player detailObtain(int playerId);

    /**
     * 处理战队管理的举报信息
     * @param playerId
     * @return
     */
    int playerCharge(int playerId);

    /**
     * 玩家更新个人信息
     * @param playerTxt
     * @return
     */
    Player playerUpdate(Player playerTxt);

    /**
     * 玩家更新个人头像
     * 修改数据库信息，删除玩家旧的头像
     * @param playerId
     * @param oldPortrait
     * @param newPortrait
     * @return
     */
    int playerPortraitUpdate(int playerId,String oldPortrait,String newPortrait);

    /**
     * 查询该账号是否被注册
     * @param accountTxt
     * @return
     */
    int accountExistCheck(String accountTxt);
}
