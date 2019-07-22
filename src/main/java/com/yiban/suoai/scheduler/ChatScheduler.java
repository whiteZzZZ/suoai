package com.yiban.suoai.scheduler;

import com.yiban.suoai.mapper.ChatMapper;
import com.yiban.suoai.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChatScheduler {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ChatMapper chatMapper;

    @Scheduled(cron = "0 0 0/1 * * ? ")//每小时1次
    public void updateDataBase(){
        redisUtil.objectKeys("room:*");

    }
}
