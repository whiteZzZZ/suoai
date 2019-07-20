package com.yiban.suoai.controller;


import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/chat/{room}/{Token}")
public class WebSocketChatServer {
    @Autowired
    RedisService redisService;
    //所有会话的保存
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    static private final int heartBeatType = 0;
    static private final int chatType = 1;
    static private final int msgType = 2;


    //打开连接
    @OnOpen
    public void onOpen(@PathParam("room")int roomId,@PathParam("Token")String token,Session session){
            int userId = 0;
        try {
                userId = redisService.getUserId(token);
            } catch (SAException e) {
                e.printStackTrace();
                Chat chat = new Chat();
                chat.setType(msgType);
                chat.setContent("token过期");
                session.getAsyncRemote().sendText(chat.toString());
            }//暂时抛出，可以考虑全局代理

    }

    public void checkHeartBeat(){

    }
}
