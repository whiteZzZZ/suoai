package com.yiban.suoai.controller;


import com.yiban.suoai.pojo.User;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.UserService;
import com.yiban.suoai.util.MapHelper;
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

    @ApiOperation(value = "更改匹配功能状态", notes = "更改匹配功能状态")
    @RequestMapping(value ="updateMatching" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateMatching(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userid")  @ApiParam(value = "用户ID") Integer userid,
            @RequestParam("key")    @ApiParam(value = "功能状态") byte key ){
        User user = userService.get(userid);
        if(key == 1) {
            user.setIs_match(true);
        }else {
            user.setIs_match(false);
        }
        userService.update(user);
        return MapHelper.success();
    }



    @ApiOperation(value = "更改特定表白状态", notes = "更改特定表白状态")
    @RequestMapping(value ="updatePaticular" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updatePaticular(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userid")  @ApiParam(value = "用户ID") Integer userid,
            @RequestParam("key")    @ApiParam(value = "功能状态") byte key ){
        User user = userService.get(userid);
        if(key == 1) {
            user.setIs_particular(true);
        }else {
            user.setIs_particular(false);
        }
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改参与排行榜状态", notes = "更改参与排行榜状态")
    @RequestMapping(value ="updateRanking" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateRanking(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userid")  @ApiParam(value = "用户ID") Integer userid,
            @RequestParam("key")    @ApiParam(value = "功能状态") byte key ){
        User user = userService.get(userid);
        if(key == 1) {
            user.setIs_rank(true);
        }else {
            user.setIs_rank(false);
        }
        userService.update(user);
        return MapHelper.success();
    }


    @ApiOperation(value = "通过学号和姓名查找用户",notes = "通过学号和姓名查找用户")
    @RequestMapping(value = "finduser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findUser(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userName")  @ApiParam(value = "用户姓名") String userid,
            @RequestParam("num")    @ApiParam(value = "学号") Integer key ){

        return MapHelper.success();
    }

    @ApiOperation(value = "获取用户信息卡",notes = "获取用户信息卡")
    @RequestMapping(value = "show",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> show(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userId")  @ApiParam(value = "用户Id") Integer userid){

        return MapHelper.success();
    }

    @ApiOperation(value = "更改用户资料",notes = "更改用户资料")
    @RequestMapping(value = "updateUserData",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUserData(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userId")  @ApiParam(value = "用户Id") Integer userid) {

        return MapHelper.success();
    }




}
