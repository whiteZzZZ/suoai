package com.yiban.suoai.forepojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.yiban.suoai.pojo.ChatList;

import java.util.Date;

public class ForeChatList  {
    private Integer id;

    private Integer userId1;

    private Integer userId2;

    private String url;

    private String message;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date time;

    public ForeChatList(ChatList cl){
        this.id = cl.getId();
        this.userId1 = cl.getUserId1();
        this.userId2 = cl.getUserId2();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId1() {
        return userId1;
    }

    public void setUserId1(Integer userId1) {
        this.userId1 = userId1;
    }

    public Integer getUserId2() {
        return userId2;
    }

    public void setUserId2(Integer userId2) {
        this.userId2 = userId2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
