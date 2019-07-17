package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Cyinfor;
import com.yiban.suoai.pojo.CyinforExample;
import java.util.List;

public interface CyinforMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cyinfor record);

    int insertSelective(Cyinfor record);

    List<Cyinfor> selectByExampleWithBLOBs(CyinforExample example);

    List<Cyinfor> selectByExample(CyinforExample example);

    Cyinfor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cyinfor record);

    int updateByPrimaryKeyWithBLOBs(Cyinfor record);

    int updateByPrimaryKey(Cyinfor record);
}