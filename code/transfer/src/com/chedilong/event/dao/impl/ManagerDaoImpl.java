package com.chedilong.event.dao.impl;

import com.chedilong.event.dao.ManagerDao;
import com.chedilong.event.entity.Manager;
import com.chedilong.event.entity.Player;
import com.chedilong.event.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {
    /**
     * 管理员的增删改操作
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
     * 管理员的查询
     *
     * @param sql
     * @param message
     * @return
     */
    @Override
    public List<Manager> userFind(String sql, List<Object> message) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Manager> result = new ArrayList<>();
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
                 Manager manager = new Manager(
                         rs.getInt("id"),
                         rs.getString("account"),
                         null,
                         rs.getString("portrait"),
                         rs.getString("name"),
                         rs.getInt("age"),
                         rs.getString("rank"),
                         rs.getInt("amount"),
                         rs.getString("team"),
                         rs.getString("email"));
                         result.add(manager);
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
     * 管理员注册
     *
     * @param manager
     * @return
     */
    @Override
    public int register(Manager manager) {
        return 0;
    }
}
