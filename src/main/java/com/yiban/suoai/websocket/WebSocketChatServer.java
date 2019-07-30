package com.yiban.suoai.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@ServerEndpoint("/chat/{Token}")
public class WebSocketChatServer {


    @Autowired
    RedisUtil redisUtil=MyApplicationContextAware.getApplicationContext().getBean(RedisUtil.class);
    //所有会话的保存
    public static Map<Integer, Session> onlineSessions = new ConcurrentHashMap<>();

    static private final int heartBeatType = 0;
    static private final int chatType = 1;
    static private final int msgType = 2;
    static private final long time = 7200;


    //打开连接
    @OnOpen
    public void onOpen(@PathParam("Token")String token,Session session){
        System.out.println("in open");
        System.out.println(token);
        int userId = 0;
        try {
            System.out.println(redisUtil);
            userId = redisUtil.getUserId(token);
            onlineSessions.put(userId,session);
            //加入心跳 3分钟
            redisUtil.set("heartbeat:"+userId,"heartbeat",180);
            System.out.println("建立连接："+userId);
           // session.getAsyncRemote().sendText("message:'建立连接'");
            } catch (SAException e) {
                e.printStackTrace();
                Chat chat = new Chat();
                chat.setType(msgType);
                chat.setContent("token过期");
                JSONObject result = new JSONObject();
                result.put("chat",chat);
                session.getAsyncRemote().sendText(JSONObject.toJSONString(chat));
            try {
                session.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }//暂时抛出，可以考虑全局代理

    }

    @OnMessage
    public void onMessage(Session session,String json){
        System.out.println("in message");
        Chat chat= JSONObject.parseObject(json,Chat.class);
        JSONObject test = new JSONObject();
        test.put("chat",chat);
        System.out.println("test="+test);
        if(chat.getType() == chatType){
            sendMessage(session,json,chat);
        }else if(chat.getType() == heartBeatType){
            checkHeartBeat(session,chat);
        }else if(chat.getType() == msgType){
            checkMsg(session,chat);
        }

    }

    @OnError
    public void onError(Session session, Throwable error){
        log.error(error.getMessage());
        error.printStackTrace();
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("连接关闭");
        Collection<Session> values = onlineSessions.values();
        values.remove(session);
    }

    private void checkHeartBeat(Session session,Chat chat){
        String token = chat.getToken();
        try {
            int userId = redisUtil.getUserId(token);
            redisUtil.expire("heartbeat:"+userId,180);
        }catch (SAException e){
            e.printStackTrace();
        }
    }

    private void sendMessage(Session session,String json,Chat chat){
        Session rec = onlineSessions.get(chat.getUserId());
        if(rec!= null) {
            System.out.println("找到对方"+rec.getId());
            rec.getAsyncRemote().sendText(JSONObject.toJSONString(chat));
        }
        redisUtil.rpushObject("room:update:"+chat.getCuId(),chat);
        System.out.println("json="+json);
    }

    private void checkMsg(Session session,Chat chat){

    }

    @Scheduled(cron = "0/10 * * * * ? ") //10秒一次
    private void sendHeartBeat(){
        Chat chat = new Chat();
        chat.setType(heartBeatType);
        for(Map.Entry<Integer,Session> entry: onlineSessions.entrySet()){
            Session session = entry.getValue();
            session.getAsyncRemote().sendText(JSONObject.toJSONString(chat));
        }
    }
}

