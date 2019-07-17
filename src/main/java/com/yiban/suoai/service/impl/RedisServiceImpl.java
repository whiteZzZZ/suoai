package com.yiban.suoai.service.impl;

import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.util.RedisAPI;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPool;

public class RedisServiceImpl implements RedisService {

    private static Logger logger = Logger.getLogger(RedisServiceImpl.class);// 添加日志


    private final JedisPool Pool = RedisAPI.getPool();//

    private static final String PhoneNumberCode="phoneNum";//手机号码前缀

}
