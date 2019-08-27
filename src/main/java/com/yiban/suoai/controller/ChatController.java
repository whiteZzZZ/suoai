package com.yiban.suoai.controller;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeChatList;
import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.pojo.ChatList;
import com.yiban.suoai.service.ChatService;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("聊天相关")
@RestController
@RequestMapping("chat")
public class ChatController {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ChatService chatService;

    @ApiOperation(value = "获取聊天列表", notes = "获取聊天列表")
    @RequestMapping(value="getChatList" ,method = RequestMethod.GET)
    public Map<String,Object> getChatList(@RequestHeader("token") @ApiParam(value = "权限校验")String token){
        Map map = MapHelper.success();
        try {
            int userId = redisUtil.getUserId(token);
            List<ChatList> list = chatService.getChatList(userId);
            List<ForeChatList> cl = chatService.getFore(list);
            map.put("list",cl);
            return map;
        } catch (SAException e) {
            e.printStackTrace();
            return MapHelper.error();
        }
    }

    @ApiOperation(value = "获取聊天记录", notes = "获取聊天记录")
    @RequestMapping(value="getChatRecord",method = RequestMethod.GET)
    public Map<String,Object> getChatRecord(@RequestParam("cuId")@ApiParam(value = "聊天室id")int cuId){
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

    @ApiOperation(value = "获取（添加）聊天室id", notes = "获取（添加）聊天室id")
    @RequestMapping(value="getAddChatRoom",method = RequestMethod.GET)
    public Map<String, Object> getAddChatRoom(@RequestParam("userId")@ApiParam(value = "对面用户id")int userId,@RequestHeader("token")@ApiParam(value = "权限校验") String token){
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

    @ApiOperation(value = "删除聊天记录")
    @DeleteMapping("deleteRecord")
    public Map<String,Object> deleteRecord(@RequestParam("cuId")@ApiParam("房间号") int cuId,@RequestHeader("token")@ApiParam(value = "权限校验")String token) throws SAException {
        int userId = redisUtil.getUserId(token);
        chatService.deleteRecord(cuId);
        return MapHelper.success();
    }

    @ApiOperation("删除聊天列表")
    @DeleteMapping("deleteChatList")
    public Map<String,Object> deleteChatList(@RequestParam("id")@ApiParam("房间id") int id,@RequestHeader("token")@ApiParam(value = "权限校验")String token) throws SAException {
        int userId = redisUtil.getUserId(token);
        chatService.deleteChatList(id);
        return MapHelper.success();
    }
}