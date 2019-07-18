package com.yiban.suoai.service;

import com.yiban.suoai.exception.SAException;
import org.springframework.stereotype.Service;


public interface RedisService {

    /** 添加token到redis中
     *
     *  @param userId
     *  @param token
     */
    void addTokenToRedis(int userId, String token);

    /**
     * 获得userid
     * @param token
     * @return
     */
    int getUserId(String token) throws SAException;

}
