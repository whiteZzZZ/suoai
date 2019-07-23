package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.NoticeMapper;
import com.yiban.suoai.pojo.Notice;
import com.yiban.suoai.pojo.NoticeExample;
import com.yiban.suoai.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;


    @Override
    public int add(Notice notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public int delete(int id) {
        return noticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Notice get(int id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Notice> getAllByUserId(int userId) {
        //要取到特定用的通知 和 全体的通知
        NoticeExample example=new NoticeExample();
         List<Integer>  integerList=new ArrayList<>();
         integerList.add(0);
         integerList.add(userId);
        example.createCriteria().andUserIdIn(integerList);
        return noticeMapper.selectByExample(example);
    }
}
