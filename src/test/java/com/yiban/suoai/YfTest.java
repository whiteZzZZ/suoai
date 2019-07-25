package com.yiban.suoai;

import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.pojo.Cyinfor;
import com.yiban.suoai.service.CyinforService;
import com.yiban.suoai.service.impl.RedisServiceImpl;
import com.yiban.suoai.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
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
    @Autowired
    CyinforService cyinforService;
    public final static String forePath="D/image";//todo  记得修改  路径
    public final static String cyinfor="/cyinfor";//cyinfor路径


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
    public void testRedisUtil() throws SAException {
        List<Cyinfor> cyinfors=cyinforService.topTen();
        redisUtil.setObject(RedisServiceImpl.Wall,cyinfors);
    }


    @Test
    public void fileSave2() {
        String subpath="/cyinfor";
        File file1=new File(forePath+subpath);
        if(!file1.exists()){
            file1.mkdirs();
        }
        String uuid="123213";
        String path=forePath+subpath+"/"+uuid+"-y.jpg";

        try {
            File file = new File(path);
            OutputStream out = new FileOutputStream(file);
            byte[] data = Base64.getDecoder().decode("http://tmp/wxa89746c53ec389f1.o6zAJszUrvR4HctQ2Ydo….6mqseHw4kuqzd37a60563d42854d53f0b991ede4963c.jpg");
            out.write(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
