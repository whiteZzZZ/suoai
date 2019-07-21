package com.yiban.suoai.service.impl;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.service.RedisService;

import com.yiban.suoai.util.RedisUtil;
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

    private static final String WallCode="Wall";//表白墙前缀

    private static final String Token="token:";//表白墙前缀



    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void addTokenToRedis(int userId, String token) {

        redisUtil.set(Token+token, String.valueOf(userId),saveState);

       /* Jedis redis =Pool.getResource();

        redis.set(token,String.valueOf(userId));

        redis.expire(token,saveState);//有效期为15天

        redis.close();*/
    }

    @Override
    public int getUserId(String token) throws SAException {

        if(!redisUtil.hasKey(token)){
            throw new SAException("token过期","003");
        }

        String s=redisUtil.get(Token+token);

        int userId=Integer.parseInt(s);

        redisUtil.expire(token,saveState);

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
