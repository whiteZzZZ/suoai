package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.DailySentenceMapper;
import com.yiban.suoai.pojo.DailySentence;
import com.yiban.suoai.service.DailySentenceService;
import com.yiban.suoai.util.RedisUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DailySentenceServiceImpl implements DailySentenceService {


    @Autowired
    DailySentenceMapper dailySentenceMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public DailySentence getByDay() {
        return (DailySentence) redisUtil.getObject(RedisServiceImpl.dailySentence);
    }

    @Override
    public void updateByDay() {
        DailySentence dailySentence= (DailySentence) redisUtil.getObject(RedisServiceImpl.dailySentence);
        if(dailySentence==null){//判断第一次是否存在
            redisUtil.setObject(RedisServiceImpl.dailySentence,dailySentenceMapper.selectByPrimaryKey(1));
        }else {
            int num = Integer.valueOf(dailySentence.getId());
            num++;
            DailySentence newDailySentence = dailySentenceMapper.selectByPrimaryKey(num);
            if(newDailySentence==null){//判断是否到达最大
                redisUtil.setObject(RedisServiceImpl.dailySentence,dailySentenceMapper.selectByPrimaryKey(1));
            }else {
                redisUtil.setObject(RedisServiceImpl.dailySentence,newDailySentence);
            }
        }
    }
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
