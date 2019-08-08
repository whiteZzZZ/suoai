package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.TitleMapper;
import com.yiban.suoai.pojo.Title;
import com.yiban.suoai.pojo.TitleExample;
import com.yiban.suoai.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleServiceImpl implements TitleService {
    @Autowired
    TitleMapper titleMapper;
    @Override
    public void add(Title user) {
        titleMapper.insert(user);
    }

    @Override
    public void update(Title user) {
        titleMapper.updateByPrimaryKey(user);
    }

    @Override
    public Title get(int id) {
        return titleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Title> getByUserId(int userId) {
        TitleExample titleExample = new TitleExample();
        titleExample.createCriteria().andUseridEqualTo(userId);
        return titleMapper.selectByExample(titleExample);
    }
}
