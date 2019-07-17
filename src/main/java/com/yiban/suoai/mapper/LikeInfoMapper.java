package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.LikeInfo;
import com.yiban.suoai.pojo.LikeInfoExample;
import java.util.List;

public interface LikeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeInfo record);

    int insertSelective(LikeInfo record);

    List<LikeInfo> selectByExample(LikeInfoExample example);

    LikeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeInfo record);

    int updateByPrimaryKey(LikeInfo record);
}