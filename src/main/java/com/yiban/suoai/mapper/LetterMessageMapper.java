package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.LetterMessage;
import com.yiban.suoai.pojo.LetterMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LetterMessageMapper {
    int countByExample(LetterMessageExample example);

    int deleteByExample(LetterMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LetterMessage record);

    int insertSelective(LetterMessage record);

    List<LetterMessage> selectByExample(LetterMessageExample example);

    LetterMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LetterMessage record, @Param("example") LetterMessageExample example);

    int updateByExample(@Param("record") LetterMessage record, @Param("example") LetterMessageExample example);

    int updateByPrimaryKeySelective(LetterMessage record);

    int updateByPrimaryKey(LetterMessage record);
}