package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.RankList;
import com.yiban.suoai.pojo.RankListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RankListMapper {
    int countByExample(RankListExample example);

    int deleteByExample(RankListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RankList record);

    int insertSelective(RankList record);

    List<RankList> selectByExample(RankListExample example);

    RankList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RankList record, @Param("example") RankListExample example);

    int updateByExample(@Param("record") RankList record, @Param("example") RankListExample example);

    int updateByPrimaryKeySelective(RankList record);

    int updateByPrimaryKey(RankList record);
}