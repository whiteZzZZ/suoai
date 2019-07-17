package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Letter;
import com.yiban.suoai.pojo.LetterExample;
import java.util.List;

public interface LetterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Letter record);

    int insertSelective(Letter record);

    List<Letter> selectByExampleWithBLOBs(LetterExample example);

    List<Letter> selectByExample(LetterExample example);

    Letter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Letter record);

    int updateByPrimaryKeyWithBLOBs(Letter record);

    int updateByPrimaryKey(Letter record);
}