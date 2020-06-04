package com.chedilong.event.service.impl;

import com.chedilong.event.dao.TransferInFoDao;
import com.chedilong.event.dao.TransferPictureDao;
import com.chedilong.event.dao.impl.TransferInFoDaoImpl;
import com.chedilong.event.dao.impl.TransferPictureDaoImpl;
import com.chedilong.event.entity.TransferInFo;
import com.chedilong.event.entity.TransferPicture;
import com.chedilong.event.service.TransferInFoService;
import com.chedilong.event.service.TransferPictureService;

import java.util.ArrayList;
import java.util.List;

public class TransferPictureServiceImpl implements TransferPictureService {
    /**
     * 获取转会信息的图片
     *
     * @param playerId
     * @return
     */
    @Override
    public List<TransferPicture> detailObtain(int playerId) {
        TransferPictureDao transferPictureDao = new TransferPictureDaoImpl();
        String sql = "select * from transferpicture where playerId = ?";
        List<Object> message = new ArrayList<>();
        message.add(playerId);
        List<TransferPicture> transferPictures = transferPictureDao.transferPictureFind(sql,message);
        return transferPictures;
    }
}
