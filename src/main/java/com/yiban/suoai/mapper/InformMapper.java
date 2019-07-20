package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Inform;
import com.yiban.suoai.pojo.InformExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InformMapper {
    int countByExample(InformExample example);

    int deleteByExample(InformExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Inform record);

    int insertSelective(Inform record);

    List<Inform> selectByExample(InformExample example);

    Inform selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Inform record, @Param("example") InformExample example);

    int updateByExample(@Param("record") Inform record, @Param("example") InformExample example);

    int updateByPrimaryKeySelective(Inform record);

    int updateByPrimaryKey(Inform record);
}