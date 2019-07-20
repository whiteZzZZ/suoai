package com.yiban.suoai.controller;


import com.alibaba.fastjson.JSONObject;
import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/chat/{room}/{Token}")
public class WebSocketChatServer {
    @Autowired
    RedisService redisService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    //所有会话的保存
    private static Map<Integer, Session> onlineSessions = new ConcurrentHashMap<>();

    static private final int heartBeatType = 0;
    static private final int chatType = 1;
    static private final int msgType = 2;


    //打开连接
    @OnOpen
    public void onOpen(@PathParam("room")int roomId,@PathParam("Token")String token,Session session){
        int userId = 0;
        try {
            userId = redisService.getUserId(token);
            onlineSessions.put(userId,session);
            } catch (SAException e) {
                e.printStackTrace();
                Chat chat = new Chat();
                chat.setType(msgType);
                chat.setContent("token过期");
                JSONObject result = new JSONObject();
                result.put("chat",chat);
                session.getAsyncRemote().sendText(chat.toString());
            try {
                session.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }//暂时抛出，可以考虑全局代理

    }

    @OnMessage
    public void onMessage(Session session,String json){
        Chat chat= JSONObject.parseObject(json,Chat.class);
        if(chat.getType() == chatType){
            sendMessage(session,json,chat);
        }else if(chat.getType() == heartBeatType){
            checkHeartBeat(session,json);
        }else if(chat.getType() == msgType){

        }

    }
    private void checkHeartBeat(Session session,String json){

    }

    private void sendMessage(Session session,String json,Chat chat){
        Session rec = onlineSessions.get(chat.getUserId());
        rec.getAsyncRemote().sendText(JSONObject.toJSONString(chat));
    }

    private void checkMsg(Session session,String json){

    }


}
