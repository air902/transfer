package com.chedilong.event.service.impl;

import com.chedilong.event.dao.PlayerDao;
import com.chedilong.event.dao.TransferInFoDao;
import com.chedilong.event.dao.impl.PlayerDaoImpl;
import com.chedilong.event.dao.impl.TransferInFoDaoImpl;
import com.chedilong.event.entity.Player;
import com.chedilong.event.entity.TransferInFo;
import com.chedilong.event.service.PlayerService;
import com.chedilong.event.util.StringJudgeUtil;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import sun.plugin2.message.Message;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    /**
     * 玩家登录
     *
     * @param name
     * @param password
     * @return
     */
    @Override
    public Player login(String name, String password) {
        List<Object> message = new ArrayList<>();
        Player result = null;
        message.add(name);
        message.add(password);
        String sql = "select * from `player` where account = ? and password = ?";
        PlayerDao playerDao = new PlayerDaoImpl();
        List<Player> list = playerDao.userFind(sql,message);
        if(list.size()>0){
            result = list.get(0);
        }
        return result;
    }

    /**
     * 玩家注册
     *
     * @param player
     * @return
     */
    @Override
    public int register(Player player) {
        List<Object> message = new ArrayList<>();
        message.add(player.getAccount());
        message.add(player.getPassword());
        message.add(player.getPortrait());
        message.add(player.getName());
        message.add(player.getAge());
        message.add(player.getIntroduction());
        message.add(player.getLastTeam());
        message.add(player.getJoinDate());
        message.add(player.getEmail());
        message.add(player.getAccountStatus());
        message.add(player.getTeamStatus());
        message.add(player.getRank());
        message.add(player.getReason());

        String sql = "insert into player values(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PlayerDao playerDao = new PlayerDaoImpl();
        int result = playerDao.userCud(sql,message);
        return result;
    }

    /**
     * 获取被举报用户的总记录数和总页数
     *
     * @param count
     * @return
     */
    @Override
    public int[] informTotalPage(int count) {
        //总记录数，总页数初始值分别为0和1
        int[] total = {0,1};
        //获取总记录数
        PlayerDao playerDao = new PlayerDaoImpl();
        List<Object> message = new ArrayList<>();
        String sql = "select * from  player where accountStatus = '被举报'";
        List<Player> playerList = playerDao.userFind(sql,message);
        total[0] = playerList.size();
        //计算总页数
        if(total[1]%count == 0){
            total[1] = total[0]/count;
        }else{
            total[1] = total[0]/count+1;
        }
        return total;
    }

    /**
     * 获取指定页数的举报信息
     *
     * @param currentPage 当前页
     * @param count       每页显示信息条数
     * @return
     */
    @Override
    public List<Player> informFind(int currentPage, int count) {
        //获取指定页数的被举报用户的的用户信息
        List<Object> message = new ArrayList<>();
        String sql = "select * from player where accountStatus = '被举报' limit ?,? ";
        message.add((currentPage-1)*count);
        message.add(count);
        List<Player> playerList = new ArrayList<>();
        PlayerDao playerDao = new PlayerDaoImpl();
        playerList = playerDao.userFind(sql,message);
        return playerList;
    }

    /**
     * 获取符合要求的用户总记录数和总页数
     *
     * @param count
     * @param accountStatus
     * @param playerName
     * @return
     */
    @Override
    public int[] playerTotalPage(int count, String accountStatus, String playerName) {
        //总记录数，总页数初始值分别为0和1
        int[] total = {0,1};
        PlayerDao playerDao = new PlayerDaoImpl();
        List<Object> message = new ArrayList<>();
        String sql = null;
        //当用户选择搜索类型为全部时，对转会信息分类不作限制
        if(accountStatus.equals("全部") && StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from  player";
        }else if(!accountStatus.equals("全部") && StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from  player where accountStatus = ?";
            message.add(accountStatus);
        }else if(accountStatus.equals("全部") && StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  player where name like ?";
            playerName = "%"+playerName+"%";
            message.add(playerName);
        }else if(!accountStatus.equals("全部") && StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  player where accountStatus = ? and name like ?";
            playerName = "%"+playerName+"%";
            message.add(accountStatus);
            message.add(playerName);
        }
        List<Player> playerList = playerDao.userFind(sql,message);
        //获取总记录数
        total[0] = playerList.size();
        //计算页数
        if(total[1]%count == 0){
            total[1] = total[0]/count;
        }else{
            total[1] = total[0]/count+1;
        }
        return total;
    }

    /**
     * 获取指定页数的用户信息
     *
     * @param currentPage   当前页
     * @param count         每页显示信息条数
     * @param accountStatus 用户账户状态
     * @param playerName    玩家姓名
     * @return
     */
    @Override
    public List<Player> playerInFoFind(int currentPage, int count, String accountStatus, String playerName) {
        List<Object> message = new ArrayList<>();
        String sql = null;
        //当用户选择搜索类型为 全部 时，对用户分类不作限制
        if(accountStatus.equals("全部")&& StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from player limit ?,?";
        }else if(!accountStatus.equals("全部")&& StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from  player where accountStatus = ? limit ?,?";
            message.add(accountStatus);
        }else if(accountStatus.equals("全部")&& StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  player where name like ? limit ?,?";
            playerName = "%"+playerName+"%";
            message.add(playerName);
        }else if(!accountStatus.equals("全部")&& StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  player where accountStatus = ? and name like ? limit ?,?";
            playerName = "%"+playerName+"%";
            message.add(accountStatus);
            message.add(playerName);
        }
        message.add((currentPage-1)*count);
        message.add(count);
        List<Player> playerList = new ArrayList<>();
        PlayerDao playerDao = new PlayerDaoImpl();
        playerList = playerDao.userFind(sql,message);
        return playerList;
    }

    /**
     * 通过用户账号审核
     *
     * @param throughPlayer
     * @return
     */
    @Override
    public int throughApplication(String throughPlayer) {
        List<Object> message = new ArrayList<>();
        message.add("审核通过");
        message.add(throughPlayer);
        String sql = "update player set accountStatus = ? where account = ?";
        PlayerDao playerDao = new PlayerDaoImpl();
        int result = playerDao.userCud(sql,message);
        return result;
    }

    /**
     * 驳回用户信息申请和封禁用户
     *
     * @param banPlayer
     * @param operation
     * @return
     */
    @Override
    public int banApplication(int banPlayer, String operation) {
        List<Object> message = new ArrayList<>();
        message.add(operation);
        message.add(banPlayer);
        String sql = "update player set accountStatus = ? where id = ?";
        PlayerDao playerDao = new PlayerDaoImpl();
        int result = playerDao.userBan(sql,message);
        return result;
    }

    /**
     * 根据玩家id获取玩家详细信息
     *
     * @param playerId
     * @return
     */
    @Override
    public Player detailObtain(int playerId) {
        PlayerDao playerDao = new PlayerDaoImpl();
        String sql = "select * from player where id = ?";
        List<Object> message = new ArrayList<>();
        message.add(playerId);
        List<Player> playerList = playerDao.userFind(sql,message);
        Player result = playerList.get(0);
        return result;
    }

    /**
     * 处理战队管理的举报信息
     *
     * @param playerId
     * @return
     */
    @Override
    public int playerCharge(int playerId) {
        List<Object> message = new ArrayList<>();
        message.add("被举报");
        message.add(playerId);
        String sql = "update player set accountStatus = ? where id = ?";
        PlayerDao playerDao = new PlayerDaoImpl();
        int result = playerDao.userCud(sql,message);
        return result;
    }

    /**
     * 玩家更新个人信息
     *
     * @param playerTxt
     * @return
     */
    @Override
    public Player playerUpdate(Player playerTxt) {
        PlayerDao playerDao = new PlayerDaoImpl();
        String sql = "update player set account = ?," +
                "name = ?,age = ?,introduction = ?," +
                "lastTeam = ?,joinDate = ?,email = ?," +
                "accountStatus = ? where id = ?";
        List<Object> message = new ArrayList<>();
        message.add(playerTxt.getAccount());
        message.add(playerTxt.getName());
        message.add(playerTxt.getAge());
        message.add(playerTxt.getIntroduction());
        message.add(playerTxt.getLastTeam());
        message.add(playerTxt.getJoinDate());
        message.add(playerTxt.getEmail());
        message.add("审核中");
        message.add(playerTxt.getId());
        int flag = playerDao.userCud(sql,message);
        if(flag>0){
            //修改成功,查找该玩家的全部信息并返回
            String sqlFind = "select * from player where id = ?";
            List<Object> messageFind = new ArrayList<>();
            messageFind.add(playerTxt.getId());
            List<Player> result = playerDao.userFind(sqlFind,messageFind);
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
     * 玩家更新个人头像
     * 修改数据库信息，删除玩家旧的头像
     *
     * @param playerId
     * @param oldPortrait
     * @param newPortrait
     * @return
     */
    @Override
    public int playerPortraitUpdate(int playerId, String oldPortrait, String newPortrait) {
        //删除选手的旧头像
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
        PlayerDao playerDao = new PlayerDaoImpl();
        String sql = "update player set portrait = ?,accountStatus = ? where id = ?";
        List<Object> message = new ArrayList<>();
        message.add(newPortrait);
        message.add("审核中");
        message.add(playerId);
        int result = playerDao.userCud(sql,message);
        if(flag && result>0){
            return result;
        }else {
            return 0;
        }
    }

    /**
     * 查询该账号是否被注册
     * @param accountTxt
     * @return
     */
    @Override
    public int accountExistCheck(String accountTxt) {
        PlayerDao playerDao = new PlayerDaoImpl();
        String sql = "select * from player where account = ?";
        List<Object> message = new ArrayList<>();
        message.add(accountTxt);
        List<Player> playerList = playerDao.userFind(sql,message);
        int result = playerList.size();
        return result;
    }
}
