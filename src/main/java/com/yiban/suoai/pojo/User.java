package com.yiban.suoai.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private Integer id;

    private String turename;

    private String name;

    private String headImg;

    private String bgImg;

    private String phone;

    private String password="";

    private String sha="";

    private String salt="";

    private Boolean sex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//格式化时间
    private Date birthday;

    private String area;

    private Integer schoolId=1;

    private Integer academyId=1;

    private String stuNum="";

    private String signature;

    private Boolean isParticular=true;

    private Boolean isMatch=true;

    private Boolean isRank=true;

    private Boolean violator=false;

    private Integer expressTime=0;

    private Integer experience=0;

    private Integer titleId=1;

    private Integer paper=1;

    private Integer level=1;

    private Integer yibanid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTurename() {
        return turename;
    }

    public void setTurename(String turename) {
        this.turename = turename == null ? null : turename.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg == null ? null : bgImg.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha == null ? null : sha.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum == null ? null : stuNum.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public Boolean getIsParticular() {
        return isParticular;
    }

    public void setIsParticular(Boolean isParticular) {
        this.isParticular = isParticular;
    }

    public Boolean getIsMatch() {
        return isMatch;
    }

    public void setIsMatch(Boolean isMatch) {
        this.isMatch = isMatch;
    }

    public Boolean getIsRank() {
        return isRank;
    }

    public void setIsRank(Boolean isRank) {
        this.isRank = isRank;
    }

    public Boolean getViolator() {
        return violator;
    }

    public void setViolator(Boolean violator) {
        this.violator = violator;
    }

    public Integer getExpressTime() {
        return expressTime;
    }

    public void setExpressTime(Integer expressTime) {
        this.expressTime = expressTime;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public Integer getPaper() {
        return paper;
    }

    public void setPaper(Integer paper) {
        this.paper = paper;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getYibanid() {
        return yibanid;
    }

    public void setYibanid(Integer yibanid) {
        this.yibanid = yibanid;
    }
}