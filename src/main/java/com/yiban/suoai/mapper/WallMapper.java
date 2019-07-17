package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Wall;
import com.yiban.suoai.pojo.WallExample;
import java.util.List;

public interface WallMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wall record);

    int insertSelective(Wall record);

    List<Wall> selectByExample(WallExample example);

    Wall selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wall record);

    int updateByPrimaryKey(Wall record);
}