package com.yiban.suoai.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisUtil {
    @Autowired
    static private StringRedisTemplate stringRedisTemplate;
    @Autowired
    static private RedisTemplate redisTemplate;
}
