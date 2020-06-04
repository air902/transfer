package com.chedilong.event.dao.impl;

import com.chedilong.event.dao.TransferPictureDao;
import com.chedilong.event.entity.TransferInFo;
import com.chedilong.event.entity.TransferPicture;
import com.chedilong.event.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransferPictureDaoImpl implements TransferPictureDao {
    /**
     * 转会信息图片的增删改操作
     *
     * @param sql
     * @param message
     * @return
     */
    @Override
    public int transferPictureCud(String sql, List<Object> message) {
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
     * 转会信息图片的查询
     * @param sql
     * @param message
     * @return
     */
    @Override
    public List<TransferPicture> transferPictureFind(String sql, List<Object> message) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TransferPicture> result = new ArrayList<>();
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
                TransferPicture transferPicture = new TransferPicture(
                        rs.getInt("id"),
                        rs.getInt("playerId"),
                        rs.getString("picture"),
                        rs.getInt("transferInFoId")
                );
                result.add(transferPicture);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatabaseConnectionUtil.close(con, pstmt, rs);
        }
    }
}
