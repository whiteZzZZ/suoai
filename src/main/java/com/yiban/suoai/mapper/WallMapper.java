package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Wall;
import com.yiban.suoai.pojo.WallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WallMapper {
    int countByExample(WallExample example);

    int deleteByExample(WallExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wall record);

    int insertSelective(Wall record);

    List<Wall> selectByExample(WallExample example);

    Wall selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wall record, @Param("example") WallExample example);

    int updateByExample(@Param("record") Wall record, @Param("example") WallExample example);

    int updateByPrimaryKeySelective(Wall record);

    int updateByPrimaryKey(Wall record);
}