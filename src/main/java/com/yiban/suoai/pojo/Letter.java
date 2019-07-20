package com.yiban.suoai.pojo;

import java.util.Date;

public class Letter {
    private Integer id;

    private String headline;

    private Date time;

    private Boolean myself;

    private Boolean hide;

    private Boolean publish;

    private Integer userId;

    private Boolean isDelete;

    private Boolean isRead;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline == null ? null : headline.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}