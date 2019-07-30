package com.yiban.suoai;

import com.alibaba.fastjson.JSONObject;
import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.scheduler.ChatScheduler;
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
import java.util.Date;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    @Autowired
    ChatScheduler chatScheduler;

    @Test
    public void testRedis()throws Exception{
        //stringRedisTemplate.opsForValue().set("test1","112");
        redisUtil.setObject("keyyy",new Chat(),4);
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

    @Test
    public void addRoom(){
        List<Chat> chats = new ArrayList<>();
        for(int i = 0;i<10;i++){
            Chat chat = new Chat();
            chat.setContent("hello"+i);
            chat.setType(1);
            chat.setTime(new Date());
            chat.setUserId(1);
            chats.add(chat);
        }
        for(int i =0;i<10;i++){
            redisUtil.setObject("room:"+i,chats,7200);
        }
    }

    @Test
    public void testPattern(){
        Set<String> set =  redisUtil.objectKeys("room:*");
        set.forEach(System.out::println);
    }

    @Test
    public void testIncr(){

       redisUtil.lpushObject("qwer",5);
        List<Integer> list = redisUtil.getList("qwer");
       list.forEach(System.out::println);
    }

    @Test
    public void testSchedule(){
        chatScheduler.updateDataBase();
    }
}
