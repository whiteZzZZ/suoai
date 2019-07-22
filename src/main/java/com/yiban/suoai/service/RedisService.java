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

  /*  *//**
     * 把点赞的通知发给对方
     * @param userId
     *//*
    void addLikeToRedis(int userId);


    *//**
     * 对方获取点赞的通知
     * @param userId
     *//*
    int getLikeFromRedis(int userId);

    *//**
     * 点开通知后 删掉提醒
     * @param userId
     *//*
    int deleteLikeFromRedis(int userId);


    *//**
     * 把评论的通知发给对方
     * @param userId
     *//*
    void addReviewToRedis(int userId);


    *//**
     * 对方获取评论的通知
     * @param userId
     *//*
    int getReviewFromRedis(int userId);

    *//**
     * 点开通知后 删掉提醒
     * @param userId
     *//*
    int deleteReviewFromRedis(int userId);


    *//**
     * 把匹配的通知发给对方
     * @param userId
     *//*
    void addMatchingToRedis(int userId);


    *//**
     * 对方获取匹配的通知
     * @param userId
     *//*
    int getMatchingFromRedis(int userId);

    *//**
     * 点开通知后 删掉提醒
     * @param userId
     *//*
    int deleteMatchingFromRedis(int userId);



    *//**
     * 把通知发给对方
     * @param userId
     *//*
    void addImformToRedis(int userId);


    *//**
     * 对方获取通知
     * @param userId
     *//*
    int getImformFromRedis(int userId);


    *//**
     * 点开通知后 删掉提醒
     * @param userId
     *//*
    int deleteImformFromRedis(int userId);
*/

}
