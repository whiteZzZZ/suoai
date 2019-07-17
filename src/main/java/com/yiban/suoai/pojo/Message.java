package com.yiban.suoai.pojo;

import java.util.Date;

public class Message {
    private Integer id;

    private Integer type;

    private Integer cy_id;

    private Integer sponsor_id;

    private Integer user_id;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCy_id() {
        return cy_id;
    }

    public void setCy_id(Integer cy_id) {
        this.cy_id = cy_id;
    }

    public Integer getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(Integer sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}