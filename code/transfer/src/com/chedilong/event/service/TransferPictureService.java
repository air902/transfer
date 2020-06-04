package com.chedilong.event.service;

import com.chedilong.event.entity.TransferPicture;

import java.util.List;

public interface TransferPictureService {

    /**
     * 获取转会信息的图片
     * @param playerId
     * @return
     */
    List<TransferPicture> detailObtain(int playerId);
}
