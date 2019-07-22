package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.MessageMapper;
import com.yiban.suoai.pojo.Message;
import com.yiban.suoai.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    MessageMapper messageMapper;

    @Override
    public int add(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public Message get(int id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Message message) {
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public int delete(int id) {
        return messageMapper.deleteByPrimaryKey(id);
    }
}
