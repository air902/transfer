package com.chedilong.event.dao.impl;

import com.chedilong.event.dao.PlayerDao;
import com.chedilong.event.entity.Player;
import com.chedilong.event.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoImpl implements PlayerDao {
    /**
     * 用户的增删改操作
     * @param sql
     * @param message
     * @return
     */
    @Override
    public int userCud(String sql, List<Object> message) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = DatabaseConnectionUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            //给占位符赋值
            for(int i = 0;i<message.size();i++){
                Object object = message.get(i);
                pstmt.setObject(i+1, object);
            }
            return pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }finally {
            DatabaseConnectionUtil.close(con,pstmt,null);
        }
    }

    /**
     * 玩家的查询
     * @param sql
     * @param message
     * @return
     */
    @Override
    public List<Player> userFind(String sql, List<Object> message) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Player> result = new ArrayList<>();
        try {
            con = DatabaseConnectionUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            //给占位符赋值
            for (int i = 0; i < message.size(); i++) {
                Object object = message.get(i);
                pstmt.setObject(i + 1, object);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //封装获取到的玩家信息
                Player player = new Player(
                        rs.getInt("id"),
                        rs.getString("account"),
                        null,
                        rs.getString("portrait"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("introduction"),
                        rs.getString("lastTeam"),
                        rs.getString("joinDate"),
                        rs.getString("email"),
                        rs.getString("accountStatus"),
                        rs.getString("teamStatus"),
                        rs.getString("rank"),
                        rs.getString("reason"));
                result.add(player);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnectionUtil.close(con, pstmt, rs);
        }
    }

    /**
     * 玩家个人信息申请驳回和玩家封禁
     *
     * @param sql
     * @param message
     * @return
     */
    @Override
    public int userBan(String sql, List<Object> message) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String trasnferSql = "update transferinfo set status = '审核不通过',reason = '个人信息被驳回或账号曾被封禁'  where playerId = ?";
        try{
            con = DatabaseConnectionUtil.getConnection();
            con.setAutoCommit(false);
            //修改玩家表
            pstmt = con.prepareStatement(sql);
            for(int i = 0;i<message.size();i++){
                Object object = message.get(i);
                pstmt.setObject(i+1, object);
            }
            result = pstmt.executeUpdate();
            //修改转会信息表
            pstmt = con.prepareStatement(trasnferSql);
            pstmt.setObject(1, message.get(1));
            result += pstmt.executeUpdate();
            con.commit();
            return  result;
        }catch(SQLException e){
            if(con != null){
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return 0;
        }finally {
            DatabaseConnectionUtil.close(con,pstmt,null);
        }
    }
}
