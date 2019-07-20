package com.yiban.suoai.pojo;

import java.util.Date;

public class Message {
    private Integer id;

    private Integer type;

    private Integer cyId;

    private Integer sponsorId;

    private Integer userId;

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

    public Integer getCyId() {
        return cyId;
    }

    public void setCyId(Integer cyId) {
        this.cyId = cyId;
    }

    public Integer getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Integer sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}