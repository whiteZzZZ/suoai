package com.yiban.suoai.weixin;

public class UnionidAndOpenId {

    private String openID;
    private String UnionID;

    public UnionidAndOpenId() {
        // Auto-generated constructor stub
    }

    public UnionidAndOpenId(String UnionID, String openID) {
        // Auto-generated constructor stub
        this.openID = openID;
        this.UnionID = UnionID;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getUnionID() {
        return UnionID;
    }

    public void setUnionID(String unionID) {
        UnionID = unionID;
    }


}
