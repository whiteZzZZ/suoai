package com.yiban.suoai.util;

import lombok.Data;


@Data
public class ApiResult {
    /**
     * 错误码
     * 如果是成功，则code为200
     */
    private int code;
    /**
     * 对错误的具体解释
     */
    private String message;
    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private final Object value;

    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getValue() {
		return value;
	}

	public ApiResult(int code, String message, Object value) {
        this.code = code;
        this.message = message;
        this.value = value;
    }

    public ApiResult(ErrorCode code, Object value) {
        this.code = code.getCode();
        this.message = code.getMsg();
        this.value = value;
    }

    public static ApiResult valueOf(Object body) {
        return new ApiResult(ErrorCode.SUCCESS.getCode(),ErrorCode.SUCCESS.getMsg(),body);
    }

    public static ApiResult errorOf(ErrorCode errorCode, String message) {
        return new ApiResult(errorCode.getCode(),errorCode.getMsg(),message);
    }
}