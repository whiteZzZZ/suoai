package com.yiban.suoai.service;

import org.springframework.stereotype.Service;


public interface RedisService {

    /** 添加token到redis中
     *
     *  @param userId
     *  @param token
     */
    void addTokenToRedis(int userId, String token);
}
