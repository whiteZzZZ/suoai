package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.BlockMatch;
import com.yiban.suoai.pojo.BlockMatchExample;
import java.util.List;

public interface BlockMatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlockMatch record);

    int insertSelective(BlockMatch record);

    List<BlockMatch> selectByExample(BlockMatchExample example);

    BlockMatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlockMatch record);

    int updateByPrimaryKey(BlockMatch record);
}