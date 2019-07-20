package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.BlockMatch;
import com.yiban.suoai.pojo.BlockMatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlockMatchMapper {
    int countByExample(BlockMatchExample example);

    int deleteByExample(BlockMatchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlockMatch record);

    int insertSelective(BlockMatch record);

    List<BlockMatch> selectByExample(BlockMatchExample example);

    BlockMatch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlockMatch record, @Param("example") BlockMatchExample example);

    int updateByExample(@Param("record") BlockMatch record, @Param("example") BlockMatchExample example);

    int updateByPrimaryKeySelective(BlockMatch record);

    int updateByPrimaryKey(BlockMatch record);
}