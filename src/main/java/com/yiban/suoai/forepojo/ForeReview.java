package com.yiban.suoai.forepojo;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ForeReview {

    private Integer reviewId;

    private Integer cy_id;

    private Integer user_id;

    private Integer like_time;

    private Integer review_time;

    private String content;

    private String head_img;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//格式化时间
    private Date time;

    private Integer reply_id=0;

    private Integer reply_user_id=0;

    private String reply_content=null;

    private String reply_name=null;

    private Boolean ifLike=false;

    private Boolean Reply=false;

    public Boolean getReply() {
        return Reply;
    }

    public void setReply(Boolean reply) {
        Reply = reply;
    }

    public Boolean getIfLike() {
        return ifLike;
    }

    public void setIfLike(Boolean ifLike) {
        this.ifLike = ifLike;
    }

    public Integer getReply_user_id() {
        return reply_user_id;
    }

    public void setReply_user_id(Integer reply_user_id) {
        this.reply_user_id = reply_user_id;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getReply_name() {
        return reply_name;
    }

    public void setReply_name(String reply_name) {
        this.reply_name = reply_name;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
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

    public Integer getLike_time() {
        return like_time;
    }

    public void setLike_time(Integer like_time) {
        this.like_time = like_time;
    }

    public Integer getReview_time() {
        return review_time;
    }

    public void setReview_time(Integer review_time) {
        this.review_time = review_time;
    }

    public Integer getReply_id() {
        return reply_id;
    }

    public void setReply_id(Integer reply_id) {
        this.reply_id = reply_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
