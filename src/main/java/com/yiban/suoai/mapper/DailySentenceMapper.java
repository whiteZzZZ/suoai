package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.DailySentence;
import com.yiban.suoai.pojo.DailySentenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailySentenceMapper {
    int countByExample(DailySentenceExample example);

    int deleteByExample(DailySentenceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DailySentence record);

    int insertSelective(DailySentence record);

    List<DailySentence> selectByExample(DailySentenceExample example);

    DailySentence selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DailySentence record, @Param("example") DailySentenceExample example);

    int updateByExample(@Param("record") DailySentence record, @Param("example") DailySentenceExample example);

    int updateByPrimaryKeySelective(DailySentence record);

    int updateByPrimaryKey(DailySentence record);
}