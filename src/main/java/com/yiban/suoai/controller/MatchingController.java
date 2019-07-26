package com.yiban.suoai.controller;


import com.yiban.suoai.exception.SAException;
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


    /**
     * 思路： 先从redis中 获取有没有异性  如果没有再把自己加入匹配队列
     * @param token
     * @return
     * @throws SAException
     * @throws InterruptedException
     */
    @ApiOperation(value = "普通匹配", notes = "普通匹配")
    @RequestMapping(value ="ordinaryMatch" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> ordinaryMatch(@RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException, InterruptedException {

        Map<String, Object> map=new HashMap<>();
         int blockTime=60000;//等待六十秒
          int userId=redisService.getUserId(token);
          int matchUserId=0;
          User user=userService.get(userId);
          redisService.addOrdinaryMatch(userId,user.getSex()?1:0);
          int oppositeSex=user.getSex()?0:1;
          String result1=redisService.getOrdinaryMatch(oppositeSex);
          String result2=null;
          if(null==result1){
              Thread.sleep(blockTime);
               result2=redisService.getOrdinaryMatch(oppositeSex);
              if(null==result2){
                  return MapHelper.error();
              }
        }
          matchUserId= null==result1?Integer.parseInt(result2):Integer.parseInt(result1);//如果不为null那么就匹配到了
        //那么这两个人就出队列  匹配成功








            return null;

    }

}
