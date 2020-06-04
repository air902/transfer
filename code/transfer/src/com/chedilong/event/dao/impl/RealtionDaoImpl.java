package com.chedilong.event.dao.impl;

import com.chedilong.event.dao.RelationDao;
import com.chedilong.event.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RealtionDaoImpl implements RelationDao {
    /**
     * 战队管理层邀请选手加入战队的数据库操作
     *
     * @param playerId
     * @param managerId
     * @return
     */
    @Override
    public int add(int playerId, int managerId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String relationSql = "insert into relation (playerId,managerId) values (?,?)";
        String transferSql = "delete from transferinfo where playerId = ?";
        try{
            con = DatabaseConnectionUtil.getConnection();
            con.setAutoCommit(false);
            //修改玩家与战队管理层的关系表
            pstmt = con.prepareStatement(relationSql);
            pstmt.setObject(1, playerId);
            pstmt.setObject(2, managerId);
            result = pstmt.executeUpdate();
            //修改转会信息表
            pstmt = con.prepareStatement(transferSql);
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
