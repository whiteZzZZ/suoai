package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.DailySentence;
import com.yiban.suoai.pojo.DailySentenceExample;
import java.util.List;

public interface DailySentenceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DailySentence record);

    int insertSelective(DailySentence record);

    List<DailySentence> selectByExample(DailySentenceExample example);

    DailySentence selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DailySentence record);

    int updateByPrimaryKey(DailySentence record);
}