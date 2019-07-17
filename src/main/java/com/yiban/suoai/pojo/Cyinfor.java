package com.yiban.suoai.pojo;

import java.util.Date;

public class Cyinfor {
    private Integer id;

    private Integer user_id;

    private Integer school_id;

    private Integer academy_id;

    private Date time;

    private Integer like_time;

    private Integer review_time;

    private Boolean privacy;

    private Boolean hide;

    private Integer who;

    private Integer paper_id;

    private Integer has_image;

    private Boolean is_delete;

    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Integer school_id) {
        this.school_id = school_id;
    }

    public Integer getAcademy_id() {
        return academy_id;
    }

    public void setAcademy_id(Integer academy_id) {
        this.academy_id = academy_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    public Integer getWho() {
        return who;
    }

    public void setWho(Integer who) {
        this.who = who;
    }

    public Integer getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public Integer getHas_image() {
        return has_image;
    }

    public void setHas_image(Integer has_image) {
        this.has_image = has_image;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}