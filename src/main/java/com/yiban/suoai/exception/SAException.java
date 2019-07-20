package com.yiban.suoai.exception;

import com.yiban.suoai.util.ErrorCode;

public class SAException  extends Exception{

    public String message;
    public String errorCode;
    public ErrorCode code;

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
