package com.yiban.suoai.service.impl;

import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.util.RedisAPI;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisServiceImpl implements RedisService {

    private static Logger logger = Logger.getLogger(RedisServiceImpl.class);// 添加日志


    private final JedisPool Pool = RedisAPI.getPool();//

    private static final String PhoneNumberCode="phoneNum";//手机号码前缀

    @Override
    public void addTokenToRedis(int userId, String token) {

        Jedis redis =Pool.getResource();

        redis.set(token,String.valueOf(userId));

        redis.expire(token,1296000);//有效期为15天

        redis.close();
    }
}
