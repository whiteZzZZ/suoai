package com.yiban.suoai.pojo;

public class LikeInfo {
    private Integer id;

    private Integer cyId;

    private Integer userId;

    private Byte type;

    public LikeInfo() {
    }

    public LikeInfo(Integer cy_id, Integer user_id, Byte type) {
        this.cyId = cy_id;
        this.userId = user_id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCyId() {
        return cyId;
    }

    public void setCyId(Integer cyId) {
        this.cyId = cyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}