package com.yiban.suoai.pojo;

public class Image {
    private Integer id;

    private String url;

    private Integer cyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getCyId() {
        return cyId;
    }

    public void setCyId(Integer cyId) {
        this.cyId = cyId;
    }
}