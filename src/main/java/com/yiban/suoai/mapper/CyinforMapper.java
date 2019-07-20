package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Cyinfor;
import com.yiban.suoai.pojo.CyinforExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CyinforMapper {
    int countByExample(CyinforExample example);

    int deleteByExample(CyinforExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cyinfor record);

    int insertSelective(Cyinfor record);

    List<Cyinfor> selectByExampleWithBLOBs(CyinforExample example);

    List<Cyinfor> selectByExample(CyinforExample example);

    Cyinfor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cyinfor record, @Param("example") CyinforExample example);

    int updateByExampleWithBLOBs(@Param("record") Cyinfor record, @Param("example") CyinforExample example);

    int updateByExample(@Param("record") Cyinfor record, @Param("example") CyinforExample example);

    int updateByPrimaryKeySelective(Cyinfor record);

    int updateByPrimaryKeyWithBLOBs(Cyinfor record);

    int updateByPrimaryKey(Cyinfor record);
}