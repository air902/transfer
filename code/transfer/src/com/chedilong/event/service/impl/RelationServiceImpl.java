package com.chedilong.event.service.impl;

import com.chedilong.event.dao.RelationDao;
import com.chedilong.event.dao.TransferPictureDao;
import com.chedilong.event.dao.impl.RealtionDaoImpl;
import com.chedilong.event.dao.impl.TransferPictureDaoImpl;
import com.chedilong.event.entity.TransferPicture;
import com.chedilong.event.service.RelationService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RelationServiceImpl implements RelationService {
    /**
     * 战队管理层邀请用户加入自己的战队
     *
     * @param playerId
     * @param managerId
     * @return
     */
    @Override
    public boolean relationAdd(int playerId, int managerId) {
        //删除选手转会信息的图片
        TransferPictureDao transferPictureDao = new TransferPictureDaoImpl();
        String sql = "select * from transferpicture where playerId = ?";
        List<Object> message = new ArrayList<>();
        message.add(playerId);
        List<TransferPicture> transferPictureList = transferPictureDao.transferPictureFind(sql,message);
        boolean result1 = false;
        //删除图片操作
        if(transferPictureList.size()>0){
            //选手上传了图片，执行图片删除
            for(int i = 0;i<transferPictureList.size();i++){
                String picturePath = "D:/check/cat/二轮考核/code/transfer/web/picture/transferPicture/"+transferPictureList.get(i).getPicture();
                File file = new File(picturePath);
                //判断文件是否存在
                if (file.exists() == true){
                    System.out.println("图片存在，可执行删除操作");
                    Boolean flag = false;
                    flag = file.delete();
                    if (flag){
                        System.out.println("成功删除图片"+file.getName());
                        result1 = true;
                    }else {
                        System.out.println("删除失败");
                        result1 = false;
                    }
                }else {
                    System.out.println("图片不存在，终止操作");
                    result1 = false;
                }
            }
        }else{
            //若选手未上传图片则不进行删除操作
            result1 = true;
        }
        //往关系表中添加玩家与管理员信息
        RelationDao relationDao = new RealtionDaoImpl();
        int result = relationDao.add(playerId,managerId);


        if(result>0 && result1){
            return true;
        }else {
            return false;
        }
    }
}
