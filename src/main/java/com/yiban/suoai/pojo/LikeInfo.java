package com.yiban.suoai.pojo;

public class LikeInfo {
    private Integer id;

    private Integer cy_id;

    private Integer user_id;

    private Byte type;

    public LikeInfo() {
    }

    public LikeInfo(Integer cy_id, Integer user_id, Byte type) {
        this.cy_id = cy_id;
        this.user_id = user_id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCy_id() {
        return cy_id;
    }

    public void setCy_id(Integer cy_id) {
        this.cy_id = cy_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}