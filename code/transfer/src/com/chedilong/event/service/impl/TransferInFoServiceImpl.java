package com.chedilong.event.service.impl;

import com.chedilong.event.dao.RelationDao;
import com.chedilong.event.dao.TransferInFoDao;
import com.chedilong.event.dao.TransferPictureDao;
import com.chedilong.event.dao.impl.RealtionDaoImpl;
import com.chedilong.event.dao.impl.TransferInFoDaoImpl;
import com.chedilong.event.dao.impl.TransferPictureDaoImpl;
import com.chedilong.event.entity.TransferInFo;
import com.chedilong.event.entity.TransferPicture;
import com.chedilong.event.service.TransferInFoService;
import com.chedilong.event.util.StringJudgeUtil;
import com.sun.org.apache.xerces.internal.impl.XMLEntityScanner;

import javax.imageio.spi.ImageInputStreamSpi;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TransferInFoServiceImpl implements TransferInFoService {

    /**
     * 后台管理员获取符合要求的转会信息的总记录数和总页数
     * @param count 每页显示条数
     * @return
     */
    @Override
    public int[] totalPage(int count,String classify,String playerName) {
        //总记录数，总页数初始值分别为0和1
        int[] total = {0,1};
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        List<Object> message = new ArrayList<>();
        String sql = null;
        //当用户选择搜索类型为全部时，对转会信息分类不作限制
        if(classify.equals("全部") && StringJudgeUtil.isEmpty(playerName)){
             sql  ="select * from  transferinfo";
        }else if(!classify.equals("全部") && StringJudgeUtil.isEmpty(playerName)){
             sql  ="select * from  transferinfo where classify = ?";
             message.add(classify);
        }else if(classify.equals("全部") && StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  transferinfo where playerName like ?";
            playerName = "%"+playerName+"%";
            message.add(playerName);
        }else if(!classify.equals("全部") && StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  transferinfo where classify = ? and playerName like ?";
            playerName = "%"+playerName+"%";
            message.add(classify);
            message.add(playerName);
        }
        List<TransferInFo> transferInFoList = transferInFoDao.transferInFoFind(sql,message);
        //获取总记录数
        total[0] = transferInFoList.size();
        //计算页数
        if(total[1]%count == 0){
            total[1] = total[0]/count;
        }else{
            total[1] = total[0]/count+1;
        }
        return total;
    }

    /**
     * 后台管理员获取指定页数的转会信息
     * @param currentPage 当前页
     * @param count 每页显示信息条数
     * @param classify 类型
     * @param playerName 玩家姓名
     * @return
     */
    @Override
    public List<TransferInFo> transferInFoFind(int currentPage, int count,String classify,String playerName) {
        List<Object> message = new ArrayList<>();
        String sql = null;
        //当用户选择搜索类型为 全部 时，对转会信息分类不作限制
        if(classify.equals("全部")&& StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from transferinfo limit ?,?";
        }else if(!classify.equals("全部")&& StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from  transferinfo where classify = ? limit ?,?";
            message.add(classify);
        }else if(classify.equals("全部")&& StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  transferinfo where playerName like ? limit ?,?";
            playerName = "%"+playerName+"%";
            message.add(playerName);
        }else if(!classify.equals("全部")&& StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  transferinfo where classify = ? and playerName like ? limit ?,?";
            playerName = "%"+playerName+"%";
            message.add(classify);
            message.add(playerName);
        }
        message.add((currentPage-1)*count);
        message.add(count);
        List<TransferInFo> transferInFoList = new ArrayList<>();
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        transferInFoList = transferInFoDao.transferInFoFind(sql,message);
        return transferInFoList;
    }

    /**
     * 通过玩家的转会信息
     *
     * @param playerId
     * @return
     */
    @Override
    public int transferPass(int playerId) {
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        int result = transferInFoDao.transferPass(playerId);
        return result;
    }

    /**
     * 驳回和撤销选手的转会申请
     *
     * @param playerId
     * @return
     */
    @Override
    public int transferBan(int playerId) {
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        String sql = "update transferinfo set status = '审核不通过' where playerId = ?";
        List<Object> message = new ArrayList<>();
        message.add(playerId);
        int result = transferInFoDao.transferInFoCud(sql,message);
        return result;
    }

    /**
     * 删除玩家的转会信息
     * 要将转会图片一起删除
     * @param transferId
     * @return
     */
    @Override
    public boolean transferDelete(int transferId) {
        TransferPictureDao transferPictureDao = new TransferPictureDaoImpl();
        String sql = "select * from transferpicture where transferInFoId = ?";
        List<Object> message = new ArrayList<>();
        message.add(transferId);
        List<TransferPicture> transferPictureList = transferPictureDao.transferPictureFind(sql,message);
        boolean result1 = false;
        //删除选手转会信息的图片
        if(transferPictureList.size()>0){
            //若选手上传了图片，执行图片删除
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

        //删除转会信息表中选手的转会信息
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        String transferSql = "delete from transferinfo where id = ?";
        List<Object> transferMessage = new ArrayList<>();
        transferMessage.add(transferId);
        int result = transferInFoDao.transferInFoCud(transferSql,transferMessage);



        if(result>0 && result1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 前端页面获取符合要求的转会信息总记录数和总页数
     *
     * @param count
     * @param classify
     * @param playerName
     * @param status
     * @return
     */
    @Override
    public int[] frontTotalPage(int count, String classify, String playerName, String status) {
        //总记录数，总页数初始值分别为0和1
        int[] total = {0,1};
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        List<Object> message = new ArrayList<>();
        String sql = null;
        //当用户选择搜索类型为全部时，对转会信息分类不作限制
        if(classify.equals("全部") && StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from  transferinfo where status = ?";
            message.add(status);
        }else if(!classify.equals("全部") && StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from  transferinfo where classify = ? and status = ?";
            message.add(classify);
            message.add(status);
        }else if(classify.equals("全部") && StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  transferinfo where status = ? and playerName like ?";
            playerName = "%"+playerName+"%";
            message.add(status);
            message.add(playerName);
        }else if(!classify.equals("全部") && StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  transferinfo where classify = ? and status = ? and playerName like ?";
            playerName = "%"+playerName+"%";
            message.add(classify);
            message.add(status);
            message.add(playerName);
        }
        List<TransferInFo> transferInFoList = transferInFoDao.transferInFoFind(sql,message);
        //获取总记录数
        total[0] = transferInFoList.size();
        //计算页数
        if(total[1]%count == 0){
            total[1] = total[0]/count;
        }else{
            total[1] = total[0]/count+1;
        }
        return total;
    }

    /**
     * 前端页面获取指定页数的转会信息
     *
     * @param currentPage 当前页
     * @param count       每页显示信息条数
     * @param classify    类型
     * @param playerName  玩家姓名
     * @param status      转会信息审核状态
     * @return
     */
    @Override
    public List<TransferInFo> frontTransferInFoFind(int currentPage, int count, String classify, String playerName, String status) {
        List<Object> message = new ArrayList<>();
        String sql = null;
        //当用户选择搜索类型为 全部 时，对转会信息分类不作限制
        if(classify.equals("全部")&& StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from transferinfo where status = ? limit ?,?";
            message.add(status);
        }else if(!classify.equals("全部")&& StringJudgeUtil.isEmpty(playerName)){
            sql  ="select * from  transferinfo where status = ? and classify = ? limit ?,?";
            message.add(status);
            message.add(classify);
        }else if(classify.equals("全部") && StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  transferinfo where status = ? and playerName like ? limit ?,?";
            playerName = "%"+playerName+"%";
            message.add(status);
            message.add(playerName);
        }else if(!classify.equals("全部")&& StringJudgeUtil.isNotEmpty(playerName)){
            sql  ="select * from  transferinfo where classify = ? and status = ? and playerName like ? limit ?,?";

            playerName = "%"+playerName+"%";
            message.add(classify);
            message.add(status);
            message.add(playerName);
        }
        message.add((currentPage-1)*count);
        message.add(count);
        List<TransferInFo> transferInFoList = new ArrayList<>();
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        transferInFoList = transferInFoDao.transferInFoFind(sql,message);
        return transferInFoList;
    }

    /**
     * 根据用户id获取用户的转会详情
     *
     * @param playerId
     * @return
     */
    @Override
    public TransferInFo detailObtain(int playerId) {
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        String sql = "select * from transferinfo where playerId = ?";
        List<Object> message = new ArrayList<>();
        message.add(playerId);
        List<TransferInFo> transferInFoList = transferInFoDao.transferInFoFind(sql,message);
        TransferInFo result;
        if(transferInFoList.size()>0){
            result = transferInFoList.get(0);
        }else{
            result = null;
        }

        return result;
    }

    /**
     * 新增选手的转会信息
     *
     * @param transferInFoTxt
     * @return
     */
    @Override
    public int transferAdd(TransferInFo transferInFoTxt) {
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        String sql = "insert into transferinfo values(null,?,?,?,?,?,?,null)";
        List<Object> message = new ArrayList<>();
        message.add(transferInFoTxt.getPlayerId());
        message.add(transferInFoTxt.getPortrait());
        message.add(transferInFoTxt.getPlayerName());
        message.add(transferInFoTxt.getPrice());
        message.add(transferInFoTxt.getClassify());
        message.add(transferInFoTxt.getStatus());
        int result = transferInFoDao.transferInFoCud(sql,message);
        return result;
    }

    /**
     * 选手修改转会信息
     *
     * @param transferTxt
     * @return
     */
    @Override
    public int transferUpdate(TransferInFo transferTxt) {
        TransferInFoDao transferInFoDao = new TransferInFoDaoImpl();
        String sql = "update transferinfo set price = ?,classify = ?,status = ? where playerId = ?";
        List<Object> message = new ArrayList<>();
        message.add(transferTxt.getPrice());
        message.add(transferTxt.getClassify());
        message.add(transferTxt.getStatus());
        message.add(transferTxt.getPlayerId());
        int result = transferInFoDao.transferInFoCud(sql,message);
        return result;
    }
}
