package com.yiban.suoai.pojo;

public class Image {
    private Integer id;

    private String url;

    private Integer cy_id;

    public Image(String url, Integer cy_id) {
        this.url = url;
        this.cy_id = cy_id;
    }

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

    public Integer getCy_id() {
        return cy_id;
    }

    public void setCy_id(Integer cy_id) {
        this.cy_id = cy_id;
    }
}