package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.AcademyMapper;
import com.yiban.suoai.pojo.Academy;
import com.yiban.suoai.pojo.AcademyExample;
import com.yiban.suoai.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademyServiceImpl implements AcademyService {
    @Autowired
    AcademyMapper academyMapper;

    @Override
    public List<Academy> selectBySchool(int schoolId) {
        AcademyExample academyExample = new AcademyExample();
        academyExample.createCriteria().andSchoolIdEqualTo(schoolId);
        return academyMapper.selectByExample(academyExample);
    }

    @Override
    public void add(Academy academy) {
        academyMapper.insert(academy);
    }

    @Override
    public void update(Academy academy) {
        academyMapper.updateByPrimaryKey(academy);
    }

    @Override
    public Academy get(int id) {
        return academyMapper.selectByPrimaryKey(id);
    }
}
