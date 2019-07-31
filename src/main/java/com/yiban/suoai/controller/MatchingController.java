package com.yiban.suoai.controller;


import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.UserService;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("matching")
@Api("匹配")
public class MatchingController {

    private static Logger logger = Logger.getLogger(MatchingController.class);// 添加日志


    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;


  /*  *//**
     * 思路： 先从redis中 获取有没有异性  如果没有再把自己加入匹配队列
     * 男女都各加锁  男女队列里最多一人  考虑阻塞超过多少秒自动退出
     * @param token
     * @return
     * @throws SAException
     * @throws InterruptedException
     *//*
    @ApiOperation(value = "普通匹配", notes = "普通匹配")
    @RequestMapping(value ="ordinaryMatch" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> ordinaryMatch(@RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException, InterruptedException {


        Map<String, Object> map=null;
         int blockTime=60000;//等待六十秒
          int userId=redisService.getUserId(token);
          int matchUserId=0;//获得匹配成功的userId
          User user=userService.get(userId);
          redisService.addOrdinaryMatch(userId,user.getSex()?1:0);
          int oppositeSex=user.getSex()?0:1;
          //String result1=redisService.getOrdinaryMatch(oppositeSex);
          String result2=null;


          if(oppositeSex==1){
              //最多等待60秒
             for(int i=0;i<30;i++){
                 Thread.sleep(1000);
                 result2=redisService.getOrdinaryMatch(oppositeSex);//获得后就会直接删除
                 if(null!=result2){
                     break;
                 }
             }
              *//*Thread.sleep(blockTime);
               result2=redisService.getOrdinaryMatch(oppositeSex);//获得后就会直接删除*//*
              if(null==result2){
                  return MapHelper.error();
              }
        }else {

          }
        //  matchUserId= null==result1?Integer.parseInt(result2):Integer.parseInt(result1);//如果不为null那么就匹配到了
          matchUserId= Integer.parseInt(result2);//如果不为null那么就匹配到了
        //那么这两个人就出队列  匹配成功
        if(0 != matchUserId){
            //匹配到 就要把自己删除
            redisService.deleteOrdinaryMatch(userId,user.getSex()?1:0);
            //把两个用户信息给前端
            User matchUser = userService.get(matchUserId);
            map=MapHelper.success();
            map.put("matchUserId",matchUserId);
            map.put("matchUserHeadImg",matchUser.getHeadImg());
            map.put("matchUserName",matchUser.getName());
            return map;
        }
        return MapHelper.error();




    }*/



    /**
     * 思路： 生产者 消费者 模型 女为生产者入队  男为消费者 取
     * 女方消息 从redis中获取循环查找60秒    男 要把消息 加入到redis
     * @param token
     * @return
     * @throws SAException
     * @throws InterruptedException
     */
    @ApiOperation(value = "普通匹配", notes = "普通匹配")
    @RequestMapping(value ="ordinaryMatch" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> ordinaryMatch(@RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException, InterruptedException {


        Map<String, Object> map = null;
        int userId = redisService.getUserId(token);
        int matchUserId = 0;//获得匹配成功的userId
        User user = userService.get(userId);
        int mysex = user.getSex() ? 1 : 0;//我的性别
        int matchUserId2 = 0;//女查找到男的 userId
        if (mysex == 0) {
            //如果是女的就入队
            redisService.addOrdinaryMatch(userId);
            for (int i = 0; i < 30; i++) {
                //搜索 自己有没有被匹配到
                matchUserId2 = redisService.getOrdinaryMatchImfore(userId);
                if (matchUserId2 != 0) {
                    break;
                } else {
                    Thread.sleep(2000);
                }
            }
            if (matchUserId2 != 0) {
                //女的匹配成功  返回男方信息
                map = MapHelper.success();
                User matchUser = userService.get(matchUserId2);
                map.put("matchUserId", matchUserId2);
                map.put("matchUserHeadImg", matchUser.getHeadImg());
                map.put("matchUserName", matchUser.getName());
                return map;
            } else {
                //没匹配到还有把自己从列表中删除
                redisService.deleteOrdinaryMatch(userId);
                return MapHelper.error();
            }


        } else {
            //男的就阻塞  查  阻塞60秒
            String match=redisService.blockGetOrdinaryMatch();
            if(null!=match){
                matchUserId = Integer.parseInt(redisService.blockGetOrdinaryMatch());
            }
            if (matchUserId != 0) {
                //匹配成功
                //通知被匹配的用户
                redisService.addOrdinaryMatchImfore(userId, matchUserId);
                User matchUser = userService.get(matchUserId);
                map.put("matchUserId", matchUserId);
                map.put("matchUserHeadImg", matchUser.getHeadImg());
                map.put("matchUserName", matchUser.getName());
                return map;
            } else {
                return MapHelper.error();
            }


        }


    }


}
