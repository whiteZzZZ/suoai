package com.yiban.suoai.service;

import com.yiban.suoai.pojo.LikeInfo;

public interface LikeInfoService {

    void add(LikeInfo likeInfo);

    LikeInfo get(int id);

    void delete(int id);

    LikeInfo getByCyidAndUserIdAndType(int cyid,int userId,int type);

}
