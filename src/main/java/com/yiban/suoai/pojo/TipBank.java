package com.yiban.suoai.pojo;

public class TipBank extends Tip{
    private Integer id;

    private Integer source;

    private Integer sourceId;

    private Integer type;

    private String content;

    private Integer status;

    private Boolean ans;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getSource() {
        return source;
    }

    @Override
    public void setSource(Integer source) {
        this.source = source;
    }

    @Override
    public Integer getSourceId() {
        return sourceId;
    }

    @Override
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getAns() {
        return ans;
    }

    public void setAns(Boolean ans) {
        this.ans = ans;
    }
}