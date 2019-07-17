package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.WeekWord;
import com.yiban.suoai.pojo.WeekWordExample;
import java.util.List;

public interface WeekWordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeekWord record);

    int insertSelective(WeekWord record);

    List<WeekWord> selectByExample(WeekWordExample example);

    WeekWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeekWord record);

    int updateByPrimaryKey(WeekWord record);
}