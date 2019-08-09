package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.TipUser;
import com.yiban.suoai.pojo.TipUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TipUserMapper {
    int countByExample(TipUserExample example);

    int deleteByExample(TipUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TipUser record);

    int insertSelective(TipUser record);

    List<TipUser> selectByExample(TipUserExample example);

    TipUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TipUser record, @Param("example") TipUserExample example);

    int updateByExample(@Param("record") TipUser record, @Param("example") TipUserExample example);

    int updateByPrimaryKeySelective(TipUser record);

    int updateByPrimaryKey(TipUser record);
}