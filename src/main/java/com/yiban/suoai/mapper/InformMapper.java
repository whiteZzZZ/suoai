package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Inform;
import com.yiban.suoai.pojo.InformExample;
import java.util.List;

public interface InformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Inform record);

    int insertSelective(Inform record);

    List<Inform> selectByExample(InformExample example);

    Inform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Inform record);

    int updateByPrimaryKey(Inform record);
}