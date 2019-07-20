package com.yiban.suoai.controller;


import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeUser;
import com.yiban.suoai.pojo.Academy;
import com.yiban.suoai.pojo.School;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.service.AcademyService;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.SchoolService;
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
   @Autowired
    SchoolService schoolService;
   @Autowired
    AcademyService academyService;

    @ApiOperation(value = "更改匹配功能状态", notes = "更改匹配功能状态")
    @RequestMapping(value ="updateMatching" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateMatching(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userid")  @ApiParam(value = "用户ID") Integer userid,
            @RequestParam("key")    @ApiParam(value = "功能状态（1开启，0不开启）") byte key ){
        User user = userService.get(userid);
        if(key == 1) {
            user.setIsMatch(true);
        }else {
            user.setIsMatch(false);
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
            @RequestParam("key")    @ApiParam(value = "功能状态（1开启，0不开启）") byte key ){
        User user = userService.get(userid);
        if(key == 1) {
            user.setIsParticular(true);
        }else {
            user.setIsParticular(false);
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
            @RequestParam("key")    @ApiParam(value = "功能状态（1开启，0不开启）") byte key ){
        User user = userService.get(userid);
        if(key == 1) {
            user.setIsRank(true);
        }else {
            user.setIsRank(false);
        }
        userService.update(user);
        return MapHelper.success();
    }


    @ApiOperation(value = "通过学号和姓名查找用户",notes = "通过学号和姓名查找用户")
    @RequestMapping(value = "finduser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findUser(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userName")  @ApiParam(value = "用户姓名") String userName,
            @RequestParam("num")    @ApiParam(value = "学号") String num ){
        User user = userService.selectByNameNum(userName, num);
        Map map = MapHelper.success();
        map.put("userId",user.getId());
        return map;
    }

    @ApiOperation(value = "获取用户信息卡",notes = "获取用户信息卡")
    @RequestMapping(value = "show",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> show(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException {
        int userid = redisService.getUserId(token);
        User user = userService.get(userid);
        ForeUser foreUser = new ForeUser();
        foreUser.setUserId(userid);
        foreUser.setName(user.getName());
        foreUser.setHeadImg(user.getHeadImg());
        foreUser.setBackGround(user.getBgImg());
        foreUser.setTitleId(user.getTitleId());
        foreUser.setArea(user.getArea());
        foreUser.setSignature(user.getSignature());
        foreUser.setRank(user.getLevel());
        foreUser.setBirthday(user.getBirthday());
        foreUser.setStu_Num(user.getStuNum());
        foreUser.setPaperId(user.getPaper());
        if(user.getSex()==true){
            foreUser.setSex("男");
        }else {
            foreUser.setSex("女");
        }
        if(user.getSchoolId()!=null) {
            School school = schoolService.get(user.getSchoolId());
            foreUser.setSchool(school.getName());
        }
        if(user.getAcademyId()!=null) {
            Academy academy = academyService.get(user.getAcademyId());
            foreUser.setAcademy(academy.getName());
        }
        Map map = MapHelper.success();
        map.put("user",foreUser);
        return map;
    }

    @ApiOperation(value = "更改用户资料",notes = "更改用户资料")
    @RequestMapping(value = "updateUserData",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUserData(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("name") @ApiParam(value = "名字") String name,
            @RequestParam("sex") @ApiParam(value = "性别") byte sex,
            @RequestParam(value = "introduction", required = false) @ApiParam(value = "介绍")String introduction,
            @RequestParam(value = "email", required = false) @ApiParam(value = "email") String email,
            @RequestParam(value = "hasFile", required = false, defaultValue = "0") @ApiParam(value = "头像文件") byte hasFile) throws SAException{
        User user = userService.get(redisService.getUserId(token));

        return MapHelper.success();
    }




}
