package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.WeekWordMapper;
import com.yiban.suoai.pojo.DailySentence;
import com.yiban.suoai.pojo.WeekWord;
import com.yiban.suoai.service.WeekWordService;
import com.yiban.suoai.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeekWordImpl implements WeekWordService {
    @Autowired
    WeekWordMapper weekWordMapper;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public void updateByWeek() {
        WeekWord weekWord= (WeekWord) redisUtil.getObject(RedisServiceImpl.weekWord);
        if(weekWord==null){//判断第一次是否存在
            redisUtil.setObject(RedisServiceImpl.weekWord, weekWordMapper.selectByPrimaryKey(1));
        }else {
            int num = Integer.valueOf(weekWord.getId());
            num++;
            WeekWord newWeekWord = weekWordMapper.selectByPrimaryKey(num);
            if(newWeekWord==null){//判断是否到达最大

            }else {
                redisUtil.setObject(RedisServiceImpl.weekWord,newWeekWord);
            }
        }
    }

    @Override
    public WeekWord getByWeek() {
        return (WeekWord) redisUtil.getObject(RedisServiceImpl.weekWord);
    }
}
