package com.yiban.suoai.exception;

public class SAException  extends Exception{

    public String message;
    public String errorCode;

    public SAException() {
    }

    public SAException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
