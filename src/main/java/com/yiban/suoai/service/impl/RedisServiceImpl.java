package com.yiban.suoai.service.impl;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.util.RedisAPI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    private static Logger logger = Logger.getLogger(RedisServiceImpl.class);// 添加日志

    private  static  final int  saveState=1296000;//token保存的15天

   // private final JedisPool Pool = RedisAPI.getPool();//

    private static final String PhoneNumberCode="phoneNum";//手机号码前缀

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public void addTokenToRedis(int userId, String token) {

        redisTemplate.opsForValue().set(token,userId,saveState);

       /* Jedis redis =Pool.getResource();

        redis.set(token,String.valueOf(userId));

        redis.expire(token,saveState);//有效期为15天

        redis.close();*/
    }

    @Override
    public int getUserId(String token) throws SAException {

        int userId=(int)redisTemplate.opsForValue().get(token);

        redisTemplate.expire(token, saveState, TimeUnit.MILLISECONDS  );

       return userId;

        /*Jedis redis =Pool.getResource();

        int userId;

        if(redis.exists(token)){
            redis.expire(token,saveState);
            userId=Integer.parseInt(redis.get(token));
            redis.close();
            return  userId;
        }else {
            redis.close();
            throw new SAException("token过期","003");
        }
*/
    }
}
