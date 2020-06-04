package com.chedilong.event.entity;

/**
 * 转会信息图片实体类
 */
public class TransferPicture {
    private Integer id;
    private Integer transferId;
    private String picture;
    private Integer playerId;

    public TransferPicture(Integer id, Integer transferId, String picture, Integer playerId) {
        this.id = id;
        this.transferId = transferId;
        this.picture = picture;
        this.playerId = playerId;
    }
    public TransferPicture(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
