package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.LikeInfo;
import com.yiban.suoai.pojo.LikeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LikeInfoMapper {
    int countByExample(LikeInfoExample example);

    int deleteByExample(LikeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LikeInfo record);

    int insertSelective(LikeInfo record);

    List<LikeInfo> selectByExample(LikeInfoExample example);

    LikeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LikeInfo record, @Param("example") LikeInfoExample example);

    int updateByExample(@Param("record") LikeInfo record, @Param("example") LikeInfoExample example);

    int updateByPrimaryKeySelective(LikeInfo record);

    int updateByPrimaryKey(LikeInfo record);
}