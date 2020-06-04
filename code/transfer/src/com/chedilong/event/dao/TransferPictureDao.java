package com.chedilong.event.dao;

import com.chedilong.event.entity.TransferInFo;
import com.chedilong.event.entity.TransferPicture;

import java.util.List;

public interface TransferPictureDao {
    /**
     * 转会信息图片的增删改操作
     * @param sql
     * @param message
     * @return
     */
    int transferPictureCud(String sql, List<Object> message);

    /**
     * 转会信息图片的查询
     * @param sql
     * @param message
     * @return
     */
    List<TransferPicture> transferPictureFind(String sql, List<Object> message);
}
