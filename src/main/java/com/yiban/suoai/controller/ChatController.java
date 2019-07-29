package com.yiban.suoai.controller;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.pojo.ChatList;
import com.yiban.suoai.service.ChatService;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("chat")
public class ChatController {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ChatService chatService;

    @RequestMapping("getChatList")
    public Map<String,Object> getChatList(@RequestHeader("token") String token){
        Map map = MapHelper.success();
        try {
            int userId = redisUtil.getUserId(token);
            List<ChatList> list = chatService.getChatList(userId);
            map.put("list",list);
            return map;
        } catch (SAException e) {
            e.printStackTrace();
            return MapHelper.error();
        }
    }

    @RequestMapping("getChatRecord")
    public Map<String,Object> getChatRecord(@RequestParam("cuId")int cuId){
        Map map = MapHelper.success();
        List<Chat> list = redisUtil.getObjectList("room:"+cuId);
        if(list==null){
            list = chatService.getChatRecord(cuId);
        }else{
            List<Chat> tmp = redisUtil.getObjList("room:update:"+cuId);
            list.addAll(tmp);
        }
        map.put("list",list);
        return map;
    }

    @RequestMapping("getAddChatRoom")
    public Map<String, Object> getAddChatRoom(@RequestParam("userId")int userId,@RequestHeader("token") String token){
        Map map = MapHelper.success();
        try {
            int myId = redisUtil.getUserId(token);
            int cuId = chatService.getAddChatRoom(myId,userId);
            map.put("cuId",cuId);
        } catch (SAException e) {
            e.printStackTrace();
        }
        return map;
    }
}