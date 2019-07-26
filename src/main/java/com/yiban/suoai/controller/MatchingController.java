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
@RequestMapping("matching")
@Api("匹配")
public class MatchingController {

    private static Logger logger = Logger.getLogger(MatchingController.class);// 添加日志


    @Autowired
    RedisService redisService;

    @ApiOperation(value = "普通匹配", notes = "普通匹配")
    @RequestMapping(value ="ordinaryMatch" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> ordinaryMatch(@RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                             @RequestParam("image")  @ApiParam(value = "用户ID") MultipartFile[] uploadFiles){
            return null;

    }

}
