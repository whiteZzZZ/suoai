package com.yiban.suoai.forepojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ForeCyinfor {

    private Integer id;

    private Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//格式化时间
    private Date time;

    private Integer like_time;

    private String text;

    private String head_img;

    private String name;

    private String image1;

    private String image2;

    private Boolean ifLike=false;

    public Boolean getIfLike() {
        return ifLike;
    }

    public void setIfLike(Boolean ifLike) {
        this.ifLike = ifLike;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
}
