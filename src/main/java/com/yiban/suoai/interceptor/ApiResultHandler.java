package com.yiban.suoai.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiban.suoai.util.ApiResult;
import com.yiban.suoai.util.ErrorCode;
import com.yiban.suoai.util.ApiResult;
import com.yiban.suoai.util.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

@ControllerAdvice(annotations = RestController.class)
public class ApiResultHandler implements ResponseBodyAdvice {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    private ThreadLocal<ObjectMapper>  mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    private static final Class[] annos = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class
    };

    /**
     * 对所有RestController的接口方法进行拦截
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    	Object out;
        ObjectMapper mapper = mapperThreadLocal.get();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if(body instanceof ApiResult){
            out = body;
        }
        else if (body instanceof ErrorCode){
            ErrorCode errorCode = (ErrorCode) body;
            out = new ApiResult(errorCode.getCode(),errorCode.getMsg(),null);
        }
        else if (body instanceof String){
            ApiResult result = ApiResult.valueOf(body);
            try {
                //因为是String类型，我们要返回Json字符串，否则SpringBoot框架会转换出错
                out = mapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                out = ApiResult.errorOf(ErrorCode.JSON_PARSE_ERROR,e.getMessage());
            }
        }
        else{
            out = ApiResult.valueOf(body);
        }
        return out;
    }


}
