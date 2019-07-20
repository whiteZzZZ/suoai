package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.WeekWord;
import com.yiban.suoai.pojo.WeekWordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeekWordMapper {
    int countByExample(WeekWordExample example);

    int deleteByExample(WeekWordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeekWord record);

    int insertSelective(WeekWord record);

    List<WeekWord> selectByExample(WeekWordExample example);

    WeekWord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeekWord record, @Param("example") WeekWordExample example);

    int updateByExample(@Param("record") WeekWord record, @Param("example") WeekWordExample example);

    int updateByPrimaryKeySelective(WeekWord record);

    int updateByPrimaryKey(WeekWord record);
}