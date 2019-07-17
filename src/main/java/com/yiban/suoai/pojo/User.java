package com.yiban.suoai.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String head_img;

    private String bg_img;

    private String phone;

    private String password;

    private String sha;

    private String salt;

    private Boolean sex;

    private Date birthday;

    private String area;

    private Integer school_id;

    private Integer academy_id;

    private String stu_num;

    private String signature;

    private Boolean is_particular;

    private Boolean is_match;

    private Boolean is_rank;

    private Boolean violator;

    private Integer express_time;

    private Integer experience;

    private Integer title_id;

    private Integer paper;

    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img == null ? null : head_img.trim();
    }

    public String getBg_img() {
        return bg_img;
    }

    public void setBg_img(String bg_img) {
        this.bg_img = bg_img == null ? null : bg_img.trim();
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

    public String getStu_num() {
        return stu_num;
    }

    public void setStu_num(String stu_num) {
        this.stu_num = stu_num == null ? null : stu_num.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public Boolean getIs_particular() {
        return is_particular;
    }

    public void setIs_particular(Boolean is_particular) {
        this.is_particular = is_particular;
    }

    public Boolean getIs_match() {
        return is_match;
    }

    public void setIs_match(Boolean is_match) {
        this.is_match = is_match;
    }

    public Boolean getIs_rank() {
        return is_rank;
    }

    public void setIs_rank(Boolean is_rank) {
        this.is_rank = is_rank;
    }

    public Boolean getViolator() {
        return violator;
    }

    public void setViolator(Boolean violator) {
        this.violator = violator;
    }

    public Integer getExpress_time() {
        return express_time;
    }

    public void setExpress_time(Integer express_time) {
        this.express_time = express_time;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getTitle_id() {
        return title_id;
    }

    public void setTitle_id(Integer title_id) {
        this.title_id = title_id;
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
}