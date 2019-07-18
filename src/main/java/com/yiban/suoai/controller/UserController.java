package com.yiban.suoai.controller;


import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("user")
@Api("用户")
public class UserController {


    private static Logger logger = Logger.getLogger(UserController.class);// 添加日志

   @Autowired
   UserService userService;
   @Autowired
   RedisService redisService;

    @ApiOperation(value = "更改匹配功能状态", notes = "用户获得广告接口，更改匹配功能状态")
    @RequestMapping(value ="updateMatching" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> updateMatching(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userid")  @ApiParam(value = "用户ID") Integer userid){
        return  null;
    }


}
