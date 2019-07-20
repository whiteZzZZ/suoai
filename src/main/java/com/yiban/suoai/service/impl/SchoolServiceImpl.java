package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.SchoolMapper;
import com.yiban.suoai.pojo.School;
import com.yiban.suoai.pojo.SchoolExample;
import com.yiban.suoai.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public void add(School school) {
        schoolMapper.insert(school);
    }

    @Override
    public void update(School school) {
        schoolMapper.updateByPrimaryKey(school);
    }

    @Override
    public School get(int id) {
        return schoolMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<School> selectByName(String schoolName) {
        SchoolExample schoolExample = new SchoolExample();
        schoolExample.createCriteria().andNameEqualTo(schoolName);
        return schoolMapper.selectByExample(schoolExample);
    }
}
