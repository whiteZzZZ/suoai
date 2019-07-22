package com.yiban.suoai.controller;


import com.yiban.suoai.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("imform")
@Api("通知")
public class ImformController {

    private static Logger logger = Logger.getLogger(ImformController.class);// 添加日志


    @Autowired
    RedisService redisService;


  /* @ApiOperation(value = "获取消息总数", notes = "获取收到的表白")
    @RequestMapping(value ="expression" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> expressionPut(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,*/



}


