package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.LetterMessage;
import com.yiban.suoai.pojo.LetterMessageExample;
import java.util.List;

public interface LetterMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LetterMessage record);

    int insertSelective(LetterMessage record);

    List<LetterMessage> selectByExample(LetterMessageExample example);

    LetterMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LetterMessage record);

    int updateByPrimaryKey(LetterMessage record);
}