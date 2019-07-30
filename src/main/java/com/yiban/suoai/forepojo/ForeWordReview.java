package com.yiban.suoai.forepojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ForeWordReview {
    private Integer reviewId;

    private Integer word_id;

    private Integer user_id;

    private Integer like_time;

    private Integer review_time;

    private String content;

    private String head_img;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//格式化时间
    private Date time;

    private Boolean ifLike=false;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getWord_id() {
        return word_id;
    }

    public void setWord_id(Integer Word_id) {
        this.word_id = Word_id;
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

    public Boolean getIfLike() {
        return ifLike;
    }

    public void setIfLike(Boolean ifLike) {
        this.ifLike = ifLike;
    }
}
