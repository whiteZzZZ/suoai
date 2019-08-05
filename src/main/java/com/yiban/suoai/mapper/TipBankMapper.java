package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.TipBank;
import com.yiban.suoai.pojo.TipBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TipBankMapper {
    int countByExample(TipBankExample example);

    int deleteByExample(TipBankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TipBank record);

    int insertSelective(TipBank record);

    List<TipBank> selectByExample(TipBankExample example);

    TipBank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TipBank record, @Param("example") TipBankExample example);

    int updateByExample(@Param("record") TipBank record, @Param("example") TipBankExample example);

    int updateByPrimaryKeySelective(TipBank record);

    int updateByPrimaryKey(TipBank record);
}