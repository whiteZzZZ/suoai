package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.DailySentenceMapper;
import com.yiban.suoai.pojo.DailySentence;
import com.yiban.suoai.service.DailySentenceService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

public class DailySentenceServiceImpl implements DailySentenceService {
    @Autowired
    DailySentenceMapper dailySentenceMapper;

    @Override
    public DailySentence get(int id) {
        return dailySentenceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(DailySentence cyinfor) {
        return dailySentenceMapper.insert(cyinfor);
    }

    @Override
    public void delete(int id) {
        dailySentenceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(DailySentence cyinfor) {
        dailySentenceMapper.updateByPrimaryKey(cyinfor) ;
    }
}
