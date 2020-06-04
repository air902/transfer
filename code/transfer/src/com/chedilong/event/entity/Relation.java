package com.chedilong.event.entity;

/**
 * 选手与管理员关系实体类
 */
public class Relation {
    private Integer id;
    private Integer playerId;
    private Integer managerId;

    public Relation(Integer id, Integer playerId, Integer managerId) {
        this.id = id;
        this.playerId = playerId;
        this.managerId = managerId;
    }
    public Relation(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}
