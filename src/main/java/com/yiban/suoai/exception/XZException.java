package com.yiban.suoai.exception;


import com.yiban.suoai.util.ErrorCode;

@SuppressWarnings("serial")
public class XZException extends Exception {

    public String message;
    public String errorCode;

    private ErrorCode errorCode2;

    public XZException(String errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public XZException(ErrorCode errorCode2, String msg) {
        super(msg);
        this.errorCode2 = errorCode2;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}