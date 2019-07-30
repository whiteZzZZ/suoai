package com.yiban.suoai.forepojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ForeLetter {
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//格式化时间
    private Date time;

    private String headLine;

    private String content;

    private Boolean myself;

    private Boolean hide;

    private Boolean publish;

    private Integer userId;

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

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getMyself() {
        return myself;
    }

    public void setMyself(Boolean myself) {
        this.myself = myself;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }
}
