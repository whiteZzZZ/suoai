package com.yiban.suoai.forepojo;

import com.yiban.suoai.pojo.Tip;
import com.yiban.suoai.pojo.TipBank;

public class ForeTip extends TipBank {
    private Integer id;

    private Integer source;

    private Integer sourceId;

    private Integer type;

    private String content;

    private Integer status;

    private String url;

    private Boolean ans;

    public ForeTip(Tip t,String url){
        this.id = t.getId();
        this.source = t.getSource();
        this.sourceId = t.getSourceId();
        this.type = t.getType();
        this.content = t.getContent();
        this.status = t.getStatus();
        this.url = url;
    }

    public ForeTip(Tip t){
        this.id = t.getId();
        this.source = t.getSource();
        this.sourceId = t.getSourceId();
        this.type = t.getType();
        this.content = t.getContent();
        this.status = t.getStatus();
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
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
        this.content = content;
    }
    @Override
    public Integer getStatus() {
        return status;
    }
    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public Boolean getAns() {
        return ans;
    }

    public void setAns(boolean ans) {
        this.ans = ans;
    }
}
