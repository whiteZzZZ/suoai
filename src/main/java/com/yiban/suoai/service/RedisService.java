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

    /**
     * 把通知发给对方
     * @param userId
     */
    void addImformToRedis(int userId,String type);


    /**
     * 对方获取通知
     * @param userId
     */
    int getImformFromRedis(int userId,String type);

    /**
     * 点开通知后 删掉提醒
     * @param userId
     */
    int deleteImformFromRedis(int userId,String type);

    /**
     * 加入匹配
     * @param userId
     * @param sex
     * @return
     */
    int addOrdinaryMatch(int userId);

    /**
     * 获取异性匹配或者匹配成功后删除自己
     * @param sex
     * @return
     */
    String getOrdinaryMatch();

    long deleteOrdinaryMatch(int userId);


    String blockGetOrdinaryMatch();

    /**
     * 通知被匹配的用户
     * @param userId
     * @param matchUserId
     * @return
     */
    int addOrdinaryMatchImfore(int userId,int matchUserId);

    /**
     * 女 查找自己有没有被匹配
     * @param userId
     * @return
     */
    int getOrdinaryMatchImfore(int userId);

}
