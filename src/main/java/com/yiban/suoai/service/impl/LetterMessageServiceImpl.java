package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.LetterMessageMapper;
import com.yiban.suoai.pojo.LetterMessage;
import com.yiban.suoai.service.LetterMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LetterMessageServiceImpl implements LetterMessageService {
    @Autowired
    LetterMessageMapper letterMessageMapper;
    @Override
    public void insert(LetterMessage letterMessage) {
        letterMessageMapper.insert(letterMessage);
    }
}
