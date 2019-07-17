package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Academy;
import com.yiban.suoai.pojo.AcademyExample;
import java.util.List;

public interface AcademyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Academy record);

    int insertSelective(Academy record);

    List<Academy> selectByExample(AcademyExample example);

    Academy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Academy record);

    int updateByPrimaryKey(Academy record);
}