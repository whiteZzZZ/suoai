package com.yiban.suoai.pojo;

import java.util.Date;

public class WordReview {
    private Integer id;

    private Integer word_id;

    private Integer user_id;

    private Integer like_time;

    private Integer review_time;

    private Date time;

    private Integer paper;

    private Boolean is_delete;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWord_id() {
        return word_id;
    }

    public void setWord_id(Integer word_id) {
        this.word_id = word_id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getPaper() {
        return paper;
    }

    public void setPaper(Integer paper) {
        this.paper = paper;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}