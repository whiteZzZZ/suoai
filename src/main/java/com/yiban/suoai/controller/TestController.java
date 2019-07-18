package com.yiban.suoai.controller;

import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.util.MapHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("test")
@Api("测试")
public class TestController {



    @Autowired
    RedisService redisService;


    @ApiOperation(value = "登录，仅测试使用", notes = "登录，仅测试使用")
    @RequestMapping(value ="loginTest" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateMatching(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userid")  @ApiParam(value = "用户ID") Integer userid){
        Map map=new HashMap();
        redisService.addTokenToRedis(userid, token);
        return MapHelper.success();
    }

}
