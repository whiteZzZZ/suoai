package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Title;
import com.yiban.suoai.pojo.TitleExample;
import java.util.List;

public interface TitleMapper {
    int deleteByExample(TitleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Title record);

    int insertSelective(Title record);

    List<Title> selectByExample(TitleExample example);

    Title selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);
}