package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Title;
import com.yiban.suoai.pojo.TitleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TitleMapper {
    int countByExample(TitleExample example);

    int deleteByExample(TitleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Title record);

    int insertSelective(Title record);

    List<Title> selectByExample(TitleExample example);

    Title selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Title record, @Param("example") TitleExample example);

    int updateByExample(@Param("record") Title record, @Param("example") TitleExample example);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);
}