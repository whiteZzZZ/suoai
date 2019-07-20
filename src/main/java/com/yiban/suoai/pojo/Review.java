package com.yiban.suoai.pojo;

import java.util.Date;

public class Review {
    private Integer id;

    private Integer cyId;

    private Integer userId;

    private Integer likeTime;

    private Integer replyId;

    private Date time;

    private Boolean isDelete;

    private Integer reviewTime;

    private String content;

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

    public Integer getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Integer likeTime) {
        this.likeTime = likeTime;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Integer reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}