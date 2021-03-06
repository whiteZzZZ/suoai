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
     * 判断token是否存在
     * @param token
     * @return
     */
    Boolean tokenExists(String token);


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
     * 第二次点赞后   把之前那条提醒减掉
     * @param userId
     * @param type
     * @return
     */
    int deleteOneImformFromRedis(int userId,String type);


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
     * @param
     * @return
     */
    int addOrdinaryMatch(int userId);

    /**
     * 获取异性匹配或者匹配成功后删除自己
     * @param sex
     * @return
     */
    String getOrdinaryMatch(int sex);

    /**
     *
     * 设置每天时空邮局获取限制
     * @param userId
     */
    void setSpaceLetterLimit(int userId);

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
     * 判断是否已到达获取次数
     * @param userId
     * @return
     */
    boolean getSpaceLimit(int userId);

    /**
     * 每天重置获取次数
     */
    void resetSpaceLimit();
    /**
     * 女 查找自己有没有被匹配
     * @param userId
     * @return
     */
    int getOrdinaryMatchImfore(int userId);


    /**
     * 发送男生取消匹配通知
     * @param userId
     * @return
     */
    int deleteOrdinaryMatchImform(int userId);

    /**
     * 男生接受取消匹配通知 1为已经按了取消匹配
     * @param userId
     * @return
     */
    int getDeleteOrdinaryMatchImform(int userId);

    /**
     * 用户每天只能灵魂匹配一次  匹配后就加入redis
     * @param userId
     * @return
     */
    int setSendinvitationTime(int userId);

    /**
     * 看看用户是否匹配过  这里不能删除掉
     * @param userId
     * @return
     */
    int getSendinvitationTime(int userId);

    /**
     * 用定时器 删除所有用户灵魂匹配的次数 每天0点
     * @return
     */
    int deleteSendinvitationTime();
}
