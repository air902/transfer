package com.chedilong.event.dao.impl;

import com.chedilong.event.dao.TransferInFoDao;
import com.chedilong.event.entity.Player;
import com.chedilong.event.entity.TransferInFo;
import com.chedilong.event.util.DatabaseConnectionUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransferInFoDaoImpl implements TransferInFoDao {
    /**
     * 转会信息的增删改操作
     *
     * @param sql
     * @param message
     * @return
     */
    @Override
    public int transferInFoCud(String sql, List<Object> message) {
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
     * 转会信息查询
     *
     * @param sql
     * @param message
     * @return
     */
    @Override
    public List<TransferInFo> transferInFoFind(String sql, List<Object> message) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TransferInFo> result = new ArrayList<>();
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
                //封装获取到的转会信息
                 TransferInFo transferInFo = new TransferInFo(
                         rs.getInt("id"),
                         rs.getInt("playerId"),
                         rs.getString("portrait"),
                         rs.getString("playerName"),
                         rs.getBigDecimal("price"),
                         rs.getString("classify"),
                         rs.getString("status"),
                         rs.getString("reason"));
                    result.add(transferInFo);
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
     * 通过玩家的转会信息
     *
     * @param playerId
     * @return
     */
    @Override
    public int transferPass(int playerId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String transferSql = "update transferinfo set status = ? where playerId = ?";
        String relationSql = "delete from relation where playerId = ?";
        try{
            con = DatabaseConnectionUtil.getConnection();
            con.setAutoCommit(false);
            //修改转会信息表
            pstmt = con.prepareStatement(transferSql);
            pstmt.setObject(1, "审核通过");
            pstmt.setObject(2, playerId);
            result = pstmt.executeUpdate();
            //修改玩家与战队管理层的关系表
            pstmt = con.prepareStatement(relationSql);
            pstmt.setObject(1, playerId);
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
