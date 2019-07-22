package com.yiban.suoai.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;


@Component
public class RedisMessageListener implements MessageListener {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern){
        System.out.println(message);
        System.out.println(pattern);
        System.out.println("onPMessage pattern " + pattern + " " + " " + message);
        String channel = new String(message.getChannel());
        System.out.println(message.getBody());
    }
}
