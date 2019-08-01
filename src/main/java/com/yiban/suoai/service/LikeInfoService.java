package com.yiban.suoai.service;

import com.yiban.suoai.pojo.LikeInfo;
import org.omg.CORBA.INTERNAL;

import java.util.List;

public interface LikeInfoService {

    void add(LikeInfo likeInfo);

    LikeInfo get(int id);

    void delete(int id);

    LikeInfo getByCyidAndUserIdAndType(int cyid,int userId,int type);


    /**
     * 匹配  找到和自己点赞记录相同的用户
     * @param cyid
     * @param type
     * @return
     */
    List<Integer> getByCyidAndType(int cyid,int type,int userId);


    /**
     * 拿到自己的点赞记录
     * @param userId
     * @return
     */
    List<LikeInfo>  getByUserId(int userId);

}
