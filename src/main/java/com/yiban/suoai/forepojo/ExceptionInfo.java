package com.yiban.suoai.forepojo;

import com.yiban.suoai.util.ErrorCode;

public class ExceptionInfo {

    public String message;
    public String errorCode;
    public ErrorCode code;

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
}
