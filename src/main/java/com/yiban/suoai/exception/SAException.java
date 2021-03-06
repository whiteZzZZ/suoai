package com.yiban.suoai.exception;

import com.yiban.suoai.util.ErrorCode;

public class SAException  extends Exception{

    public String message;
    public String errorCode;
    public ErrorCode code;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public SAException() {
    }

    public SAException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public SAException(ErrorCode code){
        this.code = code;
    }
}
