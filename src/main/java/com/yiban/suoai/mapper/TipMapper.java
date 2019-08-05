package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Tip;
import com.yiban.suoai.pojo.TipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TipMapper {
    int countByExample(TipExample example);

    int deleteByExample(TipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tip record);

    int insertSelective(Tip record);

    List<Tip> selectByExample(TipExample example);

    Tip selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tip record, @Param("example") TipExample example);

    int updateByExample(@Param("record") Tip record, @Param("example") TipExample example);

    int updateByPrimaryKeySelective(Tip record);

    int updateByPrimaryKey(Tip record);
}