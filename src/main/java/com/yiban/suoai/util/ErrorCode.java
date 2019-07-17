package com.yiban.suoai.util;

public enum ErrorCode {

    SUCCESS(200,"成功"),
    TOKEN_FAILURE(1003,"TOKEN_FAILURE"),//token验证失败
    NO_PERMISSION(1009,"NO_PERMISSION"),//没有权限
    VALUE_IS_EMPTY(1005,"VALUE_IS_EMPTY"),//值为空
    PASSWORD_ERROR(1006,"PASSWORD_ERROR"),//密码错误
    COUPONS_EXPIRED(1007,"COUPONS_EXPIRED"),
    VALUE_IS_FULL(1010,"VALUE_IS_FULL"),
    MSG_NOT_SAFE(1004,"CONTENT_NOT_SAFE"),//敏感内容
    SERVER_ERROR(10000,"SERVER_ERROR"),
    AUTH_ERROR(10001,"AUTH_ERROR"),
    PARAMS_ERROR(10002,"PARAMS_ERROR"),
    JSON_PARSE_ERROR(10003,"JSON_PARSE_ERROR"),
    ILLEAGAL_STRING(15001,"ILLEAGAL_STRING"),
    UNKNOW_ERROR(16000,"UNKNOW_ERROR");


    private int code;
    private String msg;

    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}