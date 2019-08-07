package com.yiban.suoai.exception;

import com.yiban.suoai.exception.SAException;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 统一的异常类进行处理(把默认的异常返回信息改成自定义的异常返回信息)
// SAException，将自动找到此类中对应的方法执行，并返回json数据给前台
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(value = SAException.class)    //异常处理器，SAException
    public Map<String, Object> hanlerException(HttpServletRequest request, SAException e){
        Map<String, Object> map=new HashMap<>();
        map.put("message",e.getMessage());
        map.put("error",e.getErrorCode());
        map.put("ErrorCode",e.getCode());
        return map;
    }
}
