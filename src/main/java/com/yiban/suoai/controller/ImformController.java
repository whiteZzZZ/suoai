package com.yiban.suoai.controller;


import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeImform;
import com.yiban.suoai.pojo.Message;
import com.yiban.suoai.pojo.Notice;
import com.yiban.suoai.service.InformService;
import com.yiban.suoai.service.MessageService;
import com.yiban.suoai.service.NoticeService;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.impl.RedisServiceImpl;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("imform")
@Api("通知")
public class ImformController {

    private static Logger logger = Logger.getLogger(ImformController.class);// 添加日志


    @Autowired
    RedisService redisService;
    @Autowired
    MessageService messageService;
    @Autowired
    NoticeService noticeService;


    @ApiOperation(value = "获取消息数量", notes = "获取消息数量  需要前端 10秒一次轮询")
    @RequestMapping(value ="getMessageCount" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMessageCount(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException {
         int TotalMessage=0;
         int userId=redisService.getUserId(token);
         int expressionMessage=redisService.getImformFromRedis(userId, RedisServiceImpl.Expression);
         int likeMessage=redisService.getImformFromRedis(userId, RedisServiceImpl.Like);
         int commentMessage=redisService.getImformFromRedis(userId, RedisServiceImpl.Comment);
         //匹配的消息还没 弄
         //int matchingMessage=redisService.getImformFromRedis(userId, RedisServiceImpl.Matching);

         int imform2Message=redisService.getImformFromRedis(userId, RedisServiceImpl.Imform2);
         TotalMessage=likeMessage+expressionMessage+commentMessage+imform2Message;
         Map map=new HashMap();
         map.put("TotalMessage",TotalMessage);
         map.put("expressionMessage",expressionMessage);
         map.put("likeMessage",likeMessage);
         map.put("commentMessage",commentMessage);
        // map.put("matchingMessage",matchingMessage);
         map.put("imform2Message",imform2Message);
         return map;
     }

    @ApiOperation(value = "获取收到的表白", notes = "获取收到的表白")
    @RequestMapping(value ="expression" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> expression(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token
            ) throws SAException {
        int userId=redisService.getUserId(token);
        //点进去后，把该消息 提醒删掉
        redisService.deleteImformFromRedis(userId,RedisServiceImpl.Expression);
        List<Message> list= messageService.getByUserAndType(userId,0);//公开表白
        List<Message> list1= messageService.getByUserAndType(userId,1);//私密表白
        list.addAll(list1);
        List<ForeImform> foreImforms=messageService.full(list);
        Map map=new HashMap();
        map.put("list",foreImforms);
        return map;
    }


    @ApiOperation(value = "获取收到的点赞通知", notes = "获取收到的点赞")
    @RequestMapping(value ="like" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> like(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token
    ) throws SAException {
        int userId=redisService.getUserId(token);
        //点进去后，把该消息 提醒删掉
        redisService.deleteImformFromRedis(userId,RedisServiceImpl.Like);
        List<Message> list= messageService.getByUserAndType(userId,2);//点赞
        List<ForeImform> foreImforms=messageService.full(list);
        Map map=new HashMap();
        map.put("list",foreImforms);
        return map;
    }




    @ApiOperation(value = "获取收到的评论通知", notes = "获取收到的评论")
    @RequestMapping(value ="review" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> review(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token
    ) throws SAException {
        int userId=redisService.getUserId(token);
        //点进去后，把该消息 提醒删掉
        redisService.deleteImformFromRedis(userId,RedisServiceImpl.Comment);
        List<Message> list= messageService.getByUserAndType(userId,3);//评论
        List<ForeImform> foreImforms=messageService.full(list);
        Map map=new HashMap();
        map.put("list",foreImforms);
        return map;
    }


    @ApiOperation(value = "获取收到的系统通知", notes = "获取收到的系统通知")
    @RequestMapping(value ="systematicNotification" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> systematicNotification(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token
    ) throws SAException {
        int userId=redisService.getUserId(token);
        //点进去后，把该消息 提醒删掉
        redisService.deleteImformFromRedis(userId,RedisServiceImpl.Imform2);
        List<Notice>  list= noticeService.getAllByUserId(userId);
        Map map=new HashMap();
        map.put("list",list);
        return map;
    }




}


