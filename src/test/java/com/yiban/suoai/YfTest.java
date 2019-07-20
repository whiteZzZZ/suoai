package com.yiban.suoai;

import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
        System.out.println(redisUtil.get("d4d605fa7bde590c31ae7f005b3d2b513163f4e3"));
    }

    @Test
    public void testJSON(){
        String str = "{'id':1,'content':'hello','userId':33}";
        Chat chat = JSONObject.parseObject(str,Chat.class);
        System.out.println(chat.getContent());
    }

    @Test
    public void testRedisUtil(){
        System.out.println(redisUtil.hasKey("fdsf"));
    }


}
