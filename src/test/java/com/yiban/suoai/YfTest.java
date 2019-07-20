package com.yiban.suoai;

import com.alibaba.fastjson.JSONObject;
import com.yiban.suoai.pojo.Chat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.yiban.suoai")
public class YfTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("test1","112");

        System.out.println(stringRedisTemplate.opsForValue().get("test1"));
    }

    @Test
    public void testJSON(){
        String str = "{'id':1,'content':'hello','userId':33}";
        Chat chat = JSONObject.parseObject(str,Chat.class);
        System.out.println(chat.getContent());

    }

    public static void main(String[] args) {
        String str = "{'id':1,'content':'hello','userId':33}";
        Chat chat = JSONObject.parseObject(str,Chat.class);
        System.out.println(chat.getContent());
        String str2 = JSONObject.toJSONString(chat);
        System.out.println(str2);
    }
}
