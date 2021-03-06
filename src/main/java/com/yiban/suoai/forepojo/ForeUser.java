package com.yiban.suoai.forepojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ForeUser {
    private Integer userId;

    private String name;

    private String sex;

    private String area;

    private String phone;

    private String stu_Num;

    private String headImg;

    private String backGround;

    private String school=null;

    private String academy=null;

    private String title;

    private Integer level;

    private String signature;

    private Integer paperId;

    private Integer experience;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//格式化时间
    private Date birthday;

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStu_Num() {
        return stu_Num;
    }

    public void setStu_Num(String stu_Num) {
        this.stu_Num = stu_Num;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getBackGround() {
        return backGround;
    }

    public void setBackGround(String backGround) {
        this.backGround = backGround;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titleId) {
        this.title = titleId;
    }

    public Integer getlevel() {
        return level;
    }

    public void setlevel(Integer level) {
        this.level = level;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
