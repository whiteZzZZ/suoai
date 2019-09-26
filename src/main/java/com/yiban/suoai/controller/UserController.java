package com.yiban.suoai.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.forepojo.ForeUser;
import com.yiban.suoai.pojo.*;
import com.yiban.suoai.service.*;
import com.yiban.suoai.util.*;
import com.yiban.suoai.weixin.UnionidAndOpenId;
import com.yiban.suoai.yiban.AppContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.experimental.Helper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
   @Autowired
    DailySentenceService dailySentenceService;
//   @Autowired
//   InformService informService;
   @Autowired
   CyinforService cyinforService;
   @Autowired
   TitleService titleService;

    @ApiOperation(value = "获取侧拉栏状态", notes = "获取侧拉栏状态")
    @RequestMapping(value ="getState" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getState(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException{
        Map map = MapHelper.success();
        int userId = redisService.getUserId(token);
        User user = userService.get(userId);
        map.put("match",user.getIsMatch());
        map.put("paticular",user.getIsParticular());
        map.put("ranking",user.getIsRank());
        return map;
    }

    @ApiOperation(value = "更改匹配功能状态", notes = "更改匹配功能状态")
    @RequestMapping(value ="updateMatching" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateMatching(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("key")    @ApiParam(value = "功能状态（1开启，0不开启）") byte key ) throws SAException{
        int userId = redisService.getUserId(token);
        User user = userService.get(userId);
        if(key == 1) {
            user.setIsMatch(true);
        }else {
            user.setIsMatch(false);
        }
        userService.update(user);
        return MapHelper.success();
    }



    @ApiOperation(value = "更改特定表白状态", notes = "更改特定表白状态")
    @RequestMapping(value ="updatePaticular" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updatePaticular(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("key")    @ApiParam(value = "功能状态（1开启，0不开启）") byte key ) throws SAException{
        int userId = redisService.getUserId(token);
        User user = userService.get(userId);
        if(key == 1) {
            user.setIsParticular(true);
        }else {
            user.setIsParticular(false);
        }
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改参与排行榜状态", notes = "更改参与排行榜状态")
    @RequestMapping(value ="updateRanking" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateRanking(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("key")    @ApiParam(value = "功能状态（1开启，0不开启）") byte key ) throws SAException{
        int userId = redisService.getUserId(token);
        User user = userService.get(userId);
        if(key == 1) {
            user.setIsRank(true);
        }else {
            user.setIsRank(false);
        }
        userService.update(user);
        return MapHelper.success();
    }


    @ApiOperation(value = "通过学号和姓名查找用户",notes = "通过学号和姓名查找用户")
    @RequestMapping(value = "showByName",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findUser(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userName")  @ApiParam(value = "用户姓名") String userName){
        List<ForeUser> search = userService.search(userName);
        Map map = MapHelper.success();
        map.put("data",search);
        return map;
    }

    @ApiOperation(value = "获取用户信息卡",notes = "获取用户信息卡")
    @RequestMapping(value = "show",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> show1(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException {
        int userid = redisService.getUserId(token);
        User user = userService.get(userid);
        ForeUser foreUser = new ForeUser();
        foreUser.setUserId(userid);
        foreUser.setName(user.getName());
        foreUser.setHeadImg(user.getHeadImg());
        foreUser.setBackGround(user.getBgImg());
        foreUser.setTitle(titleService.get(user.getTitleId()).getName());
        foreUser.setArea(user.getArea());
        foreUser.setSignature(user.getSignature());
        foreUser.setlevel(user.getLevel());
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

    @ApiOperation(value = "获取指定用户信息卡",notes = "获取指定用户信息卡")
    @RequestMapping(value = "/show/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> show2(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @PathVariable("userId") @ApiParam(value = "用户Id") Integer userId) throws SAException {
        User user = userService.get(userId);
        ForeUser foreUser = new ForeUser();
        foreUser.setUserId(userId);
        foreUser.setName(user.getName());
        foreUser.setHeadImg(user.getHeadImg());
        foreUser.setBackGround(user.getBgImg());
        foreUser.setTitle(titleService.get(user.getTitleId()).getName());
        foreUser.setArea(user.getArea());
        foreUser.setSignature(user.getSignature());
        foreUser.setlevel(user.getLevel());
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

    @ApiOperation(value = "更改用户资料-姓名",notes = "更改用户资料-姓名")
    @RequestMapping(value = "updateUserName",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserName(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("name") @ApiParam(value = "名字") String name
            ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        user.setName(name);
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改用户资料-真实姓名",notes = "更改用户资料-真实姓名")
    @RequestMapping(value = "updateUserRealName",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserRealName(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("realName") @ApiParam(value = "名字") String name
    ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        user.setTurename(name);
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "获取当前用户拥有称号(默认拥有一个id为1，名字是等级的lv.1的称号)",notes = "获取当前用户拥有称号")
    @RequestMapping(value = "userTitle",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> updateUserTitle(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token
    ) throws SAException{
        int userId = redisService.getUserId(token);
        List<Title> byUserId = titleService.getByUserId(userId);
        Map success = MapHelper.success();
        success.put("data",byUserId);
        return success;
    }


    @ApiOperation(value = "更改用户资料-称号",notes = "更改用户资料-称号")
    @RequestMapping(value = "userTitle",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserTitle(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("title") @ApiParam(value = "称号Id") Integer titleId
    ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        user.setTitleId(titleId);
        userService.update(user);
        return MapHelper.success();
    }


    @ApiOperation(value = "更改用户资料-性别",notes = "更改用户资料-性别")
    @RequestMapping(value = "updateUserSex",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserSex(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("sex") @ApiParam(value = "性别") String sex
            ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        if(sex.equals("男")){
            user.setSex(true);
        }else {
            user.setSex(false);
        }
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改用户资料-学院",notes = "更改用户资料-学院")
    @RequestMapping(value = "updateUserAcademy",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserAcademy(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("schoolId") @ApiParam(value = "所处学校") Integer schoolId,
            @RequestParam("academyId") @ApiParam(value = "学院Id") Integer adademyId
            ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        List<Academy> academies = academyService.selectBySchool(schoolId);
        int id = academies.get(adademyId-1).getId();
        user.setAcademyId(id);
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改用户资料-学校",notes = "更改用户资料-学校")
    @RequestMapping(value = "updateUserSchool",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserSchool(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("schoolId") @ApiParam(value = "学校Id") Integer schoolId
            ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        user.setSchoolId(schoolId);
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改用户资料-地区",notes = "更改用户资料-地区")
    @RequestMapping(value = "updateUserArea",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserArea(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("area") @ApiParam(value = "地区") String area
            ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        user.setArea(area);
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改用户资料-签名",notes = "更改用户资料-签名")
    @RequestMapping(value = "updateUserSignature",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserSignature(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam(value = "signature", required = false) @ApiParam(value = "签名")String signature
            ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        user.setSignature(signature);
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改用户资料-生日",notes = "更改用户资料-生日")
    @RequestMapping(value = "updateUserBirthday",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserBirthday(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("birthday") @ApiParam(value = "生日") long birthday
            ) throws SAException, ParseException {
        User user = userService.get(redisService.getUserId(token));
        Date startTime = new Date(birthday*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setBirthday(sdf.parse(sdf.format(startTime)));
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改用户资料-电话",notes = "更改用户资料-电话")
    @RequestMapping(value = "updateUserPhone",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserPhone(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam(value = "phone", required = false) @ApiParam(value = "phone") String phone
            ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        user.setPhone(phone);
        userService.update(user);
        return MapHelper.success();
    }

    @ApiOperation(value = "更改用户资料-学号",notes = "更改用户资料-学号")
    @RequestMapping(value = "updateUserNum",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUserNum(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam(value = "num", required = false) @ApiParam(value = "num") String num
    ) throws SAException{
        User user = userService.get(redisService.getUserId(token));
        user.setStuNum(num);
        userService.update(user);
        return MapHelper.success();
    }


    @ApiOperation(value = "获取每日一句",notes = "获取每日一句")
    @RequestMapping(value = "dailySentence",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> dailySentenceGet(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token
            ) throws SAException{
        Map map = MapHelper.success();
        DailySentence byDay = dailySentenceService.getByDay();
        map.put("word",byDay.getWord());
        map.put("author",byDay.getAuthor());
        map.put("img",byDay.getImage());
        return map;
    }

    @ApiOperation(value = "获取我的表白",notes = "获取我的表白")
    @RequestMapping(value = "MyExpression",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> MyExpressionGet(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
                @RequestParam("startPage") @ApiParam(value = "起始页") Integer start
    ) throws SAException{
        int userId=redisService.getUserId(token);
        PageHelper.offsetPage(start * PageUtil.pageSize,  PageUtil.pageSize);
        List<Cyinfor> cyinfors = cyinforService.getByUserId(userId);
        int total = (int) new PageInfo<>(cyinfors).getTotal();
        List<ForeCyinfor>  list=cyinforService.foreFull(cyinfors,userId);//里面方法有 对每个表白判断  当前的这个用户是不是点了赞的  也有图片的路径

        Map<String, Object> map = MapHelper.success();
        map.put("data", list);
        map.put("page", PageUtil.getPage(total, cyinfors.size(), start));
        return map;
    }


//    @ApiOperation(value = "用户登录", notes = "用户登录")
//    @RequestMapping(value = "forelogin", method = RequestMethod.GET)
//    @ResponseBody
//    public Map login(
//            @RequestParam("JSCODE") @ApiParam(value = "JSCODE") String JSCODE,
//            @RequestParam("encryptedData") @ApiParam(value = "微信加密内容") String encryptedData,
//            @RequestParam("iv") @ApiParam(value = "微信解密信息") String iv, HttpServletResponse resp) throws Exception {
//
//        UnionidAndOpenId unionidAndOpenId = XzAppUtil.getUnionId(JSCODE, encryptedData, iv);
////        String unionId = unionidAndOpenId.getUnionID();
//        String openId = unionidAndOpenId.getOpenID();
//        User user = userService.getByOperId(openId);
//       // User user = userServices.get(openId);//检查openId是否存在数据库中，如果时该用户第一次登陆，则存入该用户
//
//        if (null == user) {
//
////            user = UserFactory.getUser("", openId, "https://zhouzhi-gz.oss-cn-shenzhen.aliyuncs.com/xinzhi/Data/headImg/default.jpg");
////            userServices.add(user);
//            user = new User();
//            user.setOpenid(openId);
//            String token = DigestUtils.md5Hex(openId + AppUtil.getDate());// 根据openid和当前时间生成随机token
//            redisService.addTokenToRedis(user.getId(),token);
//            //redisServices.addTokenToRedis(user.getUserId(), token);// 将该随机token存入Redis中，用作登录态的维持
//            Map map = MapHelper.success();
//            map.put("token", token);
//            map.put("userId", user.getId());
//            map.put("isNew", 1);
//            return map;
//        } else {
//            String token = DigestUtils.md5Hex(openId + AppUtil.getTime());
//            redisService.addTokenToRedis(user.getId(),token);
//          //  redisServices.addTokenToRedis(user.getUserId(), token);// 将该随机token存入Redis中
//            Map map = MapHelper.success();
//            map.put("token", token);
//            map.put("userId", user.getId());
//            map.put("isNew", 0);
//            return map;
//        }
//
//    }




//    @ApiOperation(value = "发送举报信息",notes = "发送举报信息")
//    @RequestMapping(value = "inform",method = RequestMethod.PUT)
//    @ResponseBody
//    public Map<String, Object> inform(
//            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
//            @RequestParam("typeId") @ApiParam(value = "类型") Integer typeId,
//            @RequestParam(value = "cyId") @ApiParam(value = "创阅Id") Integer cyId,
//            @RequestParam(value = "text") @ApiParam(value = "内容") String text
//            ) throws SAException{
//        int userId = redisService.getUserId(token);
//        Inform inform = informService.get(cyId);
//        if(inform == null){
//            Inform inform1 = new Inform();
//            inform1.setId(cyId);
//            inform1.setCheck(0);
//            if(text!=null) {
//                inform1.setContent(text);
//            }
//            inform1.setNum(1);
//            inform1.setUserId(userId);
//            inform1.setType(typeId);
//            inform1.setContent(text);
//            informService.add(inform1);
//        }else {
//            inform.setNum(inform.getNum()+1);
//            if(inform.getNum()>=3){
//                Cyinfor cyinfor = cyinforService.get(cyId);
//                cyinfor.setIsDelete(true);
//                cyinforService.update(cyinfor);
//            }
//            informService.update(inform);
//        }
//        return MapHelper.success();
//    }

}
