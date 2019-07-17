package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.RankList;
import com.yiban.suoai.pojo.RankListExample;
import java.util.List;

public interface RankListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RankList record);

    int insertSelective(RankList record);

    List<RankList> selectByExample(RankListExample example);

    RankList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RankList record);

    int updateByPrimaryKey(RankList record);
}