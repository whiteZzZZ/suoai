package com.yiban.suoai.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LetterMessage {
    private Integer id;

    private Integer letter_id;

    private String message;

    private Integer user_id;

    private Boolean is_delete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//格式化时间
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLetter_id() {
        return letter_id;
    }

    public void setLetter_id(Integer letter_id) {
        this.letter_id = letter_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}