package com.yiban.suoai;

import com.alibaba.fastjson.JSONObject;
import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.yiban.suoai")
public class YfTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate<String,Object> rr;
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testRedis(){
        //stringRedisTemplate.opsForValue().set("test1","112");
        Chat chat = new Chat();
        chat.setId(11);
        chat.setCuId(12);
        chat.setType(3);
        chat.setContent("hello world");
        redisTemplate.opsForValue().set("ff:fff4",chat);
        Chat cc =(Chat)redisTemplate.opsForValue().get("ff:fff4");
        System.out.println(cc.getContent());
    }

    @Test
    public void testJSON(){
        String str = "{'id':1,'content':'hello','userId':33}";
        Chat chat = JSONObject.parseObject(str,Chat.class);
        System.out.println(chat.getContent());
    }

    @Test
    public void testRedisUtil(){
        Chat chat;
        List<Chat> list = new ArrayList<>();
        for(int i = 0;i<10;i++){
            chat=new Chat();
            chat.setContent("con"+i);
            list.add(chat);
        }

    }


}
